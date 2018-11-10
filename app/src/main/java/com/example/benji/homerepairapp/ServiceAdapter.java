package com.example.benji.homerepairapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ServiceAdapter extends ArrayAdapter<Service> {

    private final Context context;
    private final ArrayList<Service> services;

    public ServiceAdapter(Context context, ArrayList<Service> values){
        super(context, R.layout.service_layout, values);
        this.context = context;
        this.services = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup serviceList){;

        Service currentService = services.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.service_layout, serviceList, false);

        if (rowView == null){
            rowView = inflater.inflate(R.layout.service_layout, serviceList, false);
        }

        TextView service = (TextView) rowView.findViewById(R.id.serviceNameItem);
        TextView hRate = (TextView) rowView.findViewById(R.id.hourlyRateItem);

        service.setText(currentService.getServiceName());
        hRate.setText(Double.toString(currentService.getRate()));

        return rowView;

    }
}
