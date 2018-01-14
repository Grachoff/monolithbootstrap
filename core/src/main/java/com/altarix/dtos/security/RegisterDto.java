package com.altarix.dtos.security;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterDto implements Serializable{
    private String email;
    private String password;
    private String recaptchaResponse;
}