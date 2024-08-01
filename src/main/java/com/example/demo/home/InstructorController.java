package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class InstructorController {
    @GetMapping("/instructor")
    public String info(){
        return "instructor_info";
    }
}
