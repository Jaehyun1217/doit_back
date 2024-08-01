package com.example.demo.home;

import com.example.demo.user.dto.request.SignUpRequestDto;
import com.example.demo.user.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LogInController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}


