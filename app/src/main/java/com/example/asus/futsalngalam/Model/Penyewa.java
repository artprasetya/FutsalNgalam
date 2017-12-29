package com.example.asus.futsalngalam.Model;

/**
 * Created by ASUS on 24-Nov-17.
 */

public class Penyewa {

    private String Nama, Telepon, uriProfileImage;

    public Penyewa(String nama, String telepon) {

    }

    public Penyewa(String nama, String telepon, String uriProfileImage) {
        this.Nama = nama;
        this.Telepon = telepon;
        this.uriProfileImage = uriProfileImage;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setTelepon(String telepon) {
        Telepon = telepon;
    }

    public String getNama() {
        return Nama;
    }

    public String getTelepon() {
        return Telepon;
    }
}
