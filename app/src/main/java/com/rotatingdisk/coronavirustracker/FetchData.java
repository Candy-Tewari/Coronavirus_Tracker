package com.rotatingdisk.coronavirustracker;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.*;
import java.net.URL;
import java.net.MalformedURLException;
import java.util.Arrays;

public class FetchData {
    private String s="";
    private long active_cases;
    private long cured;
    private long death;
    private long migrated;
    private String date;
    private long stateData[][] = new long[32][3];
    private boolean isDataExtrtacted = true;
    static String stateName[]={"Andaman and Nicobar Islands", "Andhra Pradesh", "Arunachal Pradesh", "Assam",
            "Bihar", "Chandigarh", "Chhattisgarh", "Delhi", "Goa",
            "Gujarat", "Haryana", "Himachal Pradesh", "Jammu and Kashmir", "Jharkhand",
            "Karnataka", "Kerala", "Ladakh", "Madhya Pradesh", "Maharashtra", "Manipur",
            "Meghalaya", "Mizoram", "Odisha", "Puducherry", "Punjab", "Rajasthan", "Tamil Nadu",
            "Telengana", "Tripura", "Uttarakhand", "Uttar Pradesh", "West Bengal"};
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
    private void downloadWebPage(String webpage)
    {
        try {
            URL url = new URL(webpage);
            BufferedReader readr =
                    new BufferedReader(new InputStreamReader(url.openStream()));
            String line;
            while ((line = readr.readLine()) != null) {
                s=s+line;
            }
            readr.close();
            System.out.println("Successfully Downloaded.");
        }
        catch (MalformedURLException mue) {
            System.out.println("Malformed URL Exception raised");
            isDataExtrtacted=false;
        }
        catch (IOException ie) {
            System.out.println("IOException raised");
            isDataExtrtacted=false;
        }
    }
    private int opening(int index, int times){
        if(s.charAt(index)=='<'){times--;}
        int bracketOne=index;
        while(times-->0)
            bracketOne = s.indexOf("<", bracketOne+1);
        return bracketOne;
    }
    private int closing(int index, int times){
        if(s.charAt(index)=='>'){times--;}
        int bracketOne=index;
        while(times-->0)
            bracketOne = s.indexOf(">", bracketOne+1);
        return bracketOne;
    }
    private void getData(){
        int startIndex=0;
        int timeIndex= s.indexOf(":", s.indexOf("COVID-19 INDIA", startIndex));
        date = s.substring(timeIndex+2, s.indexOf("GMT", startIndex)-1);
        int activeCasesIndex = s.indexOf("Active Status", startIndex);
        startIndex=activeCasesIndex;
        active_cases = Long.parseLong(s.substring(closing(activeCasesIndex, 2)+1, opening(activeCasesIndex, 2)));
        int curedIndex = s.indexOf("Inactive Status", startIndex);
        startIndex=curedIndex;
        cured = Long.parseLong(s.substring(closing(curedIndex, 2)+1, opening(curedIndex, 2)));
        int deathIndex = s.indexOf("Death Status", startIndex);
        startIndex=deathIndex;
        death = Long.parseLong(s.substring(closing(deathIndex, 2)+1, opening(deathIndex, 2)));
        int migratedIndex = s.indexOf("Inactive Status", startIndex);
        startIndex=migratedIndex;
        migrated = Long.parseLong(s.substring(closing(migratedIndex, 2)+1, opening(migratedIndex, 2)));
    }
    private void getTable(){
        int startIndex=0;
        for(int i=0;i<32;i++){
            int index = s.indexOf(stateName[i], startIndex);
            for(int j=0;j<3;j++){
                stateData[i][j]=Long.parseLong(s.substring(closing(index, 2)+1, opening(index, 3)));
                index = opening(index, 3);
            }
            startIndex=index;
        }
    }
    private void printData(){
        System.out.println("Active Status: "+active_cases);
        System.out.println("Cured: "+cured);
        System.out.println("Death: "+death);
        System.out.println("Migrated: "+migrated);
        System.out.print("Date: "+date);
        for(int i=0;i<32;i++){
            for(int j=0;j<3;j++){
                System.out.print(stateData[i][j]+" ");
            }
            System.out.println();
        }
    }
    private void saveDataInSharedPreferences(Context context){
        String StringData = "";
        for(int i=0;i<32;i++){
            for(int j=0;j<3;j++){
                StringData += stateData[i][j]+",";
            }
        }
        StringData = StringData.substring(0, StringData.length()-1);
        System.out.println("-------------->"+StringData);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(ACTIVE_STATUS, active_cases);
        editor.putLong(DISCHARGED, cured);
        editor.putLong(DEATHS, death);
        editor.putLong(MIGRATED, migrated);
        editor.putString(STATE_DATA, StringData);
        editor.putString(LAST_DATE, date);
        editor.apply();
    }
    public boolean collectData(Context context){
        String url = "https://www.mohfw.gov.in/";
        downloadWebPage(url);
        if(!isDataExtrtacted)
            return isDataExtrtacted;
        savePreviousState(context);
        getData();
        getTable();
        saveDataInSharedPreferences(context);
        return isDataExtrtacted;
    }

    private void savePreviousState(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(LAST_UNCHANGED_ACTIVE_CASES, sharedPreferences.getLong(ACTIVE_STATUS, 0));
        editor.putLong(LAST_UNCHANGED_DISCHARGED_CASES, sharedPreferences.getLong(DISCHARGED, 0));
        editor.putLong(LAST_UNCHANGED_DEATHS_CASES, sharedPreferences.getLong(DEATHS, 0));
        editor.putLong(LAST_UNCHANGED_MIGRATED_CASES, sharedPreferences.getLong(MIGRATED, 0));
    }
}
