package com.example.demo.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/lecture")
@Controller
public class LectureController {
    @GetMapping("/info")
    public String info(){
        return "lecture";
    }

    @GetMapping("/list")
    public String list(){
        return "lecture_list";
    }
}
