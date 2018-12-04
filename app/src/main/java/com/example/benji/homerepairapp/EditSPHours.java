package com.example.benji.homerepairapp;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

public class EditSPHours extends Fragment implements TimePickerDialog.OnTimeSetListener {

    EditText cNameBox;
    EditText sDescriptionBox;
    String timeSelected = "";
    Switch licenseSwitch;

    //for bundle
    String SPUsername, SPPassword;


    TextView mo1View, tu1View, we1View, th1View, fr1View, sa1View, su1View, mo2View, tu2View, we2View, th2View, fr2View, sa2View, su2View;

    int moStart, tuStart, weStart, thStart, frStart, saStart, suStart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        View v = inflater.inflate(R.layout.activity_edit_sphours, container, false);

        //get SP username and password from bundle
        Bundle bundle = getArguments();
        String [] spLoginArray = bundle.getStringArray("sp");
        SPUsername = spLoginArray[0];
        SPPassword = spLoginArray[1];

        cNameBox = v.findViewById(R.id.companyName);
        sDescriptionBox = v.findViewById(R.id.comment);

        licenseSwitch = v.findViewById(R.id.licenseSwitch);

        mo1View = (TextView) v.findViewById(R.id.mo1);
        tu1View = (TextView) v.findViewById(R.id.tu1);
        we1View = (TextView) v.findViewById(R.id.we1);
        th1View = (TextView) v.findViewById(R.id.th1);
        fr1View = (TextView) v.findViewById(R.id.fr1);
        sa1View = (TextView) v.findViewById(R.id.sa1);
        su1View = (TextView) v.findViewById(R.id.su1);

        mo2View = (TextView) v.findViewById(R.id.mo2);
        tu2View = (TextView) v.findViewById(R.id.tu2);
        we2View = (TextView) v.findViewById(R.id.we2);
        th2View = (TextView) v.findViewById(R.id.th2);
        fr2View = (TextView) v.findViewById(R.id.fr2);
        sa2View = (TextView) v.findViewById(R.id.sa2);
        su2View = (TextView) v.findViewById(R.id.su2);


        // THIS IS FOR THE FIRST TIME
        mo1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "mo1";
            }
        });

        tu1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "tu1";

            }
        });

        we1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "we1";

            }
        });

        th1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "th1";

            }
        });

        fr1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "fr1";

            }
        });

        sa1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "sa1";

            }
        });

        su1View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "su1";

            }
        });


        // THIS IS FOR THE END TIME
        mo2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(mo1View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "mo2";


            }
        });

        tu2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(tu1View.getText().equals("__ : __")){
                    return;
                }
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "tu2";

            }
        });

        we2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(we1View.getText().equals("__ : __")){
                    return;
                }
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "we2";

            }
        });

        th2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(th1View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "th2";

            }
        });

        fr2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(fr1View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "fr2";

            }
        });

        sa2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(sa1View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "sa2";

            }
        });

        su2View.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                if(su1View.getText().equals("__ : __")){
                    return;
                }

                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getActivity().getSupportFragmentManager(), "time picker");
                timeSelected = "su2";

            }
        });

        return v;
    }

    private boolean isEmptyCName(){
        String cName = cNameBox.getText().toString();

        if(cName.isEmpty()){
            cNameBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    private boolean isEmptysDescription(){
        String sDescription = sDescriptionBox.getText().toString();

        if(sDescription.isEmpty()){
            sDescriptionBox.setError("Field cannot be empty!");
            return false;
        }
        return true;
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {

        String extraMinZero = "", extraHourZero = "";

        if(minute < 10){
            extraMinZero = "0";
        }

        if(hourOfDay < 10){
            extraHourZero = "0";
        }

        switch(timeSelected){
            case "mo1":
                mo1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                moStart = hourOfDay*60 + minute;
                break;

            case "tu1":
                tu1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                tuStart = hourOfDay*60 + minute;
                break;
            case "we1":
                we1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                weStart = hourOfDay*60 + minute;
                break;
            case "th1":
                th1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                thStart = hourOfDay*60 + minute;
                break;
            case "fr1":
                fr1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                frStart = hourOfDay*60 + minute;
                break;
            case "sa1":
                sa1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                saStart = hourOfDay*60 + minute;
                break;
            case "su1":
                su1View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                suStart = hourOfDay*60 + minute;
                break;
            case "mo2":
                if((hourOfDay*60+minute) > moStart) {
                    mo2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
            case "tu2":
                if((hourOfDay*60+minute) > tuStart) {
                    tu2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
            case "we2":
                if((hourOfDay*60+minute) > weStart) {
                    we2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
            case "th2":
                if((hourOfDay*60+minute) > thStart) {
                    th2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
            case "fr2":
                if((hourOfDay*60+minute) > frStart) {
                    fr2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
            case "sa2":
                if((hourOfDay*60+minute) > saStart) {
                    sa2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
            case "su2":
                if((hourOfDay*60+minute) > suStart) {
                    su2View.setText(convertTimeToString(hourOfDay, minute, extraMinZero, extraHourZero));
                }
                break;
        }
    }

    private String convertTimeToString(int hourOfDay, int minute, String extraMinZero, String extraHourZero){

        return extraHourZero + Integer.toString(hourOfDay) + " : " + extraMinZero + Integer.toString(minute);
    }

    public void saveHours(View view){

        DBHandler db = new DBHandler(getActivity());

        if(!isEmptyCName() | !isEmptysDescription()){
            return;
        }

        db.addSPInfo(SPUsername, SPPassword, cNameBox.getText().toString(), sDescriptionBox.getText().toString(), licenseSwitch.isChecked(), mo1View.getText().toString(),
                tu1View.getText().toString(), we1View.getText().toString(), th1View.getText().toString(), fr1View.getText().toString(), sa1View.getText().toString(), su1View.getText().toString(),
                mo2View.getText().toString(), tu2View.getText().toString(), we2View.getText().toString(), th2View.getText().toString(), fr2View.getText().toString(), sa2View.getText().toString(),
                su2View.getText().toString());

        //db.close();

        Intent intent = new Intent (getActivity(), ServiceProviderNav.class);
        startActivity(intent);
    }

}
