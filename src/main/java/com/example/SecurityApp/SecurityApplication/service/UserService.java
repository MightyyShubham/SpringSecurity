package com.example.SecurityApp.SecurityApplication.service;

import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.DTO.SignUpDTO;
import com.example.SecurityApp.SecurityApplication.DTO.UserDTO;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Exception.ResourceNotFoundException;
import com.example.SecurityApp.SecurityApplication.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("========== AUTH START ==========");
        System.out.println("Username received: [" + username + "]");

        System.out.println("BEFORE DB CALL");

        Optional<User> optionalUser = userRepository.findByEmail(username);

        System.out.println("AFTER DB CALL");
        System.out.println("User present: " + optionalUser.isPresent());
        return userRepository.findByEmail(username).orElseThrow(()->new BadCredentialsException("User with Email"+username+"Not found"));
    }

    public User getUserById(Long userId){
        return  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User with Email"+userId+"Not found"));
    }
    public UserDTO signUp(SignUpDTO signUpDTO)
    {
        Optional<User> user = userRepository.findByEmail(signUpDTO.getEmail());
        if (user.isPresent())
        {
            throw new BadCredentialsException("User is Already Existing in Memory");
        }
        User toBeCreateDUser = modelMapper.map(signUpDTO,User.class);
        toBeCreateDUser.setPassword(passwordEncoder.encode(toBeCreateDUser.getPassword()));
        User saveUser = userRepository.save(toBeCreateDUser);
        return modelMapper.map(saveUser,UserDTO.class);
    }


}
