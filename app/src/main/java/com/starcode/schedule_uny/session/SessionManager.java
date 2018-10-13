package com.starcode.schedule_uny.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    public static final String SP_CONTENTTYPE="spContentType";
//    public static final String SP_ACCEPT="spAccept";
    public static final String SP_AUTHORIZATION="spAuthorization";
    public static final String SP_SESIONLOGIN="spTrue";

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

    public  Boolean getSpSesionlogin() {
        return sp.getBoolean(SP_SESIONLOGIN, false);
    }

}
