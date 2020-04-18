package com.rotatingdisk.coronavirustracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.lang.StrictMath.ceil;

public class HomeFragment extends Fragment {

    private static final String SHARED_PREF = "Coronavirus Tracker";
    private static final String ACTIVE_STATUS = "active cases";
    private static final String DISCHARGED = "discharged cases";
    private static final String DEATHS = "deaths";
    private static final String MIGRATED = "migrated";
    private static final String STATE_DATA = "state data";
    private static final String LAST_DATE = "last date";
    private static final String LAST_UNCHANGED_ACTIVE_CASES = "last unchanged";
    private static final String LAST_UNCHANGED_DISCHARGED_CASES = "discharged unchanged";
    private static final String LAST_UNCHANGED_DEATHS_CASES = "death unchanged";
    private static final String LAST_UNCHANGED_MIGRATED_CASES = "migrated unchanged";

    private void getTheClockStarted(final View view){
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy, HH:mm:ss");
        formatter.setLenient(false);
        long milliseconds=System.currentTimeMillis();
        final long[] startTime = new long[1];
        String endTime = "03.05.2020, 00:00:00";
        long diff=0;

        Date endDate;
        try {
            endDate = formatter.parse(endTime);
            milliseconds = endDate.getTime();

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        startTime[0] = System.currentTimeMillis();


        CountDownTimer countDownTimer = new CountDownTimer(milliseconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                startTime[0] = startTime[0] - 1;
                Long serverUptimeSeconds =
                        (millisUntilFinished - startTime[0]) / 1000;

                String daysLeft = String.format("%d", serverUptimeSeconds / 86400);
                if(daysLeft.length()==1)
                    daysLeft="0"+daysLeft;
                ((TextView)view.findViewById(R.id.days)).setText(daysLeft);

                String hoursLeft = String.format("%d", (serverUptimeSeconds % 86400) / 3600);
                if(hoursLeft.length()==1)
                    hoursLeft="0"+hoursLeft;
                ((TextView)view.findViewById(R.id.hours)).setText(hoursLeft);

                String minutesLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) / 60);
                if(minutesLeft.length()==1)
                    minutesLeft="0"+minutesLeft;
                ((TextView)view.findViewById(R.id.mins)).setText(minutesLeft);

                String secondsLeft = String.format("%d", ((serverUptimeSeconds % 86400) % 3600) % 60);
                if(secondsLeft.length()==1)
                    secondsLeft="0"+secondsLeft;
                ((TextView)view.findViewById(R.id.secs)).setText(secondsLeft);
            }
            @Override
            public void onFinish() {
            }
        }.start();
    }

    private static String formatDate(String s){
        if(s.contains("January"))
            s=s.replace("January", "01");
        else if(s.contains("February"))
            s=s.replace("February", "02");
        else if(s.contains("March"))
            s=s.replace("March", "03");
        else if(s.contains("April"))
            s=s.replace("April", "04");
        else if(s.contains("May"))
            s=s.replace("May", "05");
        else if(s.contains("June"))
            s=s.replace("June", "06");
        else if(s.contains("July"))
            s=s.replace("July", "07");
        else if(s.contains("August"))
            s=s.replace("August", "08");
        else if(s.contains("September"))
            s=s.replace("September", "09");
        else if(s.contains("October"))
            s=s.replace("October", "10");
        else if(s.contains("November"))
            s=s.replace("November", "11");
        else
            s=s.replace("December", "12");
        return s;
    }

    /*private static void setIncrementAndDecrement(View view, SharedPreferences sharedPreferences){

        if(sharedPreferences.getLong(LAST_UNCHANGED_ACTIVE_CASES, 0)==0)
            return;

        //Active Cases
        long before = sharedPreferences.getLong(LAST_UNCHANGED_ACTIVE_CASES, 0);
        long after = sharedPreferences.getLong(ACTIVE_STATUS, 0);
        double diff = after-before;
        if(diff<=0){
            ((ImageView)view.findViewById(R.id.active_Arrow)).setImageResource(R.drawable.decreased);
            diff*=-1;
            ((TextView)view.findViewById(R.id.active_Text)).setText(""+ceil(((diff/before)*100)) + "%");
        }
        else{
            ((ImageView)view.findViewById(R.id.active_Arrow)).setImageResource(R.drawable.increase);
            ((TextView)view.findViewById(R.id.active_Text)).setText(""+ceil(((diff/before)*100)) + "%");
        }

        //Death Cases
        before = sharedPreferences.getLong(LAST_UNCHANGED_DEATHS_CASES, 0);
        after = sharedPreferences.getLong(DEATHS, 0);
        diff = after - before;
        if(diff<=0){
            ((ImageView)view.findViewById(R.id.deathArrow)).setImageResource(R.drawable.decreased);
            diff*=-1;
            ((TextView)view.findViewById(R.id.deathText)).setText(""+ceil(((diff/before)*100)) + "%");
        }
        else{
            ((ImageView)view.findViewById(R.id.deathArrow)).setImageResource(R.drawable.increase);
            ((TextView)view.findViewById(R.id.deathText)).setText(""+ceil(((diff/before)*100)) + "%");
        }

        //Migrated Cases
        before = sharedPreferences.getLong(LAST_UNCHANGED_MIGRATED_CASES, 0);
        after = sharedPreferences.getLong(MIGRATED, 0);
        diff = after - before;
        if(diff<=0){
            ((ImageView)view.findViewById(R.id.migratedArrow)).setImageResource(R.drawable.decreased);
            diff*=-1;
            ((TextView)view.findViewById(R.id.migratedText)).setText(""+ceil(((diff/before)*100)) + "%");
        }
        else{
            ((ImageView)view.findViewById(R.id.migratedArrow)).setImageResource(R.drawable.increase);
            ((TextView)view.findViewById(R.id.migratedText)).setText(""+ceil(((diff/before)*100)) + "%");
        }

        //Discharged
        before = sharedPreferences.getLong(LAST_UNCHANGED_DISCHARGED_CASES, 0);
        after = sharedPreferences.getLong(DISCHARGED, 0);
        diff = after-before;
        if(diff<=0){
            ((ImageView)view.findViewById(R.id.dischargedArrow)).setImageResource(R.drawable.decreased);
            diff*=-1;
            ((TextView)view.findViewById(R.id.dischargedText)).setText(""+ceil(((diff/before)*100)) + "%");
        }
        else{
            ((ImageView)view.findViewById(R.id.dischargedArrow)).setImageResource(R.drawable.increase);
            ((TextView)view.findViewById(R.id.dischargedText)).setText(""+ceil(((diff/before)*100)) + "%");
        }
    }*/

    public static void updateValues(SharedPreferences sharedPreferences, View view){
        ((TextView)view.findViewById(R.id.active_cases_field)).setText(""+sharedPreferences.getLong(ACTIVE_STATUS, 0));
        ((TextView)view.findViewById(R.id.discharged_field)).setText(""+sharedPreferences.getLong(DISCHARGED, 0));
        ((TextView)view.findViewById(R.id.deaths_field)).setText(""+sharedPreferences.getLong(DEATHS, 0));
        ((TextView)view.findViewById(R.id.migrated_field)).setText(""+sharedPreferences.getLong(MIGRATED, 0));
        String date = sharedPreferences.getString(LAST_DATE, "Reversed");
        if(date.equals("Reversed")) {
            ((TextView) view.findViewById(R.id.last_updated)).setText("Last updated infinite hour ago");
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy, HH:mm");
        date = formatDate(date);
        long time=0;
        long mins=0, hours=0;
        try{
            Date dateeee = new Date();
            String nowTime = formatter.format(dateeee);
            System.out.println("------------------------->Now Time: "+nowTime);
            System.out.println("------------------------->Then Time: "+date);
            time = (formatter.parse(nowTime).getTime() - formatter.parse(date).getTime()) /1000;
            mins = ((time% 86400) % 3600) / 60;
            hours = (time%86400)/3600;
        }
        catch (Exception e){}
        ((TextView) view.findViewById(R.id.last_updated)).setText("Last updated "+ (time%86400)/3600 +" hour ago");

        System.out.println("Old active: "+sharedPreferences.getLong(LAST_UNCHANGED_ACTIVE_CASES, -6));
        System.out.println("now active: "+sharedPreferences.getLong(ACTIVE_STATUS, 0));
        //Checking and rectifying the arrows
            //if(mins<1 && hours==0)
                //setIncrementAndDecrement(view, sharedPreferences);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getTheClockStarted(view);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        updateValues(sharedPreferences, view);
        return view;
    }
}
