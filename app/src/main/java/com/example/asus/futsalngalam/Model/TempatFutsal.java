package com.example.asus.futsalngalam.Model;

/**
 * Created by ASUS on 28-Dec-17.
 */

public class TempatFutsal {

    private String IdTempatFutsal;
    private String Nama;
    private String Alamat;
    private String Harga;
    private String Latitude;
    private String Longtitude;
    private String imageUrl;

    public TempatFutsal() {
    }

    public TempatFutsal(String idTempatFutsal, String nama, String alamat, String harga, String latitude, String longtitude, String imageUrl) {
        IdTempatFutsal = idTempatFutsal;
        Nama = nama;
        Alamat = alamat;
        Harga = harga;
        Latitude = latitude;
        Longtitude = longtitude;
        this.imageUrl = imageUrl;
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

    public void setIdTempatFutsal(String idTempatFutsal) {
        IdTempatFutsal = idTempatFutsal;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setNama(String nama) {
        Nama = nama;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public void setLongtitude(String longtitude) {
        Longtitude = longtitude;
    }
}
