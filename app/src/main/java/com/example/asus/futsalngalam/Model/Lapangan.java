package com.example.asus.futsalngalam.Model;

import java.io.Serializable;

public class Lapangan implements Serializable {
    String idLapangan, idPetugas, namaLapangan, kategori;
    int hargaSewa;

    public Lapangan() {
    }

    public Lapangan(String idLapangan, String idPetugas, String namaLapangan, String kategori, int hargaSewa) {
        this.idLapangan = idLapangan;
        this.idPetugas = idPetugas;
        this.namaLapangan = namaLapangan;
        this.kategori = kategori;
        this.hargaSewa = hargaSewa;
    }

    public String getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(String idLapangan) {
        this.idLapangan = idLapangan;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getNamaLapangan() {
        return namaLapangan;
    }

    public void setNamaLapangan(String namaLapangan) {
        this.namaLapangan = namaLapangan;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getHargaSewa() {
        return hargaSewa;
    }

    public void setHargaSewa(int hargaSewa) {
        this.hargaSewa = hargaSewa;
    }
}
