package com.example.nycftaetix;

public class SignIn {
    private String email;
    private String password;

    SignIn(){ }
    SignIn(String email, String password){
        this.email = email;
        this.password = password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
