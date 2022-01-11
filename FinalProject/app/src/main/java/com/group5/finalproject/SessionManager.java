package com.group5.finalproject;

import android.content.Context;
import android.content.SharedPreferences;

/*******************************************************************
 *
 *  Ito ang ating SessionManager Class na may Shared preference para mapigilan
 *  ang pag logout ng ating session kapag naclose ang ating app unless mag logout tayo :)
 *
 *  PAra siyang facebook or messenger or any social media apps na kapag di pinindot ang
 *  logout di mawawala ang session ng account
 *
 *  Kaya ako gumawa ng class para tawagin ko nalang ito since two users naman students
 *  and teachers
 *
 *  Ayus diba? :) :) :)
 *
 ******************************************************************/

public class SessionManager {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public Context context;
    int PRIVATE_MODE = 0;

    //Shared preference file na kusang na create kapag wala pa sa shared pref folders
    //Something like that ahhahahaa

    private static final String PREF_NAME= "LOGIN";

    //Keys ng login and mga values na need natin
    /*
        Ginawa ko siyang parang getters and setters kasi medyo hassle ipasok sa hashmap
     */
    public static final String KEY_LOGIN = "key_login";
    public static final String KEY_USER = "key_user";
    public static final String KEY_USERNAME = "key_username";
    public static final String KEY_EMAIL = "key_email";
    public static final String KEY_SURNAME = "key_surname";
    public static final String KEY_FIRSTNAME = "key_firstname";

    //Yung constructor natin
    public SessionManager(Context context){
        sharedPreferences = context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor = sharedPreferences.edit();
        editor.apply();
    }

    //Boolean login and String user either teacher or student
    public void setLogin(boolean login){
        editor.putBoolean(KEY_LOGIN,login);
        editor.apply();
    }
    public boolean getLogin(){
        return sharedPreferences.getBoolean(KEY_LOGIN, false);
    }

    //Either teacher or student
    public void setUser(String user){
        editor.putString(KEY_USER,user);
        editor.apply();
    }
    public String getUser(){
        return sharedPreferences.getString(KEY_USER, "");
    }

    //And yung mga need nating values
    public void setUsername(String username){
        editor.putString(KEY_USERNAME,username);
        editor.apply();
    }
    public String getUsername(){
        return sharedPreferences.getString(KEY_USERNAME,"");
    }

    public void setEmail(String email){
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }
    public String getEmail(){
        return sharedPreferences.getString(KEY_EMAIL,"");
    }

    public void setSurname(String surname){
        editor.putString(KEY_SURNAME,surname);
        editor.apply();
    }
    public String getSurname(){
        return sharedPreferences.getString(KEY_SURNAME,"");
    }

    public void setFirstname(String firstname){
        editor.putString(KEY_FIRSTNAME,firstname);
        editor.apply();
    }
    public String getFirstname(){
        return sharedPreferences.getString(KEY_FIRSTNAME,"");
    }
}
