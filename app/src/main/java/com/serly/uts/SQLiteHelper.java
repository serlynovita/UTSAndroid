package com.serly.uts;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class SQLiteHelper extends SQLiteOpenHelper {

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void queryData(String sql){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public void inserData(String nama_barang, String harga, String berat,
                          String quantity,String kondisi,String informasi_produk,
                          byte[] gambar){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO barang VALUES (NULL,?,?,?,?,?,?,?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,nama_barang);

        statement.executeInsert();
    }

    public void updateData(String nama_barang, String harga, String berat,
                           String quantity,String kondisi,String informasi_produk,
                           byte[] gambar, int id_barang){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "UPDATE barang SET nama_barang=?,harga=?,berat=?," +
                "quantity=?,kondisi=?,informasi_produk=?,gambar=? WHERE id_barang=?";

        SQLiteStatement statement = database.compileStatement(sql);

        statement.bindString(1,nama_barang);
        statement.bindString(2,harga);
        statement.bindString(3,berat);
        statement.bindString(4,quantity);
        statement.bindString(5,kondisi);
        statement.bindString(6,informasi_produk);
        statement.bindBlob(7,gambar);
        statement.bindLong(8,(long)id_barang);

        statement.execute();
        database.close();
    }

    public void deleteData(int id_barang){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM barang WHERE id_barang=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindLong(1,(long) id_barang);

        statement.execute();
        database.close();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
