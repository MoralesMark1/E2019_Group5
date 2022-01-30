package com.group5.finalproject;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TeacherHomePage extends AppCompatActivity {

    String[] data = {"Mobile Programming", "Micro-controller", "Software Engineering"};
    int cntr = 0;

    //Initialize yung ating mga components for onClickListener dito ahhh
    ImageButton teacher_profile;
    ImageView teacher_newquiz; //Para sa pagcreate ng new quiz excel ang bubuksan
    Intent myfileIntent;
    String path; // Dito mai-istore yung file na napili ng user

    SessionManager sessionManager;

    //Custom Arraylist Question Objects
    ArrayList<Questions> questions = new ArrayList<>();

    //URL ng quiz php natin kung san ilalagay ang mga yan


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_teacher_home_page);

        //sessionManager = new SessionManager(this);
        teacher_profile = (ImageButton) findViewById(R.id.image_profileteacher);
        teacher_newquiz = (ImageView) findViewById(R.id.teacher_newquiz);

        List<String> items = new LinkedList<>();
        items.add("Code here");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassesAdapter adapter = new ClassesAdapter(items);
        recyclerView.setAdapter(adapter);

        teacher_newquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /*
                //newQuiz function po ditoooo
                myfileIntent = new Intent(Intent.ACTION_GET_CONTENT);
                myfileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); // Specific FIle format
                myfileIntent.addCategory(Intent.CATEGORY_OPENABLE); // to open the file
                startActivityForResult(myfileIntent, 10);
                */

                //Gagamit ako ng bagong startActivityForResult since deprecated na siya hayy kainis
                //Pang open ito ng files natin
                //Say Hello to Activity Results Launcher hayyyy Research pa moreeeee

               myfileIntent = new Intent(Intent.ACTION_GET_CONTENT);
               myfileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
               myfileIntent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
               myfileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //Read lang poo walang write
                //Then yung ating hayyy yungggg activity results launcher
                excelReader.launch(myfileIntent); //Para daw hindi deprecated tulad nung nakacomment sa taas

            }
        });

        teacher_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent teacher_profile = new Intent(TeacherHomePage.this, MyProfileTeacher.class);

                //Kung ang build version daw ay more than lollipop edi goods sa transition sabi ni docs ehh
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Activity transition dito hehehe
                    startActivity(teacher_profile, ActivityOptions.makeSceneTransitionAnimation(TeacherHomePage.this).toBundle());
                } else {
                    // Edi walang transition
                    startActivity(teacher_profile);
                }
            }
        });

    }

    //  ----------------------------- Excel Import Code -------------------------------
