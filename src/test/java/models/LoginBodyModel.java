package models;

public class LoginBodyModel {
    // String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
    String email;

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

    String password;

}
