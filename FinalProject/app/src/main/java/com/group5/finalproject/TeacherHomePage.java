package com.group5.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RequiresApi(api = Build.VERSION_CODES.O)
public class TeacherHomePage extends AppCompatActivity implements RecyclerViewInterface {

    String[] data = {"Questions"};
    int cntr = 0;

    //Initialize yung ating mga components for onClickListener dito ahhh
    ImageView teacher_newquiz, teacher_profile, btn_dialogcancel; //Para sa pagcreate ng new quiz excel ang bubuksan
    Intent myfileIntent;
    TextView file_path,quiz_link;
    Button btn_createquiz; //Yung mga buttons sa ating create dialog something

    String path; // Dito mai-istore yung file na napili ng user

    SessionManager sessionManager;

    //Custom Arraylist Question Objects
    ArrayList<Questions.QuestionsItem> questions = new ArrayList<>();

    //URL ng quiz php natin kung san ilalagay ang mga yan
    String URL_quiz = "http://e2019cc107group5.000webhostapp.com/finalproject/create_quiz.php"; //URL ng create quiz php file natin sa webhost


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        layouts();
        setContentView(R.layout.activity_teacher_home_page);

        System.setProperty("org.apache.poi.javax.xml.stream.XMLInputFactory", "com.fasterxml.aalto.stax.InputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLOutputFactory", "com.fasterxml.aalto.stax.OutputFactoryImpl");
        System.setProperty("org.apache.poi.javax.xml.stream.XMLEventFactory", "com.fasterxml.aalto.stax.EventFactoryImpl");


        //sessionManager = new SessionManager(this);
        teacher_profile = (ImageView) findViewById(R.id.image_profileteacher);
        teacher_newquiz = (ImageView) findViewById(R.id.teacher_newquiz);

        List<String> items = new LinkedList<>();
        items.add("Questions");

        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ClassesAdapter adapter = new ClassesAdapter(items, this);
        recyclerView.setAdapter(adapter);

        teacher_newquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /***********************8
                 * Tinanggal ko yung startActivityForResult kasi deprecated na siya
                 */

                //Gagamit ako ng bagong startActivityForResult since deprecated na siya hayy kainis
                //Pang open ito ng files natin
                //Say Hello to Activity Results Launcher hayyyy Research pa moreeeee

               myfileIntent = new Intent(Intent.ACTION_GET_CONTENT); //Para makuha ang content
               myfileIntent.setType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"); //Excel only please
               myfileIntent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
               myfileIntent.addCategory(Intent.CATEGORY_OPENABLE); //Para maopen ang file explorer something ganun
               myfileIntent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION); //Read lang poo walang write
                //Then yung ating hayyy yungggg activity results launcher
               excelReader.launch(myfileIntent); //Para daw hindi deprecated tulad nung nakacomment sa taas kaso tinanggal ko na ahahaha

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
                    finish();
                } else {
                    // Edi walang transition
                    startActivity(teacher_profile);
                    finish();
                }
            }
        });
    }

    //  ----------------------------- Excel Import Code -------------------------------
