package com.beginner.news;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by sahai613 on 08-03-2018.
 */

public class ApiClient {
    public static final String Base_URL="https://hacker-news.firebaseio.com/";
    public static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
        if(retrofit==null){
            retrofit=new Retrofit.Builder().baseUrl(Base_URL).addConverterFactory(ScalarsConverterFactory.create()).build();

        }
        return retrofit;
    }
}
