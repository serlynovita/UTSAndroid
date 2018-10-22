package com.serly.uts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataHelper extends SQLiteOpenHelper {

    //Nama Database
    private static final String NAMA_DB = "jualhp.db";

    //Nama Tabel
    private static final String BARANG = "barang";
    private static final String USER = "user";

    //Versi Database
    private static final int VERSI_DB = 1;

    //Nama Kolom dalam Tabel BARANG
    private static final String ID_BARANG = "id_barang";
    private static final String NAMA_BARANG = "nama_barang";
    private static final String HARGA = "harga";
    private static final String BERAT = "berat";
    private static final String QUANTITY = "quantity";
    private static final String KONDISI = "kondisi";
    private static final String INFORMASI_PRODUK = "informasi_produk";
    private static final String GAMBAR = "gambar";

    //Nama Kolom dalam Tabel User
    private static final String ID_USER = "id_user";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";


    //SQL Create Tabel
    private static final String CREATE_BARANG =
            "CREATE TABLE "
                    + BARANG
                    + " ("
                    + ID_BARANG + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + NAMA_BARANG + " VARCHAR(255), "
                    + HARGA + " VARCHAR(255), "
                    + BERAT + " VARCHAR(255), "
                    + QUANTITY + " VARCHAR(255) ,"
                    + KONDISI + " VARCHAR(255), "
                    + INFORMASI_PRODUK + " VARCHAR(255), "
                    + GAMBAR + " BLOB "
                    + ");";

    private static final String CREATE_USER =
            "CREATE TABLE "
                    + USER
                    + " ("
                    + ID_USER + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + USERNAME + " VARCHAR(255), "
                    + PASSWORD + " VARCHAR(255) "
                    + "); ";


    //SQL Drop Tabel
    private static final String DROP_BARANG = "DROP TABLE IF EXIST " + BARANG;
    private static final String DROP_USER = "DROP TABLE IF EXIST " + USER;

    //Custom Constructor
    public MyDataHelper(Context context){
        super(context, NAMA_DB, null, VERSI_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_BARANG);
        db.execSQL(CREATE_USER);
        //db.execSQL(DROP_BARANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}