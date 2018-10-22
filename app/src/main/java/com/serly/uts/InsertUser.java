package com.serly.uts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertUser extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1, btn2;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_user);

        dbHelper = new MyDataHelper(this);
        username = (EditText) findViewById(R.id.valueUsername);
        password = (EditText) findViewById(R.id.valuePassword);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("INSERT INTO USER(username, password) values('" + username.getText().toString() + "','" + password.getText().toString() + "')");
                Toast.makeText(getApplicationContext(),"User berhasil",Toast.LENGTH_LONG).show();

                finish();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
