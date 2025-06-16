package models.pojo;

public class LoginBodyModel {
    // String authData = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";
    String email;


    public void setEmail(String email) {
        this.email = email;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    String password;

}
