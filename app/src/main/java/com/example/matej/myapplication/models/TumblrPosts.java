package com.example.matej.myapplication.models;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface TumblrPosts {

   @GET("{blog-identifier}/posts/photo?api_key=mGkf7izHQpiTP1t2aqe6loV8TH7Q0fTacp23etEbB0r7UYrani")
   Call<Example> postsForUser(@Path("blog-identifier") String user);
}
