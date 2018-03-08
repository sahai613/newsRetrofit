package com.beginner.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private adapter adapter1;
    private Api_Interface apiInterface;
    private   ArrayList<Article> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        apiInterface=ApiClient.getApiClient().create(Api_Interface.class);
        apiInterface.getTopStories().enqueue(new Callback<List<Integer>>() {
            @Override
            public void onResponse(Call<List<Integer>> call, Response<List<Integer>> response) {
                List<Integer> topStories = response.body();
                for (int i = 0; i < 5; i++) {
                    apiInterface.getArticle(topStories.get(i)).enqueue(articleCallback);
                }
            }
            Callback<String> articleCallback = new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    String json = response.body();
                    JSONObject obj= null;

                    try {
                        obj = new JSONObject(json);
                        String articleTitle=obj.getString("title");
                        String articleURL=obj.getString("url");
                        list.add(new Article(articleTitle,articleURL));
                        adapter1.notifyDataSetChanged();
                        recyclerView.setAdapter(adapter1);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {

                }
            };

            @Override
            public void onFailure(Call<List<Integer>> call, Throwable t) {

            }
        });

    };}

