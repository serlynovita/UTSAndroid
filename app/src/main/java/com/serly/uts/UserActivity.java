package com.serly.uts;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class UserActivity extends AppCompatActivity {

    ListView ListView01;

    protected Cursor cursor;
    MyDataHelper jualhp;

    public static  UserActivity tabelUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Button btn = (Button) findViewById(R.id.tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(UserActivity.this,InsertUser.class);
                startActivity(myintent);
            }
        });

        tabelUser = this;
        TampilkanList();
    }

    public void TampilkanList(){
        jualhp = new MyDataHelper(this);

        SQLiteDatabase db =jualhp.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM user",null);
        final String[] databaru = new String[cursor.getCount()];

        cursor.moveToFirst();

        for(int cc=0;cc<cursor.getCount();cc++){
            cursor.moveToPosition(cc);
            databaru[cc]=cursor.getString(1);
        }

        ListView01 = (ListView) findViewById(R.id.listView1);
        ListView01.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,databaru));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = databaru[arg2];
                final CharSequence[] dialogitem = {"Lihat User","Update User","Hapus User"};

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);

                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item){
                            case 0 :
                                Intent intent0 = new Intent(getApplicationContext(),ViewUser.class);
                                intent0.putExtra("username",selection);
                                startActivity(intent0);
                                break;
                            case 1 :
                                Intent intent1 = new Intent(getApplicationContext(),UpdateUser.class);
                                intent1.putExtra("username",selection);
                                startActivity(intent1);
                                break;
                            case 2 :
                                SQLiteDatabase db = jualhp.getWritableDatabase();
                                db.execSQL("DELETE FROM user WHERE username = '" + selection + "'");
                                TampilkanList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });

        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();

        cursor.close();
    }
}
