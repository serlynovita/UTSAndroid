package com.serly.uts;

public class Model {
    private int id_barang;
    private String nama_barang;
    private String harga;
    private String berat;
    private String quantity;
    private String kondisi;
    private String informasi_produk;
    private byte[] gambar;

    public Model(int id_barang,String nama_barang,
                 String harga,String berat,
                 String quantity,String kondisi,
                 String informasi_produk, byte[] gambar){
        this.id_barang=id_barang;
        this.nama_barang=nama_barang;
        this.harga=harga;
        this.berat= berat;
        this.quantity=quantity;
        this.kondisi=kondisi;
        this.informasi_produk=informasi_produk;
        this.gambar=gambar;
    }

    public Model(int id_barang, String nama_barang, String harga, byte[] gambar) {
        this.id_barang=id_barang;
        this.nama_barang=nama_barang;
        this.harga=harga;
        this.gambar=gambar;
    }

    public Model(int id_barang, String nama_barang, String harga) {
        this.id_barang=id_barang;
        this.nama_barang=nama_barang;
        this.harga=harga;
    }

    public int getId_barang() {
        return id_barang;
    }

    public void setId_barang(int id_barang) {
        this.id_barang = id_barang;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }

    public String getBerat() {
        return berat;
    }

    public void setBerat(String berat) {
        this.berat = berat;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getKondisi() {
        return kondisi;
    }

    public void setKondisi(String kondisi) {
        this.kondisi = kondisi;
    }

    public String getInformasi_produk() {
        return informasi_produk;
    }

    public void setInformasi_produk(String informasi_produk) {
        this.informasi_produk = informasi_produk;
    }

    public byte[] getGambar() {
        return gambar;
    }

    public void setGambar(byte[] gambar) {
        this.gambar = gambar;
    }
}
