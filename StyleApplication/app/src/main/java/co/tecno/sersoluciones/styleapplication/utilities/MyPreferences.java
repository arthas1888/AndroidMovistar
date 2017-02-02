package co.tecno.sersoluciones.styleapplication.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import java.security.PrivateKey;

public class MyPreferences {

    private static final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "MyData";
    private static final String IS_LOGIN = "is_login";
    private static final String USERNAME = "username";
    private Context context;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    public MyPreferences(Context context){
        this.context = context;
        pref = this.context.getSharedPreferences(PREF_NAME,
                PRIVATE_MODE);
        editor = pref.edit();
    }

    public boolean isLogin(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void setStateLogin(boolean state){
        editor.putBoolean(IS_LOGIN, state);
        editor.apply();
    }

    public String getUsername(){
        return pref.getString(USERNAME, "");
    }

    public void setUsername(String username){
        editor.putString(USERNAME, username);
        editor.apply();
    }
}
