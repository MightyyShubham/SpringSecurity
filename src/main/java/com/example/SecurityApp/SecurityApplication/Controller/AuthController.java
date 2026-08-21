package com.example.SecurityApp.SecurityApplication.Controller;

import com.example.SecurityApp.SecurityApplication.DTO.LoginDTO;
import com.example.SecurityApp.SecurityApplication.DTO.SignUpDTO;
import com.example.SecurityApp.SecurityApplication.DTO.UserDTO;
import com.example.SecurityApp.SecurityApplication.service.AuthService;
import com.example.SecurityApp.SecurityApplication.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@RequestBody SignUpDTO signUpDTO)
    {
        UserDTO userDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO, HttpServletRequest request,HttpServletResponse response)
    {
        String Token = authService.login(loginDTO);
        Cookie cookie = new Cookie("token",Token);
        cookie.setHttpOnly(true);
//        cookie.setSecure();
        response.addCookie(cookie);
        return ResponseEntity.ok(Token);
    }

    @GetMapping("/secertMessage")
    public String message(){
        return "Fuck You";
    }

}
