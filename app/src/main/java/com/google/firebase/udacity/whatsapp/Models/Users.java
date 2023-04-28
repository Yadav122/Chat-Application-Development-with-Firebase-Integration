package com.google.firebase.udacity.whatsapp.Models;

public class Users {
    String profilePic ,userName ,Email ,password ,userId , lastMessage, status;

    public Users(String profilePic, String userName, String email, String password, String userId, String lastMessage) {
        this.profilePic = profilePic;
        this.userName = userName;
        Email = email;
        this.password = password;
        this.userId = userId;
        this.lastMessage = lastMessage;
    }
    public Users(){}

    public Users(String userName, String email, String password) {
        this.userName = userName;
        Email = email;
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
