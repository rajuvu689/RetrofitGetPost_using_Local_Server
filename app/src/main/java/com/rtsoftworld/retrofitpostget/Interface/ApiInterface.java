package com.rtsoftworld.retrofitpostget.Interface;

import com.rtsoftworld.retrofitpostget.Model.ServerResponse;
import com.rtsoftworld.retrofitpostget.Model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("retrofit_get_post/server_side_code.php")
    Call<ServerResponse> getUserValidity(@Body User userLoginCredential);

    @GET("retrofit_get_post/server_side_code.php")
    Call<ServerResponse> getJoke(@Query("user_id") String userId);
}
