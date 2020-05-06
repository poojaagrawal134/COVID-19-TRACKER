package com.example.covidtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1 ;
     TextView track,symptoms,proct,nearby,help,news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        track=findViewById(R.id.covid);
        symptoms=findViewById(R.id.sym);
        nearby=findViewById(R.id.near);
        proct=findViewById(R.id.proc);
        help=findViewById(R.id.help);
        news=findViewById(R.id.news);
        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,News.class));
            }
        });
        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Help.class));
            }
        });
        nearby.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.coronatracker.in/"));
                startActivity(intent);
            }
        });
        track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Data.class));
            }
        });
        symptoms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Symptoms.class));
            }
        });
        proct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Protection.class));
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.hel,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.log:
                startActivity(new Intent(Home.this,Settings.class));
                break;
            case R.id.mic:
                speak();
                break;
            case R.id.notification:
                getnotification();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    private void getnotification() {
        String message="Hello";
        NotificationCompat.Builder builder=new NotificationCompat.Builder(Home.this).setSmallIcon(R.drawable.ic_chat_black_24dp)
                .setContentTitle("New Notification")
                .setContentText(message).setAutoCancel(true);
        Intent intent=new Intent(Home.this,Notification.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Message",message);
        PendingIntent pendingIntent=PendingIntent.getActivity(Home.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,builder.build());
    }

    private void speak() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello, Say Something");

        try{
            startActivityForResult(intent,REQUEST_CODE_SPEECH_INPUT);
        }catch (Exception e){
            Toast.makeText(Home.this,""+e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }


}
