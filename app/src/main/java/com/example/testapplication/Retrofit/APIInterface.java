package com.example.testapplication.Retrofit;

import com.example.testapplication.Model.TitleModel;
import com.google.gson.JsonElement;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {
    @GET(APIConstants.API_TITLE_URL)
    Call<JsonElement>getTitleList(@Query("tags") String tags, @Query("page") int PageNo);
}
