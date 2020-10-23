package com.example.vicnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.vicnews.Api.ApiClient;
import com.example.vicnews.Api.ApiInterface;
import com.example.vicnews.model.Articles;
import com.example.vicnews.model.BaseResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
   // public  String ApiKey = "5fcc7f5d39ce4260a411dc62e3f0892d";
    List<Articles> articles = new ArrayList<>();
    Adapter adapter;
    TextView data1;
    private String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.rec);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        ((LinearLayoutManager)layoutManager).setOrientation(LinearLayoutManager.VERTICAL);


        loadJsonData();

    }


    public void loadJsonData(){
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<BaseResponse> call;
        call = apiInterface.getNews("us");
       call.enqueue(new Callback<BaseResponse>() {
           @Override
           public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
               if (response.isSuccessful() && response.body().getArticles() != null){
                   articles = response.body().getArticles();
                   adapter = new Adapter(  MainActivity.this, articles);
                   recyclerView.setAdapter(adapter);
                   recyclerView.setHasFixedSize(true);
                   adapter.notifyDataSetChanged();
               }
               Log.d("data", response.message());
           }

           @Override
           public void onFailure(Call<BaseResponse> call, Throwable t) {
               Log.d("failure", t.getMessage());
           }
       });
    }


    @Override
    public void onItemClick(View v, int adapterPosition) {

    }

}
