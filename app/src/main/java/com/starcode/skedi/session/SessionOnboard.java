package com.starcode.skedi.session;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionOnboard {
    public static final String SP_CONTENTTYPE="spContentType";
    //    public static final String SP_ACCEPT="spAccept";
    public static final String SP_ONBOARD="sponboard";
    public static final String SP_SESIONCEK="spTrue";

    SharedPreferences sp;
    SharedPreferences.Editor spEditor;

    public SessionOnboard(Context context){
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

    public  String getSpsessionCek() {
        return sp.getString(SP_SESIONCEK, "");
    }

    public  Boolean getSpSesionOnboard() {
        return sp.getBoolean(SP_ONBOARD, false);
    }
}
