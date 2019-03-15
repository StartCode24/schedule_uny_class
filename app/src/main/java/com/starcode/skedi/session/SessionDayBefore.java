package com.starcode.skedi.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionDayBefore {
    public static final String SP_CONTENTYPE="SpDayBefore";
    public static final String SP_SENIN="spsenin";
    public static final String SP_SENIN_TIME="spsenin_time";
    public static final String SP_SELASA="spselasa";
    public static final String SP_SELASA_TIME="spselasa_time";
    public static final String SP_RABU="sprabu";
    public static final String SP_RABU_TIME="sprabu_time";
    public static final String SP_KAMIS="spkamis";
    public static final String SP_KAMIS_TIME="spkamis_time";
    public static final String SP_JUMAT="spjuamt";
    public static final String SP_JUMAT_TIME="spjumat_time";
    public static final String SP_SABTU="spsabtu";
    public static final String SP_SABTU_TIME="spsabtu_time";
    public static final String SP_MINGGU="spminggu";
    public static final String SP_MINGGU_TIME="spminggu_time";



    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SessionDayBefore(Context context){
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

    public int getSpIdSenin() {
        return sp.getInt(SP_SENIN,0);
    }
    public int getSpIdSlasa() {
        return sp.getInt(SP_SELASA,0);
    }
    public int getSpIdRabu() {
        return sp.getInt(SP_RABU,0);
    }
    public int getSpIdKamis() {
        return sp.getInt(SP_KAMIS,0);
    }
    public int getSpIdJumat() {
        return sp.getInt(SP_JUMAT,0);
    }
    public int getSpIdSabtu() {
        return sp.getInt(SP_SABTU,0);
    }
    public int getSpIdminggu() {
        return sp.getInt(SP_MINGGU,0);
    }

    public  String getSpSeninTime() {
        return sp.getString(SP_SENIN_TIME, "OFF");
    }
    public  String getSpSelasaTime() {
        return sp.getString(SP_SELASA_TIME, "OFF");
    }
    public  String getSpRabuTime() {
        return sp.getString(SP_RABU_TIME, "OFF");
    }
    public  String getSpKamisTime() {
        return sp.getString(SP_KAMIS_TIME, "OFF");
    }
    public  String getSpJumatTime() {
        return sp.getString(SP_JUMAT_TIME, "OFF");
    }
    public  String getSpSabtuTime() {
        return sp.getString(SP_SABTU_TIME, "OFF");
    }
    public  String getSpMingguTime() {
        return sp.getString(SP_MINGGU_TIME, "OFF");
    }

}
