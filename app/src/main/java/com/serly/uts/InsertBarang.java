package com.serly.uts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.ByteArrayOutputStream;

public class InsertBarang extends AppCompatActivity {

    EditText mNama, mHarga, mBerat, mQuantity, mKondisi, mInformasiProduk;
    Button mBtnAdd, mBtnList, mBtnKembali;
    ImageView mGambar;

    final int REQUEST_CODE_GALLERY = 999;

    public static SQLiteHelper mSQLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_barang);

        mNama = findViewById(R.id.editNama);
        mHarga = findViewById(R.id.editHarga);
        mBerat = findViewById(R.id.editBerat);
        mQuantity = findViewById(R.id.editQuantity);
        mKondisi = findViewById(R.id.editNama);
        mInformasiProduk = findViewById(R.id.editHarga);
        mGambar = findViewById(R.id.gambar);

        mBtnAdd = findViewById(R.id.button1);
        mBtnList = findViewById(R.id.button2);
        mBtnKembali = findViewById(R.id.button3);

        mSQLiteHelper = new SQLiteHelper(this,"jualhp.db",null,1);

        mSQLiteHelper.queryData("CREATE TABLE IF NOT EXISTS BARANG(id_barang" +
                " INTEGER PRIMARY KEY AUTOINCREMENT, nama_barang VARCHAR, harga VARCHAR," +
                " berat VARCHAR, quantity VARCHAR, kondisi VARCHAR, " +
                "informasi_produk VARCHAR, gambar BLOB)");

        mGambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        InsertBarang.this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                        );
            }
        });

        mBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mSQLiteHelper.inserData(
                            mNama.getText().toString().trim(),
                            mHarga.getText().toString().trim(),
                            mBerat.getText().toString().trim(),
                            mQuantity.getText().toString().trim(),
                            mKondisi.getText().toString().trim(),
                            mInformasiProduk.getText().toString().trim(),
                            imageViewToByte(mGambar)
                    );
                    Toast.makeText(InsertBarang.this,"Add Sukses",Toast.LENGTH_SHORT).show();

                    mNama.setText("");
                    mHarga.setText("");
                    mBerat.setText("");
                    mQuantity.setText("");
                    mKondisi.setText("");
                    mInformasiProduk.setText("");
                    mGambar.setImageResource(R.drawable.addphoto);
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });

//        mBtnList.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
//
//        mBtnKembali.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    public static byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent,REQUEST_CODE_GALLERY);
            }
            else{
                Toast.makeText(this,"Don't have permission to access file location"
                , Toast.LENGTH_SHORT).show();
            }
            return;
        }
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK){
            Uri imageUri = data.getData();
            CropImage.activity(imageUri).setGuidelines(CropImageView.Guidelines.ON).setAspectRatio(1,1).start(this);
        }

        if(requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if(resultCode == RESULT_OK){
                Uri resultUri = result.getUri();
                mGambar.setImageURI(resultUri);
            }
            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){
                Exception error = result.getError();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
