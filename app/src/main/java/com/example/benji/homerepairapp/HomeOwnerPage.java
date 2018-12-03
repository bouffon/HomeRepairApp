package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HomeOwnerPage extends AppCompatActivity {
    private User hO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_owner_page);
        Intent i = getIntent();
        hO = (User) i.getSerializableExtra("hm");
    }

    public void searchByServices(View view){
        Intent intent = new Intent(this, SearchByService.class);
        intent.putExtra("hO", hO);
        startActivity(intent);
    }

    public void searchByHours(View view){
        Intent intent = new Intent(this, SearchByHours.class);
        intent.putExtra("hO", hO);
        startActivity(intent);
    }

    public void searchByRatings(View view){
        Intent intent = new Intent(this, SearchByRating.class);
        intent.putExtra("hO", hO);
        startActivity(intent);
    }

    public void giveRatings(View view){
        Intent intent = new Intent(this, RateProvider.class);
        intent.putExtra("hO", hO);
        startActivity(intent);
    }
}
