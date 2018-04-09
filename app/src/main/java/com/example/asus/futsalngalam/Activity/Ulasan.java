package com.example.asus.futsalngalam.Activity;

/**
 * Created by ASUS on 24-Nov-17.
 */

public class Ulasan {

    private String Id, Nama, Pesan;

    public Ulasan(String id, String nama, String pesan) {
        Id = id;
        Nama = nama;
        Pesan = pesan;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNama() {
        return Nama;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public String getPesan() {
        return Pesan;
    }

    public void setPesan(String pesan) {
        Pesan = pesan;
    }
}
