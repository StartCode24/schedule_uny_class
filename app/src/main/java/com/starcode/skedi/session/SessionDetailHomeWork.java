package com.starcode.skedi.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionDetailHomeWork {
    public static final String SP_CONTENTYPE="spDetailHomeWork";
    public static final String SP_IDHOMEWORK="spid";
    public static final String SP_IDSCHEDULE2="spid2";
    public static final String SP_RELOADH="SpReloadH";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SessionDetailHomeWork(Context context){
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
    //    public  String getSpIdSchedule() {
//        return sp.getString(SP_IDSCHEDULE, "");
//    }
    public long getSpIdHomeWork() {
        return sp.getLong(SP_IDHOMEWORK,-1);
    }

    public int getSpReloadH(){
        return  sp.getInt(SP_RELOADH,0);
    }

}
