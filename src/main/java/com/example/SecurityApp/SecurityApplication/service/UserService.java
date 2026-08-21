package com.example.SecurityApp.SecurityApplication.service;

import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.DTO.SignUpDTO;
import com.example.SecurityApp.SecurityApplication.DTO.UserDTO;
import com.example.SecurityApp.SecurityApplication.Entities.User;
import com.example.SecurityApp.SecurityApplication.Exception.ResourceNotFoundException;
import com.example.SecurityApp.SecurityApplication.Repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username).orElseThrow(()->new BadCredentialsException("USer with Email"+username+"Not found"));
    }

    public User getUserById(Long userId){
        return  userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("USer with Email"+userId+"Not found"));
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
