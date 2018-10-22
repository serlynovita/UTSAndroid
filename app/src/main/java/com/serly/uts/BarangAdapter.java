package com.serly.uts;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BarangAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Model> barangList;

    public BarangAdapter(Context context, int layout, ArrayList<Model> barangList) {
        this.context = context;
        this.layout = layout;
        this.barangList = barangList;
    }

    @Override
    public int getCount() {
        return barangList.size();
    }

    @Override
    public Object getItem(int i) {
        return barangList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        ImageView gambar;
        TextView txtNama, txtHarga,txtBerat,txtQuantity,
        txtKondisi,txtInformasiProduk;
    }

    @Override
    public View getView(int i, View view, ViewGroup parent) {
        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout,null);
            holder.txtNama=row.findViewById(R.id.txtNama);
            holder.txtHarga= row.findViewById(R.id.txtHarga);
            holder.gambar=row.findViewById(R.id.imgIcon);
            row.setTag(holder);
        }
        else{
            holder = (ViewHolder)row.getTag();
        }

        Model model=barangList.get(i);
        holder.txtNama.setText(model.getNama_barang());
        holder.txtHarga.setText(model.getHarga());
        byte[] barangGambar = model.getGambar();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(barangGambar,0,barangGambar.length);
//        holder.gambar.setImageBitmap(bitmap);

        return row;
    }
}
