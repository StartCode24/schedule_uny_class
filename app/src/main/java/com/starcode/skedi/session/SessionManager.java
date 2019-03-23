package com.starcode.skedi.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String SP_CONTENTTYPE="spContentType";
//    public static final String SP_ACCEPT="spAccept";
    public static final String SP_AUTHORIZATION="spAuthorization";
    public static final String SP_SESIONLOGIN="spTrue";
    public static final String SP_HOURS="spHours";
    public static final String SP_MINUTE="spMinute";
    public static final String SP_RELOADSM="SpReloadSM";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SessionManager(Context context){
        sp= context.getSharedPreferences(SP_CONTENTTYPE,Context.MODE_PRIVATE);
        spEditor=sp.edit();
    }

    public void saveSPString(String keySP, String value){
        spEditor.putString(keySP,value);
        spEditor.commit();
    }

    public void saveSPInt(String keySP, int value){
        spEditor.putInt(keySP,value);
        spEditor.commit();
    }

    public void saveSPBoolean(String keySP, boolean value){
        spEditor.putBoolean(keySP,value);
        spEditor.commit();
    }

    public  String getSpContenttype() {
         return sp.getString(SP_CONTENTTYPE, "");
    }

//    public  String getSpAccept() {
//        return sp.getString(SP_ACCEPT, "");
//    }

    public  String getSpAuthorization() {
        return sp.getString(SP_AUTHORIZATION, "");
    }
    public  int getSpHours() {
        return sp.getInt(SP_HOURS, 0);
    }
    public  int getMinute() {
        return sp.getInt(SP_MINUTE, 0);
    }

    public  Boolean getSpSesionlogin() {
        return sp.getBoolean(SP_SESIONLOGIN, false);
    }
    public int getSpReloadSM(){
        return  sp.getInt(SP_RELOADSM,0);
    }

}
