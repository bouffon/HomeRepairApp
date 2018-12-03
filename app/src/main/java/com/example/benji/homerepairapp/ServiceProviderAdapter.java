package com.example.benji.homerepairapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceProviderAdapter extends ArrayAdapter<ServiceProvider> {


    private final Context context;
    private final ArrayList<ServiceProvider> serviceProviders;

    public ServiceProviderAdapter(Context context, ArrayList<ServiceProvider> values){
        super(context, R.layout.service_provider_layout, values);
        this.context = context;
        this.serviceProviders = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup serviceProviderList){;

        ServiceProvider currentSP = serviceProviders.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.service_provider_layout, serviceProviderList, false);

        if (rowView == null){
            rowView = inflater.inflate(R.layout.service_provider_layout, serviceProviderList, false);
        }

        TextView serviceProviderNames = (TextView) rowView.findViewById(R.id.serviceProviderInfo);
        TextView company = (TextView) rowView.findViewById(R.id.companyName);

        serviceProviderNames.setText(currentSP.getfName() + " " + currentSP.getlName());
        company.setText(currentSP.getCompany());

        return rowView;

    }
}
