package com.serly.uts;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    Button login;
    EditText nama;
    EditText password;
    Button regis;
    CheckBox chkRemember;
    Button btnLogin;
    MyDataHelper dbHelper;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.checkSavedCredentials();
        this.initComponents();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        regis = (Button) findViewById(R.id.btn_regis);
        regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this,RegistrasiActivity.class);
                startActivity(myintent);
            }
        });
    }

    private void initComponents() {
        this.chkRemember = (CheckBox) this.findViewById(R.id.chk_remember);
        //this.regis= (Button) this.findViewById(R.id.regis);
        this.btnLogin = (Button) this.findViewById(R.id.btn_login);
        this.nama = (EditText) this.findViewById(R.id.nama);
        this.password = (EditText) this.findViewById(R.id.password);
    }

    public void button_onClick(View view)
    {
        this.login();
    }

    private void checkSavedCredentials()
    {
        SharedPreferences handler = this.getSharedPreferences("key", Context.MODE_PRIVATE);
        String username = handler.getString("username", "");
        String password = handler.getString("password", "");

        boolean loginCorrect = this.checkCredentials(username, password);

        if(loginCorrect)
            this.openHome(username);
    }

    private void login()
    {
        String username = this.nama.getText().toString();
        String password = this.password.getText().toString();

        boolean loginCorrect = this.checkCredentials(username, password);

        if(loginCorrect)
        {
            boolean remember = this.chkRemember.isChecked();

            if(remember)
            {
                this.SavedCredentials();
            }
            this.openHome(username);
        }
        else
        {
            Toast.makeText(this.getApplicationContext(), "Invalid username and/or password", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkCredentials(String username, String password)
    {
        dbHelper = new MyDataHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM user WHERE username = '" +
                username + "' AND password = '"+password+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            return true;
        } else {
            return false;
        }
    }

    private void SavedCredentials()
    {
        SharedPreferences handler = this.getSharedPreferences("key",Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = handler.edit();

        editor.putString("username", this.nama.getText().toString());
        editor.putString("password", this.password.getText().toString());

        editor.apply();

    }

    private void openHome(String username)
    {
        Intent i = new Intent(this.getApplicationContext(), AdminActivity.class);
        i.putExtra("username", username);
        this.startActivity(i);
    }
}
