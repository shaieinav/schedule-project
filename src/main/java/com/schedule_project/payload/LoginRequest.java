package com.schedule_project.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {

//    @NotBlank
//    private String usernameOrEmail;
//
//    @NotBlank
//    private String password;
//
//    public String getUsernameOrEmail() {
//        return usernameOrEmail;
//    }
//
//    public void setUsernameOrEmail(String usernameOrEmail) {
//        this.usernameOrEmail = usernameOrEmail;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}