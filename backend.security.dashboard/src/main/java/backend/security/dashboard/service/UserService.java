package backend.security.dashboard.service;

import backend.security.dashboard.dto.*;
import backend.security.dashboard.mapper.UserMapper;
import backend.security.dashboard.model.*;
import backend.security.dashboard.repository.IpRepository;
import backend.security.dashboard.repository.OtpRepository;
import backend.security.dashboard.repository.RoleRepository;
import backend.security.dashboard.repository.UserRepository;
import backend.security.dashboard.utils.JwtUtils;
import backend.security.dashboard.utils.OtpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.Optional;
import java.util.Random;


@Service
public class UserService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    private final JwtUtils jwtUtils;

    private final OtpRepository otpRepository;

    private final IpRepository ipRepository;

    private  final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository,UserMapper userMapper,EmailService emailService,PasswordEncoder passwordEncoder
            ,RoleRepository roleRepository,JwtUtils jwtUtils,OtpRepository otpRepository
            ,IpRepository ipRepository,AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.ipRepository = ipRepository;
        this.otpRepository = otpRepository;
        this.authenticationManager = authenticationManager;
    }

    public List<UserDTO> getAllUsers(){
        List<User> users = this.userRepository.findAll();
        return this.userMapper.toUsersDTO(users);
    }

    public UserDTO addUser(UserRequestDTO addUserRequest){

       String firstName = addUserRequest.getFirstName();
       String lastName = addUserRequest.getLastName();
       String username = this.createRandomUserName(firstName,lastName);

        String password = this.createRandomPassword();

        User user = new User(username,
                firstName,lastName,addUserRequest.getEmail(),
                this.passwordEncoder.encode(password),addUserRequest.getPhoneNumber()
        );

        user.setAccountStatus(AccountStatus.ACTIVE);
        user.setCreationDate(LocalDateTime.now());
        Optional<Role> role = this.roleRepository.findRoleByName(ERole.valueOf(addUserRequest.getRole()));
        user.setRole(role.get());
        userRepository.save(user);

        StringBuilder emailBodyBuilder = new StringBuilder();
        emailBodyBuilder.append("Your account has been successfully created")
                .append("\nUsername : ")
                .append(username)
                .append("\nPassword : ")
                .append(password);
        this.emailService.sendEmail(addUserRequest.getEmail(),"Account Creation",emailBodyBuilder.toString());

        return userMapper.toUserDTO(user);
    }

    public UserDTO updateUser(UserRequestDTO updateUserRequest,String username){

        User user = this.userRepository.findByUsername(username).orElseThrow(
                ()-> new UsernameNotFoundException(username+" not found")
        );

        Role role = this.roleRepository.findRoleByName(ERole.valueOf(updateUserRequest.getRole())).orElseThrow(
                ()-> new RuntimeException("Role "+updateUserRequest.getRole()+ " not found ")
        );

        user.setAccountStatus(AccountStatus.valueOf(updateUserRequest.getAccountStatus()));
        user.setRole(role);
        user.setEmail(updateUserRequest.getEmail());
        user.setFirstName(updateUserRequest.getFirstName());
        user.setLastName(updateUserRequest.getLastName());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber());

        this.userRepository.save(user);

        return this.userMapper.toUserDTO(user);
    }

    public ResponseEntity<?> login(LoginDTO loginRequest, HttpServletRequest httpServletRequest){
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        String ipAddress = httpServletRequest.getRemoteAddr();
        User user = userRepository.findByUsername(loginRequest.getUsername()).orElse(null);
        boolean isOtpRequired = user.getIps().stream().noneMatch(ip -> ip.getAddress().equals(ipAddress));

        if(isOtpRequired ){
            logger.warn("OTP is required for user : {}",user.getUsername());
            boolean hasUserInvalidOtp = user.getOtp() == null || OtpUtils.hasExpired(user.getOtp()) ;
            if(!StringUtils.hasText(loginRequest.getOtp()) || hasUserInvalidOtp) {
                if(hasUserInvalidOtp) {
                    Otp otp = new Otp();
                    otpRepository.save(otp);
                    user.setOtp(otp);
                    userRepository.save(user);
                    this.emailService.sendEmail(user.getEmail(), "OTP", otp.getValue());
                }
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageDTO("OTP_REQUIRED"));
            }

            if(!OtpUtils.matches(user.getOtp(),new Otp(loginRequest.getOtp())))
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageDTO("INCORRECT_OTP"));

            Ip newIp = new Ip(ipAddress,LocalDateTime.now(),user);
            user.getIps().add(newIp);
            user.setOtp(null);
            ipRepository.save(newIp);
            userRepository.save(user);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = this.jwtUtils.generateJwtToken(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        logger.info("User {} has been connected successfully",user.getUsername());
        return ResponseEntity.ok().body(
                new LoginResponseDTO(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(),
                        userDetails.getAuthorities().stream().findFirst().get().getAuthority()
                ));
    }

    public UserDTO getUserByUsername(String username){
        User user = this.userRepository.findByUsername(username).orElse(null);
        return userMapper.toUserDTO(user);
    }
    private String createRandomUserName(String firstName,String lastName){

        UUID uuid = UUID.randomUUID();
        String randomString = uuid.toString().substring(0,6);
        StringBuilder usernameBuilder = new StringBuilder();
        usernameBuilder.append(firstName.charAt(0))
                .append(lastName.charAt(0))
                .append(randomString);

        return usernameBuilder.toString().toUpperCase();
    }

    private String createRandomPassword(){
        int passwordLength = 12;
        String allowedChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()";

        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();

        while (passwordBuilder.length() < passwordLength) {
            int randomIndex = random.nextInt(allowedChars.length());
            char randomChar = allowedChars.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();

    }



}
