package com.example.pranayanand.phpmysqlsignupandlogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Pranay Anand on 23-12-2017.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("Api.php?apicall=signup")
    Call<Responses> register(@Field("username") String username, @Field("email") String email , @Field("password") String password, @Field("gender") String gender);

}