//  --------------- Mga Pre Eto yung Code para sa importing @-Charlie -------------
// ---------------- Dito yung path nung file --------------------------

    private ActivityResultLauncher<Intent> excelReader = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result-> {
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        Uri path_describer = null;
                        if (data != null) {
                            path_describer = data.getData();
                        }
                        //Tingnan ko kung magkakaroon ba ng path using Log
                        assert path_describer != null; //Di ako papayag na null ang path nung excel noo wayy
                        Log.i("ExcelPath: ", path_describer.getPath());
                        //Ito ang pinakamahalaga ang pagreplace sana dun sa document raw kung sakaling meron
                        String source = path_describer.getPath();
                        File excelFile = new File(source); // Sana makuha na ang source absolute na directory or path
                        boolean r = excelFile.setReadOnly();
                        Log.i("Source Path: ",excelFile.toPath().getFileName().toString()); //Eh masaya magdebug sa logcat ehh mas madali
                        String filename = excelFile.getAbsolutePath();
                        Log.i("Excel Filename is ",filename); //Filename nung excel na naglalaman ng questions nandito sanaa ngaa

                        //Then next is ang pagkuha nung file papunta saaa workbook
                        /************************
                         * Dito sa part na ito magpapasok ako ng try catch kasi definitely mag-iinarte ang ating mga streams and workbooks
                         * though sana function nalang yung may parameter na String filename para yun ipasa ko nalang dun sa function pero
                         * nevermind hayy siguro saka nalang??? Gusto ko kasi maayos ang mga functions ko dito ehh
                         * Orrr wag nalang... Bahala na si Batman... Ahahaha
                         *
                         * Hey yowww, Feb 3 na palaaa
                         */
                        try {
                            questions.clear();

                            //May mga tatanggalin lang ako na paepal sa path natin kasi nakakainis ehh two days of debugging
                            if(filename.contains("/document/primary:")){
                                filename = filename.replace("/document/primary:","/storage/emulated/0/");
                                Log.i("Readable: ", filename); //Check na natin kung readable siya
                            }
                            else if(filename.contains("/document/raw:")){
                                filename = filename.replace("/document/raw:","");
                                Log.i("Readable: ", filename); //Check na natin kung readable siya
                            }else if(filename.contains("/document/2616")){
                                filename = filename.replace("/document/2616","/storage/emulated/0/");
                                Log.i("Readable: ", filename); //Check na natin kung readable siya
                            }
                            else if(filename.contains("/document/raw:/storage/6563-3792/")){
                                filename = filename.replace("/document/raw:","");
                            }

                             else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.R) {
                                if(filename.contains("/document/primary:")){
                                    filename = filename.replace("/document/primary:","/storage/emulated/0/");
                                    Log.i("Readable: ", filename); //Check na natin kung readable siya
                                }
                            }

                            //Ipasok yung String dito sa ating file input stream which is actually pwedeng diretso file na
                            FileInputStream inputStream = new FileInputStream(filename);

                            //OPCPackage opc = OPCPackage.open(excelFile.toPath().toFile().getAbsolutePath());
                            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

                            XSSFSheet sheet = workbook.getSheetAt(0); //Sheetname sa baba ahh

                            //Then susunod yung sa row number natin to get yung last row para makuha
                            // sila lahat na ipapasok sa ating custom arraylist object then pag may null value then stop siya
                            // which is obvious naman na ang null ay nandun sa dulo hayyy
                            int nrow = sheet.getLastRowNum();

                            String qlink = generatelink().toString();

                            for(int i = 0; i < nrow+1; i++){
                                XSSFRow row = sheet.getRow(i);
                                XSSFCell cellq = row.getCell(0); //Question
                                XSSFCell cellqa = row.getCell(1); //Question Choice 1
                                XSSFCell cellqb = row.getCell(2); //Question Choice 2
                                XSSFCell cellqc = row.getCell(3); //Question Choice 3
                                XSSFCell cellqd = row.getCell(4); //Question Choice 4
                                XSSFCell cellans = row.getCell(5); //Question Answer Key

                                //Then pasok sila every string natin na maghahawak ng value every for loop incrementation
                                String q = String.valueOf(cellq);
                                String qa = String.valueOf(cellqa);
                                String qb = String.valueOf(cellqb);
                                String qc = String.valueOf(cellqc);
                                String qd = String.valueOf(cellqd);
                                String ans = String.valueOf(cellans);

                                //Then ipapasok natin siya sa ating custom arraylist hayyyyy buhayyyyyyy
                                questions.add(new Questions.QuestionsItem(qlink,q,qa,qb,qc,qd,ans));
                            };

                            openDialog(filename,qlink); //Ipass ko lang dito para maipasok dun sa textview

                        }
                        catch (FileNotFoundException e){
                            e.printStackTrace();
                            Toast.makeText(this, excelFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(this, excelFile.getAbsolutePath(),Toast.LENGTH_LONG).show();
                        }
                    }

                });


