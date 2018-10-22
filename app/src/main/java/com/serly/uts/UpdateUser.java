package com.serly.uts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateUser extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbHelper;
    Button btn1, btn2;
    EditText username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        dbHelper = new MyDataHelper(this);
        username = (EditText) findViewById(R.id.valueUsername);
        password = (EditText) findViewById(R.id.valuePassword);

        btn1 = (Button) findViewById(R.id.button1);
        btn2 = (Button) findViewById(R.id.button2);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user WHERE username = '" + getIntent().getStringExtra("username") + "'", null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            username.setText(cursor.getString(1));
            password.setText(cursor.getString(2));
        }

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL(" UPDATE user SET username='" + username.getText().toString() + "', password = '" + password.getText().toString() +
                        "' WHERE username='" + getIntent().getStringExtra("username") + "'");
                Toast.makeText(getApplicationContext(),"berhasil di update",Toast.LENGTH_LONG).show();

                UserActivity.tabelUser.TampilkanList();

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
