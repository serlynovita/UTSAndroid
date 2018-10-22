package com.serly.uts;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class BarangActivity extends AppCompatActivity {

    public static  BarangActivity tabelBarang;
    ListView mListView;
    ArrayList<Model> mList;
    BarangAdapter mAdapter = null;

    protected Cursor cursor;
    ImageView imageViewIcon;
    MyDataHelper jualhp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barang);


        Button btn = (Button) findViewById(R.id.tambah);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(BarangActivity.this,InsertBarang.class);
                startActivity(myintent);
            }
        });

        tabelBarang = this;

        mListView = findViewById(R.id.listView);
        mList = new ArrayList<>();
        mAdapter = new BarangAdapter(this,R.layout.row,mList);
        mListView.setAdapter(mAdapter);


        jualhp = new MyDataHelper(this);

        SQLiteDatabase db =jualhp.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM BARANG",null);
        //Cursor cursor = InsertBarang.mSQLiteHelper.getData("SELECT id_barang,nama_barang,harga,gambar FROM BARANG");
        mList.clear();
        while(cursor.moveToNext()){
            int id_barang = cursor.getInt(0);
            String nama_barang = cursor.getString(1);
            String harga = cursor.getString(2);
            //byte[] gambar = cursor.getBlob(0);

            mList.add(new Model(id_barang,nama_barang,harga));
        }
        mAdapter.notifyDataSetChanged();
        if(mList.size()==0){
            Toast.makeText(this,"No Record found...",Toast.LENGTH_SHORT).show();
        }
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                return false;
            }
        });
    }
}
