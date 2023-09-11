package backend.security.dashboard.controller;

import backend.security.dashboard.dto.LoginDTO;
import backend.security.dashboard.dto.UserDTO;
import backend.security.dashboard.dto.UserRequestDTO;
import backend.security.dashboard.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    private UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){

        return ResponseEntity.ok().body(this.userService.getAllUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginRequest, HttpServletRequest httpServletRequest, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return this.userService.login(loginRequest,httpServletRequest);
    }

    @PostMapping("/add-user")
    public ResponseEntity<UserDTO> addUser(@RequestBody @Valid UserRequestDTO addUserRequest , BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok().body(this.userService.addUser(addUserRequest));
    }

    @PostMapping("/update-user/{username}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String username,@RequestBody @Valid UserRequestDTO userUpdateRequest , BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        return ResponseEntity.ok().body(this.userService.updateUser(userUpdateRequest,username));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDTO> getUserByUserName(@PathVariable String username){
        return ResponseEntity.ok().body(this.userService.getUserByUsername(username));
    }

}
