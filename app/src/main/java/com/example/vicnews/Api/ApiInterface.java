package com.example.vicnews.Api;


import com.example.vicnews.model.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("top-headlines")
    Call<BaseResponse> getNews(
            @Query("country") String country
    );
}
