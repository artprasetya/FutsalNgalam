package com.example.asus.futsalngalam.Model;

import java.io.Serializable;

public class Rekening implements Serializable {

    String idPetugas, idRekening, namaBank, namaRekening, nomorRekening;

    public Rekening() {
    }

    public Rekening(String idPetugas, String idRekening, String namaBank, String namaRekening, String nomorRekening) {
        this.idPetugas = idPetugas;
        this.idRekening = idRekening;
        this.namaBank = namaBank;
        this.namaRekening = namaRekening;
        this.nomorRekening = nomorRekening;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getIdRekening() {
        return idRekening;
    }

    public void setIdRekening(String idRekening) {
        this.idRekening = idRekening;
    }

    public String getNamaBank() {
        return namaBank;
    }

    public void setNamaBank(String namaBank) {
        this.namaBank = namaBank;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }
}