//  --------------- Mga Pre Eto yung Code para sa importing @-Charlie -------------
// ---------------- Dito yung path nung file --------------------------
    /************** D E P R E C A T E D *****************************************
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 10:
                if (resultCode == RESULT_OK) {
                   // path = data.getData().getPath(); // dito lang naka store yung file

                    assert data != null;
                    Uri uri = data.getData();
                    File file = new File(uri.getPath());
                    path = file.getAbsolutePath();



                }
                break;
        }
        *******************************************************/
    private ActivityResultLauncher<Intent> excelReader = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri path_describer = data.getData();
                        //Tingnan ko kung magkakaroon ba ng path using Log
                        Log.d("ExcelPath: ", path_describer.getPath());
                        String source = path_describer.getPath();
                        File excelFile = new File(source); // Sana makuha na ang source absolute na directory or path
                        Log.d("Source Path: ",excelFile.toString()); //Eh masaya magdebug sa logcat ehh mas madali
                        String filename = path_describer.getLastPathSegment();
                        Log.d("Excel Filename is ",filename); //Filename nung excel na naglalaman ng questions nandito sanaa ngaa

                        //Then next is ang pagkuha nung file papunta saaa workbook
                        /************************
                         * Dito sa part na ito may function sa baba siguro
                         * gagmaitin ko yun then ipapass ko yung File excel as parameter para makuha natin
                         * since dito ang excelFile is may source path na siya which is sana... Absolute na
                         */
                        try {
                            FileInputStream excelStream = new FileInputStream(excelFile.getAbsolutePath());
                            //Hahaha pwede pala mag absolute path mismo dun sa FileInputStream ahhahaha nakakabaliw

                            XSSFWorkbook workbook = new XSSFWorkbook(excelStream);

                            XSSFSheet sheet = workbook.getSheetAt(0); //Sheetname sa baba ahh

                            //Then susunod yung sa row number natin to get yung last row para makuha
                            // sila lahat na ipapasok sa ating custom arraylist object then pag may null value then stop siya
                            // which is obvious naman na ang null ay nandun sa dulo hayyy
                            int nrow = sheet.getLastRowNum();

                            for(int i = 0; i < nrow+1; i++){
                                XSSFRow row = sheet.getRow(i);
                                XSSFCell cellq = row.getCell(0); //Question
                                XSSFCell cellqa = row.getCell(0); //Question Choice 1
                                XSSFCell cellqb = row.getCell(0); //Question Choice 2
                                XSSFCell cellqc = row.getCell(0); //Question Choice 3
                                XSSFCell cellqd = row.getCell(0); //Question Choice 4
                                XSSFCell cellans = row.getCell(0); //Question Answer Key

                                //Then pasok sila every string natin na maghahawak ng value every for loop incrementation
                                String q = String.valueOf(cellq);
                                String qa = String.valueOf(cellqa);
                                String qb = String.valueOf(cellqb);
                                String qc = String.valueOf(cellqc);
                                String qd = String.valueOf(cellqd);
                                String ans = String.valueOf(cellans);

                                //Then ipapasok natin siya sa ating custom arraylist hayyyyy buhayyyyyyy
                                questions.add(new Questions(q,qa,qb,qc,qd,ans));

                                for(Questions que: questions){

                                    Log.i("TAG",que.getQuestion() + " | "
                                            + que.getChoiceA() + " | " + que.getChoiceB() + " | "
                                            + que.getChoiceC() + " | " + que.getChoiceD() + " | "
                                            + que.getAnswer()
                                    );
                                }
                            }
                        }
                        catch (FileNotFoundException e){
                            e.printStackTrace();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });

// ----------------------------- End of Import Excel Code---------------------------------

        //Function for getting the excel file and everything inside it
        //private void newQuiz(){
    /*
        //Excel path po dito base dun sa onActivityResult
        FileInputStream inputStream = new FileInputStream(path);

        //Ipapasok ko dito sa XSSF ang file input stream para maacess yung laman loob
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        //Then yung sheet index yung A1 sa excel
        XSSFSheet sheet = workbook.getSheetAt(0); //Sheet sa baba nung excel which is awkward hayy

        //Then susunod yung sa row number natin to get yung last row para makuha
        // sila lahat na ipapasok sa ating custom arraylist object then pag may null value then stop siya
        // which is obvious naman na ang null ay nandun sa dulo hayyy
        int nrow = sheet.getLastRowNum();

        //+1 daw kasi zero based siya
        for(int i = 0;i < nrow+1;i++){

            XSSFRow row = sheet.getRow(i);
            XSSFCell cellq = row.getCell(0); //Question
            XSSFCell cellqa = row.getCell(0); //Question Choice 1
            XSSFCell cellqb = row.getCell(0); //Question Choice 2
            XSSFCell cellqc = row.getCell(0); //Question Choice 3
            XSSFCell cellqd = row.getCell(0); //Question Choice 4
            XSSFCell cellans = row.getCell(0); //Question Answer Key

            //Then pasok sila every string natin na maghahawak ng value every for loop incrementation
            String q = String.valueOf(cellq);
            String qa = String.valueOf(cellqa);
            String qb = String.valueOf(cellqb);
            String qc = String.valueOf(cellqc);
            String qd = String.valueOf(cellqd);
            String ans = String.valueOf(cellans);

            //Then ipapasok natin siya sa ating custom arraylist hayyyyy buhayyyyyyy
            questions.add(new Questions(q,qa,qb,qc,qd,ans));

            //Ang pagkuha natin ay by row kaya ayan then next is kung paano siya ipapasok sa database

            /*******
             *  Checking lang natin print ko laman nung arraylist sa ano ko logcat
             *  parang System.out.println siya ahahhaahhaha :) :) :)
             *
            for(Questions que: questions){

                Log.i("TAG",que.getQuestion() + " | "
                        + que.getChoiceA() + " | " + que.getChoiceB() + " | "
                        + que.getChoiceC() + " | " + que.getChoiceD() + " | "
                        + que.getAnswer()
                );
            }*/
    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }
}