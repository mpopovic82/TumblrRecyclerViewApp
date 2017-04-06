package com.example.matej.myapplication.UI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.matej.myapplication.R;
import com.example.matej.myapplication.adapter.EndlessRecyclerViewScrollListener;
import com.example.matej.myapplication.adapter.TumblrAdapter;
import com.example.matej.myapplication.models.Example;
import com.example.matej.myapplication.models.TumblrPosts;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycleList)
    public RecyclerView recycleList;


    public TumblrAdapter adapter;
    private EndlessRecyclerViewScrollListener scrollListener;//
    private LinearLayoutManager linearLayoutManager;

    private Example example;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        linearLayoutManager = new LinearLayoutManager(this);
        recycleList.setLayoutManager(linearLayoutManager);
        /*scrollListener = new EndlessRecyclerViewScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {

            }
        }*/



        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit.Builder builder = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.tumblr.com/v2/blog/")
                .addConverterFactory(GsonConverterFactory.create());



        Retrofit retrofit = builder.build();
        TumblrPosts posts = retrofit.create(TumblrPosts.class);
        Call<Example> call = posts.postsForUser("machineinghost");

        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("MainActivity: ", "response successful");
                if(response.isSuccessful()) {
                    int totalPosts = response.body().getResponse().getTotalPosts();
                    Log.d("MainActivity: ", "totalPosts = " + totalPosts);
                    example = response.body();
                    setExample(response.body());

                    setRecyclerView();
                }
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.d("MainActivity: ", "response failed");
                Log.e("MainActivity: ", "throwable: " + t.toString());

            }
        });
    }

    private void setRecyclerView() {
        example.getResponse().getPosts().get(1).getSummary();
        example.getResponse().getPosts().get(1).getPhotos().get(0).getAltSizes().get(1).getUrl();

        Log.d("MainActivity: ", "example.getResponse().getPosts().get(1).getCaption(): " + example.getResponse().getPosts().get(1).getSummary());
        Log.d("MainActivity: ", "example.getResponse().getPosts().get(1).getPhotos().get(0).getAltSizes().get(1).getUrl(): "
                                    + example.getResponse().getPosts().get(1).getPhotos().get(0).getAltSizes().get(1).getUrl());

        //adapter = new TumblrAdapter(example, this);
        adapter = new TumblrAdapter(example.getResponse().getPosts().subList(0, 5), this);

        recycleList.setAdapter(adapter);

        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Triggered only when new data needs to be appended to the list
                // Add whatever code is needed to append new items to the bottom of the list
                Log.d("MainActivity", "thread: " + Thread.currentThread().getName());
                Log.d("MainActivity", "page: " + page);
                Log.d("MainActivity", "totalItemsCount: " + totalItemsCount);

                loadNextDataFromApi(page);
            }
        };
        // Adds the scroll listener to RecyclerView
        recycleList.addOnScrollListener(scrollListener);
    }

    public Example getExample() {
        return example;
    }

    public void setExample(Example example) {
        this.example = example;
    }

    public void loadNextDataFromApi(int offset) {
        adapter.fillPosts(example.getResponse().getPosts().subList(6, 10));
        adapter.notifyDataSetChanged();


        // Send an API request to retrieve appropriate paginated data
        //  --> Send the request including an offset value (i.e `page`) as a query parameter.
        //  --> Deserialize and construct new model objects from the API response
        //  --> Append the new data objects to the existing set of items inside the array of items
        //  --> Notify the adapter of the new items made with `notifyItemRangeInserted()`
    }

}
