package com.example.asus.futsalngalam.MenuProfil;

/**
 * Created by ASUS on 24-Nov-17.
 */

public class Pemesan {

    private String fotoProfil, nama, email, telepon, alamat;

    public Pemesan() {
    }

    public Pemesan(String fotoProfil, String nama, String email, String telepon, String alamat) {
        this.fotoProfil = fotoProfil;
        this.nama = nama;
        this.email = email;
        this.telepon = telepon;
        this.alamat = alamat;
    }

    public String getFotoProfil() {
        return fotoProfil;
    }

    public void setFotoProfil(String fotoProfil) {
        this.fotoProfil = fotoProfil;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
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

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }
}
