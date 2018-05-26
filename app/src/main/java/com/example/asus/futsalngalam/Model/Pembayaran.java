package com.example.asus.futsalngalam.Model;

public class Pembayaran {
    String idPesanan, idRekening, namaBank, nomorRekening, namaRekening, namaRekPemesan, nomorRekPemesan, jenisPembayaran, nominalTransfer, buktiPembayaran;

    public Pembayaran() {
    }

    public Pembayaran(String idPesanan, String namaRekPemesan, String nomorRekPemesan, String jenisPembayaran, String nominalTransfer, String buktiPembayaran) {
        this.idPesanan = idPesanan;
        this.namaRekPemesan = namaRekPemesan;
        this.nomorRekPemesan = nomorRekPemesan;
        this.jenisPembayaran = jenisPembayaran;
        this.nominalTransfer = nominalTransfer;
        this.buktiPembayaran = buktiPembayaran;
    }

    public Pembayaran(String idRekening, String namaBank, String nomorRekening, String namaRekening) {
        this.idRekening = idRekening;
        this.namaBank = namaBank;
        this.nomorRekening = nomorRekening;
        this.namaRekening = namaRekening;
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

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getNamaRekening() {
        return namaRekening;
    }

    public void setNamaRekening(String namaRekening) {
        this.namaRekening = namaRekening;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getNamaRekPemesan() {
        return namaRekPemesan;
    }

    public void setNamaRekPemesan(String namaRekPemesan) {
        this.namaRekPemesan = namaRekPemesan;
    }

    public String getNomorRekPemesan() {
        return nomorRekPemesan;
    }

    public void setNomorRekPemesan(String nomorRekPemesan) {
        this.nomorRekPemesan = nomorRekPemesan;
    }

    public String getJenisPembayaran() {
        return jenisPembayaran;
    }

    public void setJenisPembayaran(String jenisPembayaran) {
        this.jenisPembayaran = jenisPembayaran;
    }

    public String getNominalTransfer() {
        return nominalTransfer;
    }

    public void setNominalTransfer(String nominalTransfer) {
        this.nominalTransfer = nominalTransfer;
    }

    public String getBuktiPembayaran() {
        return buktiPembayaran;
    }

    public void setBuktiPembayaran(String buktiPembayaran) {
        this.buktiPembayaran = buktiPembayaran;
    }
}
