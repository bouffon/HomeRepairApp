package com.example.benji.homerepairapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class SetProviderRating extends AppCompatActivity {

   private ServiceProvider sp;
   private DBHandler db;
   private RatingBar ratingBar;
   User hO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_provider_rating);

        hO = (User) getIntent().getSerializableExtra("hO");

        db = new DBHandler(this);
        sp = (ServiceProvider) getIntent().getSerializableExtra("sp");

        ratingBar = findViewById(R.id.rating_rating_bar);
        View rateButton = findViewById(R.id.rate);

        TextView spName = findViewById(R.id.serviceProviderName);
        spName.setText(sp.getfName() + " " + sp.getlName());

        rateButton.setOnClickListener(new View.OnClickListener() {

            //take rating from bar as a double
            double rating = ratingBar.getRating();

            @Override
            public void onClick(View v) {

                TextView comment =  findViewById(R.id.comment);

                db.updateRating(sp.getUsername(), sp.getPassword(), rating, comment.getText().toString());
                Toast.makeText(getApplicationContext(), "Service provider rating successfully added", Toast.LENGTH_LONG).show();

                switchToHomeOwnerPage();
            }
        });
    }

    private void switchToHomeOwnerPage(){

        Intent i = new Intent(this, HomeOwnerPage.class);
        i.putExtra("hO", hO);
        startActivity(i);
    }
}
