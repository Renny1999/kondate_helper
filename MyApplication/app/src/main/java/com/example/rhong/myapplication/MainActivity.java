package com.example.rhong.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText input = findViewById(R.id.input);
        final Button add = findViewById(R.id.add);


        DatabaseOpenHelper helper = new DatabaseOpenHelper(this);
        final SQLiteDatabase db = helper.getWritableDatabase();
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put("name", input.getText().toString());
                db.insert("userfoodlist" , null , values);
                input.setText("");
            }
        });

    }
}
