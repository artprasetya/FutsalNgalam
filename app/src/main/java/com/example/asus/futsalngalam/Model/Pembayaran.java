package com.example.asus.futsalngalam.Model;

public class Pembayaran {
    String idPesanan, bankTempatFutsal, namaRekBankTempatFutsal, namaRekPemesan, nomorRekPemesan, jenisPembayaran, nominalTransfer, buktiPembayaran;

    public Pembayaran() {
    }

    public Pembayaran(String idPesanan, String bankTempatFutsal, String namaRekBankTempatFutsal, String namaRekPemesan, String nomorRekPemesan, String jenisPembayaran, String nominalTransfer, String buktiPembayaran) {
        this.idPesanan = idPesanan;
        this.bankTempatFutsal = bankTempatFutsal;
        this.namaRekBankTempatFutsal = namaRekBankTempatFutsal;
        this.namaRekPemesan = namaRekPemesan;
        this.nomorRekPemesan = nomorRekPemesan;
        this.jenisPembayaran = jenisPembayaran;
        this.nominalTransfer = nominalTransfer;
        this.buktiPembayaran = buktiPembayaran;
    }

    public String getIdPesanan() {
        return idPesanan;
    }

    public void setIdPesanan(String idPesanan) {
        this.idPesanan = idPesanan;
    }

    public String getBankTempatFutsal() {
        return bankTempatFutsal;
    }

    public void setBankTempatFutsal(String bankTempatFutsal) {
        this.bankTempatFutsal = bankTempatFutsal;
    }

    public String getNamaRekBankTempatFutsal() {
        return namaRekBankTempatFutsal;
    }

    public void setNamaRekBankTempatFutsal(String namaRekBankTempatFutsal) {
        this.namaRekBankTempatFutsal = namaRekBankTempatFutsal;
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
