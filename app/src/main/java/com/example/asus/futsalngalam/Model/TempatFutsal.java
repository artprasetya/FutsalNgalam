package com.example.asus.futsalngalam.Model;

import java.io.Serializable;

/**
 * Created by ASUS on 28-Dec-17.
 */

public class TempatFutsal implements Serializable {

    private String IdTempatFutsal;
    private String Nama;
    private String Alamat;
    private String Harga;
    private String Latitude;
    private String Longtitude;

    public TempatFutsal() {
    }

    public String getIdTempatFutsal() {
        return IdTempatFutsal;
    }

    public String getNama() {
        return Nama;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getHarga() {
        return Harga;
    }

    public String getLatitude() {
        return Latitude;
    }

    public String getLongtitude() {
        return Longtitude;
    }

}
