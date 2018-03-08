package com.beginner.news;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by sahai613 on 08-03-2018.
 */

public interface Api_Interface {

    @GET("v0/topstories.json?print=pretty")
    Call<List<Integer>> getTopStories();
    @GET("v0/item/{articleid}.json?print=pretty")
    Call<String> getArticle(@Path("articleid") int id);

}
