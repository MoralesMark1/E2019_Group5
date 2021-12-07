package com.example.menuexample;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // NOTE:
        // We only created 2 buttons since OPTION MENU doesn't need it.
        //

// CODE FOR BUTTON IN CONTEXT MENU HERE!
        Button btn = findViewById(R.id.btnShow);
        registerForContextMenu(btn);

// CODE FOR BUTTON IN POP-UP MENU HERE!
        Button btn1 = findViewById(R.id.btnShow1);
        btn1.setOnClickListener(v -> {
            PopupMenu popup = new PopupMenu(MainActivity.this, v);
            popup.setOnMenuItemClickListener(MainActivity.this);

            popup.inflate(R.menu.popup_menu);
            popup.show();
        });
    }


// CODE FOR OPTION MENU HERE!
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "You Selected Item: " +item.getTitle(),
                Toast.LENGTH_SHORT).show();
        return true;
    }


// CODE FOR POP-UP MENU HERE!
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        Toast.makeText(this, "Selected Item: " +item.getTitle(),
                Toast.LENGTH_SHORT).show();
        return true;
    }


// CODE FOR CONTEXT MENU HERE!
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Group 5 Members");
        // Items inside the Context Menu
        menu.add(0, v.getId(), 0, "Ochada");
        menu.add(0, v.getId(), 0, "Pasag");
        menu.add(0, v.getId(), 0, "Morales");
        menu.add(0, v.getId(), 0, "Calising");
        menu.add(0, v.getId(), 0, "Ledesma");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        // toast will display the selected item here!
        Toast.makeText(this, "Selected Item: " +item.getTitle(),
                Toast.LENGTH_SHORT).show();
        return true;
    }
}