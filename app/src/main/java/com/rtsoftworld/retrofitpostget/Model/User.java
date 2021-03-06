package com.rtsoftworld.retrofitpostget.Model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("user_id")
    private String userId;
    @SerializedName("password")
    private String password;

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