// ----------------------------- End of Import Excel Code---------------------------------
    private void layouts(){

        //Lagay ako transition para maganda
        // Dito ko ito ilalagay ayoko sa theme eh
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);

        // Exit Transition natin para maangas
        getWindow().setExitTransition(new Slide());
    }

    private String generatelink() {
        RandomString link = new RandomString();

        //Ang galing nito combination ng String and Integer pinagsama using random
        String result = link.generateAlphaNumeric(6); //Baka ibahin namin yan and dagdagan siguro for more combinations
        Log.i("Generated Link: ", result);

        return result;
    }

    @SuppressLint("NonConstantResourceId")
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(),
                Toast.LENGTH_SHORT).show();
        return true;
    }

    public void openDialog(String filepath, String quizlink){
        Dialog createQuizDialog = new Dialog(this);
        createQuizDialog.setContentView(R.layout.create_quiz_dialog);
        createQuizDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        createQuizDialog.setCanceledOnTouchOutside(false); // para hnd mawala yung dialog kapag na click sa sa labas ng dialog
        createQuizDialog.show();

        file_path = (TextView) createQuizDialog.findViewById(R.id.file_path); //Dito iset Text yung file_path
        quiz_link = (TextView) createQuizDialog.findViewById(R.id.quiz_link); //Dito iset Text yung quizlink
        btn_dialogcancel = (ImageView) createQuizDialog.findViewById(R.id.btn_dialogcancel); //Pang cancel ng dialog need pa ba ito??? hahaha
        btn_createquiz = (Button) createQuizDialog.findViewById(R.id.btn_createquiz); //Andito yung ating function para maipasok sa database

        file_path.setText(filepath);
        quiz_link.setText(quizlink);

        //Pang Cancel ng ating Dialog
        btn_dialogcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createQuizDialog.dismiss();
            }
        });

        btn_createquiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Function para maipasok sa database
                try {
                    createQuestion();
                    createQuizDialog.dismiss();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    // Function para maipasok ang mga questions custom arraylist objects sa database
    public void createQuestion() throws JSONException {

        Questions question = new Questions(questions);

        JsonObject json = new Gson().toJsonTree(question).getAsJsonObject();
        //JsonObject json = new JsonObject(questions);
        Log.d("JSON QUIZ: ",json.toString());

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_quiz,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            /**********************************************
                             *  So ayun na nga gusto ko lang sabihin na nakakaumay mag debug ahahahahhahaha
                             *  Kasi naman ganitooooo soooo naipasok ko na ang quiz data sa ating pinakamamahal na database
                             *
                             *  Ohh yeahhh ahahha hooray bruhh ahahahhahaha inabot ako ng ilang weeks para lang dun
                             *
                             *  Successful ko siyang nadebug sa php side ahahahahha
                             *
                             *  So by implementing the GSON library sa ating custom object nagawa ko siyang
                             *  iconvert sa isang magandang json object then by using string request gumawa ako ng
                             *  map ng isang key with multiple json object
                             *
                             *  By doing this, madali ko siyang maaaccess sa ating php by using the file_get_content
                             *  para makuha to make sure na ang raw data ay makukuha ng buo and
                             *  $_POST function ng php since may key siya sa ating params hashmap na nasa ilalim
                             *
                             *  Then by using foreach loop every index ng object natin na naconvert sa isang
                             *  associative using json_decode ay maipapasok na siya sa ating database table
                             *******************************************/

                            if(success.equals("1")){
                                //Successful Insertion ohh yeahhh ahahhahhahaha whoooooo hoorayy kainiss
                                Toast.makeText(TeacherHomePage.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();

                            }
                            else{
                                Toast.makeText(TeacherHomePage.this,jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        }
                        catch(JSONException e){
                                Toast.makeText(TeacherHomePage.this,"Error Inserting into database",Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error instanceof NetworkError) {
                            Toast.makeText(TeacherHomePage.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ServerError) {
                            Toast.makeText(TeacherHomePage.this,"The server could not be found. Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof AuthFailureError) {
                            Toast.makeText(TeacherHomePage.this,"Cannot connect to Internet...Please check your connection!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof ParseError) {
                            Toast.makeText(TeacherHomePage.this,"Parsing error! Please try again after some time!!",Toast.LENGTH_SHORT).show();
                        } else if (error instanceof TimeoutError) {
                            Toast.makeText(TeacherHomePage.this,"Connection TimeOut! Please check your internet connection",Toast.LENGTH_SHORT).show();
                        } else{
                            Toast.makeText(TeacherHomePage.this,"Login Error!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }){
            @Override
            protected Map<String,String> getParams() throws AuthFailureError{
                Map<String,String> params = new HashMap<>();
                params.put("quizzes",json.toString()); //Ang key natin for $_POST ay "quizzes"
                Log.d("PARAMS: ",params.get("quizzes"));
                return params;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest); //Pasok natin sa ating queue para gumana siya
    }

// methods para sa intent to quiz ng recycleView
    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(TeacherHomePage.this,TeacherQuizUI.class);
        startActivity(intent);
        finish();
    }
}