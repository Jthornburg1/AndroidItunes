package com.example.jon_thornburg.secondituneshitter.activities.utils;

import com.example.jon_thornburg.secondituneshitter.activities.models.ItunesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jon_thornburg on 1/6/17.
 */

public interface ItunesInterface {
    @GET("search")
    Call<ItunesResponse> getItunesItems(@Query("term") String term);
}
