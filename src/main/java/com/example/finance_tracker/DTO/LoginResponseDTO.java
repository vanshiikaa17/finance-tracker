package com.example.finance_tracker.DTO;

public class LoginResponseDTO {
    private String jwtToken;

    public LoginResponseDTO(String token){
        this.jwtToken=token;
    }

    public String getToken(){
        return jwtToken;
    }
}
