package com.example.asus.futsalngalam.Model;

import java.io.Serializable;

public class Ulasan implements Serializable {

    String idPetugas, idUlasan, idPesanan, idPemesan, namaPemesan, nomorTelepon, ulasan, rating;

    public Ulasan() {
    }

    public Ulasan(String idPetugas, String idUlasan, String idPesanan, String idPemesan, String namaPemesan, String nomorTelepon, String ulasan, String rating) {
        this.idPetugas = idPetugas;
        this.idUlasan = idUlasan;
        this.idPesanan = idPesanan;
        this.idPemesan = idPemesan;
        this.namaPemesan = namaPemesan;
        this.nomorTelepon = nomorTelepon;
        this.ulasan = ulasan;
        this.rating = rating;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getIdUlasan() {
        return idUlasan;
    }

    public void setIdUlasan(String idUlasan) {
        this.idUlasan = idUlasan;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getIdPemesan() {
        return idPemesan;
    }

    public void setIdPemesan(String idPemesan) {
        this.idPemesan = idPemesan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getNomorTelepon() {
        return nomorTelepon;
    }

    public void setNomorTelepon(String nomorTelepon) {
        this.nomorTelepon = nomorTelepon;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }
}
