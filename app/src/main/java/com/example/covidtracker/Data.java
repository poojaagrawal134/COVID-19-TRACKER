package com.example.covidtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.leo.simplearcloader.SimpleArcLoader;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;
import org.json.JSONException;
import org.json.JSONObject;

public class Data extends AppCompatActivity {
    TextView tvCases,tvRecoverd ,tvCritical, tvActive,tvTodayCases,tvTodaydeaths,tvTotaldeath,tvAffected;
    SimpleArcLoader simpleArcLoader;
    ScrollView scrollView;
    PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        tvCases=findViewById(R.id.tvcases);
        tvRecoverd=findViewById(R.id.tvrec);
        tvCritical=findViewById(R.id.tvcri);
        tvActive=findViewById(R.id.tvact);
        tvTodayCases=findViewById(R.id.tvtdcas);
        tvTodaydeaths=findViewById(R.id.tvodaydeath);
        tvTotaldeath=findViewById(R.id.tvtotaldeath);
        tvAffected=findViewById(R.id.tvaffect);
        simpleArcLoader=findViewById(R.id.loader);
        scrollView=findViewById(R.id.scrlstat);
        pieChart=findViewById(R.id.piechart);
        fetchdata();
    }

    private void fetchdata() {
        String url="https://corona.lmao.ninja/v2/all";
        simpleArcLoader.start();
        StringRequest request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject=new JSONObject(response.toString());
                    tvCases.setText(jsonObject.getString("cases"));
                    tvActive.setText(jsonObject.getString("active"));
                    tvRecoverd.setText(jsonObject.getString("recovered"));
                    tvCritical.setText(jsonObject.getString("critical"));
                    tvTodayCases.setText(jsonObject.getString("todayCases"));

                    tvTotaldeath.setText(jsonObject.getString("deaths"));
                    tvTodaydeaths.setText(jsonObject.getString("todayDeaths"));
                    tvAffected.setText(jsonObject.getString("affectedCountries"));
                    pieChart.addPieSlice(new PieModel("Cases",Integer.parseInt(tvCases.getText().toString()), Color.parseColor("#ffa726")));
                    pieChart.addPieSlice(new PieModel("Recovered",Integer.parseInt(tvRecoverd.getText().toString()), Color.parseColor("#66bb6a")));
                    pieChart.addPieSlice(new PieModel("Active",Integer.parseInt(tvActive.getText().toString()), Color.parseColor("#29b6f6")));
                    pieChart.addPieSlice(new PieModel("Deaths",Integer.parseInt(tvTotaldeath.getText().toString()), Color.parseColor("#ef5350")));
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    simpleArcLoader.stop();
                    simpleArcLoader.setVisibility(View.GONE);
                    scrollView.setVisibility(View.VISIBLE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                simpleArcLoader.stop();
                simpleArcLoader.setVisibility(View.GONE);
                scrollView.setVisibility(View.VISIBLE);
                Toast.makeText(Data.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void Track(View view) {
          startActivity(new Intent(getApplicationContext(), Affectedcountry.class));
    }


}
