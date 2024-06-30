package com.example.demo.controller;


import com.example.demo.dto.SignupRequest;
import com.example.demo.dto.SignupRespnse;
import com.example.demo.service.SignService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sign")
@RequiredArgsConstructor
public class SignController {
    private final SignService singService;

    @PostMapping("/signup")
    public ResponseEntity<SignupRespnse> signupPage(@RequestBody SignupRequest dto){
        return ResponseEntity.ok().body(singService.signup(dto));
    }
}
