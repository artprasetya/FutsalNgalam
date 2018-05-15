package com.example.asus.futsalngalam.Model;

import java.io.Serializable;

public class Fasilitas implements Serializable {

    String idFasilitas,idPetugas, fasilitas;

    public Fasilitas() {
    }

    public Fasilitas(String idFasilitas, String idPetugas, String fasilitas) {
        this.idFasilitas = idFasilitas;
        this.idPetugas = idPetugas;
        this.fasilitas = fasilitas;
    }

    public String getIdFasilitas() {
        return idFasilitas;
    }

    public void setIdFasilitas(String idFasilitas) {
        this.idFasilitas = idFasilitas;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }
}
