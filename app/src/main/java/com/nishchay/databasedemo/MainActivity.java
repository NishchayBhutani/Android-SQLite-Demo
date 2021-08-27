package com.nishchay.databasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       try {
           SQLiteDatabase sqLiteDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE, null);
           sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUsers(name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
//           sqLiteDatabase.execSQL("INSERT INTO newUsers (name, age) VALUES('Nishchay', 20)");
//           sqLiteDatabase.execSQL(("INSERT INTO newUsers (name, age) VALUES('Nick', 69)"));
//           sqLiteDatabase.execSQL(("INSERT INTO newUsers (name, age) VALUES('Nishchay', 20)"));

           sqLiteDatabase.execSQL("DELETE FROM newUsers WHERE name='Nishchay' AND id=1");

           Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM newUsers", null);
           int nameIndex = c.getColumnIndex("name");
           int ageIndex = c.getColumnIndex("age");
           int idIndex = c.getColumnIndex("id");

           c.moveToFirst();

           while(!c.isAfterLast()) {
               Log.i("userResults - name", c.getString(nameIndex));
               Log.i("userResults - age", Integer.toString(c.getInt(ageIndex)));
               Log.i("userResults - id", Integer.toString(c.getInt(idIndex)));

               c.moveToNext();
           }
       }
       catch (Exception e) {
           e.printStackTrace();
       }
    }
}