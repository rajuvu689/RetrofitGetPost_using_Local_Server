package com.rtsoftworld.retrofitpostget.Model;

import com.google.gson.annotations.SerializedName;

public class ServerResponse { //this class get data from server
    @SerializedName("status")
    boolean statusString;
    @SerializedName("message")
    String messageString;

    public boolean isSuccess(){
        return statusString;
    }

    public String getMessageString() {
        return messageString;
    }
}
