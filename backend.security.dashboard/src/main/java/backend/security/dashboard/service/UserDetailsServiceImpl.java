package backend.security.dashboard.service;

import backend.security.dashboard.model.User;
import backend.security.dashboard.model.UserDetailsImpl;
import backend.security.dashboard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public  UserDetailsServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=this.userRepository.findByUsername(username).orElseThrow(
                ()->new UsernameNotFoundException("user not found")
        );
        return UserDetailsImpl.build(user);
    }
}
