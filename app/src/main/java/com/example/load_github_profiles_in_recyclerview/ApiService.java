package com.example.load_github_profiles_in_recyclerview;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("users/{user}/repos")
    Call<List<ResponseModel>> getData(@Path("user") String user);

}
