package com.group5.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class TeacherClasses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_classes);
    }

//  ----------------------------- Excel Import Code -------------------------------
//  --------------- Mga Pre Eto yung Code para sa importing @-Charlie -------------
    int requestcode = 1;

    public void onActivityResult(int requestcode, int resultcode, Intent data){

        super.onActivityResult(requestcode,resultcode,data);
        Context context = getApplicationContext();

        if(requestcode == requestcode && resultcode == Activity.RESULT_OK){


            if(data == null){

                return;
            }
            Uri uri = data.getData();
            Toast.makeText(context,"The File was Imported and Ready!",Toast.LENGTH_SHORT).show();


        }

    }
    public void excelImport(View view){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        startActivityForResult(intent,requestcode);


    }
// ----------------------------- End of Import Excel Code---------------------------------

}