package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class Details extends AppCompatActivity {
 private int positioncountry;
    TextView country,cases,todaycases,deaths,todaydeaths,recovered,active,critical;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent intent=getIntent();
        positioncountry=intent.getIntExtra("position",0);
        getSupportActionBar().setTitle("Details of "+ Affectedcountry.countryModelList.get(positioncountry).getCountry());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        country=findViewById(R.id.Tvcountry);

        cases=findViewById(R.id.Tvcases);
        todaycases=findViewById(R.id.Ttodaycases);
        todaydeaths=findViewById(R.id.Tvtodaydeath);
        recovered=findViewById(R.id.Tvrec);
        active=findViewById(R.id.Tvacrive);
        critical=findViewById(R.id.Tvcritical);
        deaths=findViewById(R.id.Tvdeaths);

        country.setText(Affectedcountry.countryModelList.get(positioncountry).getCountry());
        recovered.setText(Affectedcountry.countryModelList.get(positioncountry).getRecovered());
        active.setText(Affectedcountry.countryModelList.get(positioncountry).getActive());
        todaydeaths.setText(Affectedcountry.countryModelList.get(positioncountry).getTodaydeaths());
        todaycases.setText(Affectedcountry.countryModelList.get(positioncountry).getTodaycases());
        deaths.setText(Affectedcountry.countryModelList.get(positioncountry).getDeaths());
        cases.setText(Affectedcountry.countryModelList.get(positioncountry).getCases());
        critical.setText(Affectedcountry.countryModelList.get(positioncountry).getCritical());

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId()==android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
