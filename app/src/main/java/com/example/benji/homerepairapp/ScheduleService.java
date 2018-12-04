package com.example.benji.homerepairapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;


public class ScheduleService extends AppCompatActivity {

    DBHandler db;
    List<ServiceProvider> serviceProviders;
    ArrayList<ServiceProvider>searchedServiceProviders;
    String searchType;
    ServiceProvider serviceProvider;
    User homeOwner;

    Intent i;   //public intent so that it can be called in the switch case

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_service);

        db = new DBHandler(this);
        serviceProviders = db.getAllSP();   //get all service providers from DB
        searchedServiceProviders = new ArrayList<ServiceProvider>();    //initialize list of searched service providers

        ListView listView = (ListView) findViewById(R.id.listView);

        i = getIntent();
        searchType = (String) i.getSerializableExtra("searchType");
        homeOwner = (User) i.getSerializableExtra("hO");

        if(searchType != null){
            switch (searchType) {
                case "service":
                    searchByService((String) i.getSerializableExtra("serviceName"));
                    break;

                case "hours":
                    Log.d("day of the week", i.getSerializableExtra("day").toString());
                    searchByHours((String) i.getSerializableExtra("startTime"), (String) i.getSerializableExtra("endTime"), (String) i.getSerializableExtra("day"));
                    break;

                case "rating":
                    searchByRating((Double) i.getSerializableExtra("rating"));
                    break;
            }
        }

        ServiceProviderAdapter spAdapter = new ServiceProviderAdapter(this, searchedServiceProviders);
        listView.setAdapter(spAdapter);

        //add listeners
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int position, long id) {

                serviceProvider = (ServiceProvider) adapterView.getItemAtPosition(position);    //service provider at selected position
                addPrompt(findViewById(android.R.id.content));
            }
        });

    }

    public void searchByService(String serviceName){

        String[] servicesOffered;

        for(int i = 0; i< serviceProviders.size(); i++){

            if(serviceProviders.get(i).getServices().length != 0) {

                servicesOffered = serviceProviders.get(i).getServices();

                for (int j = 0; j < servicesOffered.length; j++) {

                    if (servicesOffered[j].equals(serviceName)) {

                        searchedServiceProviders.add((serviceProviders.get(i)));
                    }
                }
            }
        }
        if (searchedServiceProviders.size() == 0) {
            couldNotFindSP();
        }
    }

    protected int timeConvert(String time){

        return (1000*(time.charAt(0)-'0') + 100*(time.charAt(1)-'0') + 10*(time.charAt(5)-'0')+ time.charAt(6)-'0');
    }


    public void searchByHours(String startTime, String endTime, String day) {

        int hoursStartIndex = -1, hoursEndIndex = -1, SPStartTime, SPEndTime, UserStartTime, UserEndTime;

        UserStartTime = timeConvert(startTime);
        UserEndTime = timeConvert(endTime);

        //determine which pair of hours must be taken from the SP hours array
        switch (day) {
            case "Monday":
                hoursStartIndex = 0;
                hoursEndIndex = 1;
                break;

            case "Tuesday":
                hoursStartIndex = 2;
                hoursEndIndex = 3;
                break;

            case "Wednesday":
                hoursStartIndex = 4;
                hoursEndIndex = 5;
                break;

            case "Thursday":
                hoursStartIndex = 6;
                hoursEndIndex = 7;
                break;

            case "Friday":
                hoursStartIndex = 8;
                hoursEndIndex = 9;
                break;

            case "Saturday":
                hoursStartIndex = 10;
                hoursEndIndex = 11;
                break;

            case "Sunday":
                hoursStartIndex = 12;
                hoursEndIndex = 13;
                break;
        }

        //determine if a service provider is in service within the selected hours by user
        for (int i = 0; i < serviceProviders.size(); i++) {

            //only check hours if service provider is in service that day
            if (!(serviceProviders.get(i).getTimes()[hoursStartIndex].equals("__ : __"))) {

                SPStartTime = timeConvert(serviceProviders.get(i).getTimes()[hoursStartIndex]);     // start time as integer from hours array

                if (serviceProviders.get(i).getTimes()[hoursEndIndex].equals("__ : __")) {
                    SPEndTime = 2359;
                } else {
                    SPEndTime = timeConvert(serviceProviders.get(i).getTimes()[hoursEndIndex]);    // end time as integer from hours array
                }

                //add service provider to searched service providers list
                if ((SPStartTime <= UserStartTime) & (SPEndTime >= UserEndTime)) {
                    searchedServiceProviders.add(serviceProviders.get(i));
                }
            }

        }

        if (searchedServiceProviders.size() == 0) {
            couldNotFindSP();
        }
    }
    public void searchByRating (double rating){

        for (int i = 0; i < serviceProviders.size(); i++) {

            if (serviceProviders.get(i).getRating() >= rating) {
                searchedServiceProviders.add(serviceProviders.get(i));
            }
        }
        if (searchedServiceProviders.size() == 0) {
            couldNotFindSP();
        }

    }

    private void couldNotFindSP () {
        Toast.makeText(this, "There are no service providers for your search criteria", Toast.LENGTH_LONG).show();
    }

    public void addPrompt (View view){

        AlertDialog.Builder delMsg = new AlertDialog.Builder(this);
        delMsg.setMessage("Are you sure you want to book this service provider?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        switchToBooking();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int choice){
                        dialog.cancel();
                    }
                });
        AlertDialog delConfirm = delMsg.create();
        delConfirm.setTitle("Book a Service Provider");
        delConfirm.show();
    }

    private void switchToBooking(){
        Intent i = new Intent(this, BookingPage.class);
        i.putExtra("sp",serviceProvider);
        i.putExtra("hO", homeOwner);
        startActivity(i);
    }
}
