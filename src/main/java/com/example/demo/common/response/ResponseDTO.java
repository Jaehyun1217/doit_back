package com.example.demo.common.response;

import lombok.Builder;


public record ResponseDTO (String message, Object data){
    @Builder
    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}