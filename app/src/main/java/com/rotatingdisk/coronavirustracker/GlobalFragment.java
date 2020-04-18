package com.rotatingdisk.coronavirustracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class GlobalFragment extends Fragment {

    private static final String STATE_DATA = "state data";
    private static final String SHARED_PREF = "Coronavirus Tracker";


    private void setData(View view, SharedPreferences sharedPreferences){
        System.out.println("0-------------------->"+sharedPreferences.getString(STATE_DATA, "0"));
        String temp[] = (sharedPreferences.getString(STATE_DATA, "0")).split(",");
        if(temp.length<32)
            return;
        int i=0;
        ((TextView)view.findViewById(R.id.i1)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i2)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i3)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i4)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i5)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i6)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i7)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i7)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i8)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i9)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i10)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i11)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i12)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i13)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i14)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i15)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i16)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i17)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i18)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i19)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i20)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i21)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i22)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i23)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i24)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i25)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i26)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i27)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i28)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i29)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i30)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i31)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i32)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i33)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i34)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i35)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i36)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i37)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i38)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i39)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i40)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i41)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i42)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i43)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i44)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i45)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i46)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i47)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i49)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i50)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i51)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i52)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i53)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i54)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i55)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i56)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i57)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i58)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i59)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i60)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i61)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i62)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i63)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i64)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i65)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i66)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i67)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i68)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i69)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i70)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i71)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i72)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i73)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i74)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i75)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i76)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i77)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i78)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i79)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i80)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i81)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i82)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i83)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i84)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i85)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i86)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i87)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i88)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i89)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i90)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i91)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i92)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i93)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i94)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i95)).setText(temp[i++]);
        ((TextView)view.findViewById(R.id.i96)).setText(temp[i]);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_globe, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        setData(view, sharedPreferences);
        return view;
    }
}
