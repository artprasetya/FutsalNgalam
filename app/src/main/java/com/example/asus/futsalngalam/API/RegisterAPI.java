package com.example.asus.futsalngalam.API;

import com.example.asus.futsalngalam.Model.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ASUS on 19-Nov-17.
 */

public interface RegisterAPI {
    @FormUrlEncoded
    @POST("insert.php")
    Call<Value> signup(@Field("id") String id,
                       @Field("nama") String nama,
                       @Field("email") String email,
                       @Field("password") String password,
                       @Field("telepon") String telepon);
}
