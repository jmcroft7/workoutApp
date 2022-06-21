package com.croft.workoutApp.security;

import com.croft.workoutApp.model.User;
import com.croft.workoutApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        Optional<User> dbUser = userRepo.findByEmail(username);
//        if (dbUser.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }

        return new CustomUserDetails(user);
    }

}
