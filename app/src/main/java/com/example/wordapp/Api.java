package com.example.wordapp;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("words?")
    Call<List<SearchResponse>> getWord(
            @Query("ml") String word
    );



}


