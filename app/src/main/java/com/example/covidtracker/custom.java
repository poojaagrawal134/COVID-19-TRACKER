package com.example.covidtracker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class custom extends AppCompatActivity {
   private ViewPager mviewpager;
   intViewAdapter mintViewAdapter;
   TabLayout tabLayout;
   Button btnnext;
   int position=0;
   Button get;
   Animation btn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom);
        btnnext=findViewById(R.id.button);
        get=findViewById(R.id.getstarted);
        tabLayout=findViewById(R.id.tab_indicator);
        btn= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.button_animation);

        final List<screenItem> mList=new ArrayList<>();
        mList.add(new screenItem("Stay Safe","Lets fight with Corona Virus by taking Precautions",R.drawable.woman));
        mList.add(new screenItem("Stay Home","Stay At home to Stop this Virus",R.drawable.house));
        mList.add(new screenItem("COVID - 19","We have to fight against this",R.drawable.covid12));
        mviewpager=findViewById(R.id.screenvp);
        mintViewAdapter=new intViewAdapter(this,mList);
        mviewpager.setAdapter(mintViewAdapter);

        tabLayout.setupWithViewPager(mviewpager);

        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                position=mviewpager.getCurrentItem();
                if(position<mList.size())
                {
                    position++;
                    mviewpager.setCurrentItem(position);
                }
                if(position==mList.size()-1)
                {
                    loadlastscreen();
                }
            }
        });
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(custom.this,Home.class));
            }
        });
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==mList.size())
                {
                    loadlastscreen();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void loadlastscreen() {
        btnnext.setVisibility(View.INVISIBLE);
        get.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.INVISIBLE);
        get.setAnimation(btn);

    }
}

