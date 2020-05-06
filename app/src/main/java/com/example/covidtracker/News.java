package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.covidtracker.parameter.Articles;
import com.example.covidtracker.parameter.Headlines;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class News extends AppCompatActivity {
    RecyclerView recyclerView;
    Adapter adapter;
    final String API_KEY="c474fb4e31954775a0ff5543c51b80e8";
    Button refreshb;
    List<Articles> articles=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        recyclerView=findViewById(R.id.rc);
        refreshb=findViewById(R.id.re);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final String country=getCountry();
        fetchJSON(country,API_KEY);


    }

    private void fetchJSON(String country, String api_key) {
        Call<Headlines> call=Client.getInstance().getApi().getHeadlines(country,API_KEY);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if(response.isSuccessful() && response.body().getArticles()!=null)
                {
                    articles.clear();
                    articles=response.body().getArticles();
                    adapter=new Adapter(News.this,articles);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(News.this,"Check your internet Connection",Toast.LENGTH_SHORT).show();
            }
        });

    }

    private String getCountry() {
        Locale locale=Locale.getDefault();
        String country=locale.getCountry();
        return country.toLowerCase();
    }
}
