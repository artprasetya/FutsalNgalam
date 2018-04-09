package com.example.asus.futsalngalam.MenuBeranda;

/**
 * Created by ASUS on 04-Jan-18.
 */

public class TempatFutsal {

    private String NamaFutsal, Harga, Alamat, Deskripsi, Email, Image, Kontak, Buka, Tutup;

    public TempatFutsal() {

    }

    public TempatFutsal(String namaFutsal, String harga, String alamat, String deskripsi, String email, String image, String kontak, String buka, String tutup) {
        NamaFutsal = namaFutsal;
        Harga = harga;
        Alamat = alamat;
        Deskripsi = deskripsi;
        Email = email;
        Image = image;
        Kontak = kontak;
        Buka = buka;
        Tutup = tutup;
    }

    public void setNamaFutsal(String namaFutsal) {
        NamaFutsal = namaFutsal;
    }

    public void setHarga(String harga) {
        Harga = harga;
    }

    public void setAlamat(String alamat) {
        Alamat = alamat;
    }

    public void setDeskripsi(String deskripsi) {
        Deskripsi = deskripsi;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setImage(String image) {
        Image = image;
    }

    public void setKontak(String kontak) {
        Kontak = kontak;
    }

    public void setBuka(String buka) {
        Buka = buka;
    }

    public void setTutup(String tutup) {
        Tutup = tutup;
    }

    public String getNamaFutsal() {
        return NamaFutsal;
    }

    public String getHarga() {
        return Harga;
    }

    public String getAlamat() {
        return Alamat;
    }

    public String getDeskripsi() {
        return Deskripsi;
    }

    public String getEmail() {
        return Email;
    }

    public String getImage() {
        return Image;
    }

    public String getKontak() {
        return Kontak;
    }

    public String getBuka() {
        return Buka;
    }

    public String getTutup() {
        return Tutup;
    }
}
