package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    /*
        Ang need lang natin is imagebutton na login para makapasok sa susunod na activity base sa
        kung sino ang nag sign up and kasama yung textview na signup para makapasok sa sign up activity
        and lastly yung dalawang editText para makuha natin yung laman nung string value na nandun
        so bale pag tama ang username and password makakapaglogin tayo, pag hindi gagamit tayo ng toast
        to notify the user na mali yung credentials na nilagay niya

       Ps. Hindi na natin need galawin yung ibang components such as yung mga textview na andun
       or yung imageview na logo natin

     */

    TextView tv_signup; //Ito yung sign up textview na pipindutin para makapunta sa next activity
    ImageButton login; //Yung image button na may pangalan na Login
    EditText et_username,et_password; //Yung dalawang parang textbox natin

    Quiview quiview = new Quiview(); //Yung Quiview Class na andito student and teachers data

    String URL= "http://e2019cc107group5.000webhostapp.com/"; //URL ng database natin sa webhost


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Need natin kunin yung id dun sa xml file natin
        login = (ImageButton) findViewById(R.id.login); //ImageButton natin
        tv_signup = (TextView) findViewById(R.id.tv_signup); //TextView natin
        et_username = (EditText) findViewById(R.id.et_username); //EditText ng Username natin
        et_password = (EditText) findViewById(R.id.et_password); //EditText ng Password natin

        //Attempted Login pero ichecheck muna natin kung tama ba ang mga credentials base sa nandun
        //sa database natin
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Hindi ito yung final na logic ahh
                Toast toast = new Toast(getApplicationContext());

                //Pasok laman ng edit text sa ating getters and setters
                quiview.setLoginUsername(et_username.getText().toString()); //Username na tinype
                quiview.setLoginPassword(et_password.getText().toString()); //Password na tinype

                //Ah basta kapag may laman ang username and password go na pag wala edi yung else
                //Pero seriously, sa database tayo kukuha nung laman hayyy buhay
                if(!quiview.getLoginUsername().contentEquals("") && !quiview.getLoginPassword().contentEquals("")){
                    toast.setText("Yehey nakalogin yan");
                }
                else{
                    toast.setText("Sad Life Sign up ka muna");
                }

            }
        });

    }


}