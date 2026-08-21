package com.example.SecurityApp.SecurityApplication.Controller;

import com.example.SecurityApp.SecurityApplication.Entities.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
@Slf4j
public class PostController {

    @GetMapping("/1")
    public String message(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("user {}",user);
        return "Fuck You";
    }
}
