package com.example.asus.futsalngalam.Model;

/**
 * Created by ASUS on 24-Nov-17.
 */

public class Penyewa {

    private String nama, email, telepon;

    public Penyewa() {
    }

    public Penyewa(String nama, String email, String telepon) {
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
    }

    public String getNama() {
        return nama;
    }

    public String getEmail() {
        return email;
    }

    public String getTelepon() {
        return telepon;
    }
}
