package com.serly.uts;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewUser extends AppCompatActivity {

    protected Cursor cursor;
    MyDataHelper dbhHelper;
    Button btn2;
    TextView username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);

        dbhHelper = new MyDataHelper(this);
        username = (TextView) findViewById(R.id.textView1);
        password = (TextView) findViewById(R.id.textView2);

        SQLiteDatabase db = dbhHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user WHERE username = '" + getIntent().getStringExtra("username") + "'", null);
        cursor.moveToFirst();

        if(cursor.getCount()>0){
            cursor.moveToPosition(0);
            username.setText(cursor.getString(1));
            password.setText(cursor.getString(2));
        }

        btn2 = (Button) findViewById(R.id.button1);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });
    }
}
