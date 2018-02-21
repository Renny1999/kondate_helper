package com.example.rhong.externaldb;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static String DB_PATH = "data/data/com.example.rhong.externaldb/databases/";
    private static String DB_NAME = "foodlist.db";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseOpenHelper openHelper = new DatabaseOpenHelper(this);
        openHelper.getWritableDatabase();

        FoodlistOpenHelper helper;
        ListView list = findViewById(R.id.list);

        try {
            this.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        helper = new FoodlistOpenHelper(this);

        ArrayList<String> mylist = new ArrayList<>();
        Cursor data = helper.getListContents();

        while(data.moveToNext()){
            mylist.add(data.getString(0));
            ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
            list.setAdapter(adapter);
        }

    }

    public void createDataBase() throws IOException{
        //if database does not exist, copy it from the assets folder

        boolean exists = checkDataBase();
        if(exists){
            try{
                copyDataBase();
              //  Log.e(TAG, "createDatabase database created");
            }catch(IOException e){
                throw new Error("ErrorCopyingDataBase");
            }
        }
    }

    public boolean checkDataBase(){
        File dbFile = new File(DB_PATH+DB_NAME);
        return dbFile.exists();
    }

    public void copyDataBase() throws IOException{
        InputStream input = getApplicationContext().getAssets().open(DB_NAME);
        String outFileName = DB_PATH+ DB_NAME;
        OutputStream output = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int length;
        while((length = input.read(mBuffer))>0){
            output.write(mBuffer, 0, length);
        }
        output.flush();;
        output.close();
        input.close();
    }
}
