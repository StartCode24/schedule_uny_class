package com.starcode.skedi.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionMinuteBefore {
    public static final String SP_CONTENTYPE="SpDayBefore";
    public static final String SP_ALARM_Active="spalarm_active";
    public static final String SP_ALARM_TIME="spalarm_time";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SessionMinuteBefore(Context context){
        sp=context.getSharedPreferences(SP_CONTENTYPE,Context.MODE_PRIVATE);
        spEditor=sp.edit();
    }
    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP,value);
        spEditor.commit();
    }
    public void saveSPLong(String keySP, long value){
        spEditor.putLong(keySP,value);
        spEditor.commit();
    }
    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP,value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP,value);
        spEditor.commit();
    }
    public  String getSpContenttype() {
        return sp.getString(SP_CONTENTYPE, "");
    }
    public  String getSpAlarmTime() {
        return sp.getString(SP_ALARM_TIME, "OFF");
    }
    public  int getSpAlarmActive() {
        return sp.getInt(SP_ALARM_Active, 0);
    }

}
