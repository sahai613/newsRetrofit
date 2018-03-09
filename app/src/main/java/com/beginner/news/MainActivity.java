package com.beginner.news;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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

    private Api_Interface apiInterface;
    public ArrayList<Article> list=new ArrayList<Article>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        final adapter eadapter = new adapter(list);
        recyclerView.setAdapter(eadapter);
        apiInterface=ApiClient.getClient(this).create(Api_Interface.class);
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


                    try {
                        JSONObject obj = new JSONObject(json);
                        String articleTitle=obj.getString("title");
                        String articleURL=obj.getString("url");
                        Log.i("Title",articleTitle);
                        Log.i("Link",articleURL);
                        list.add(new Article(articleTitle,articleURL));
                        eadapter.notifyDataSetChanged();
                        recyclerView.setAdapter(eadapter);



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
