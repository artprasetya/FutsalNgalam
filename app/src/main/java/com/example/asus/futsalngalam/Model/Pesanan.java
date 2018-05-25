package com.example.asus.futsalngalam.Model;

public class Pesanan {

    String idPetugas,
            idPesanan,
            idPemesan,
            idLapangan,
            statusPesanan,
            namaPemesan,
            noTelepon,
            namaTempatFutsal,
            namaLapangan,
            tanggalPesan,
            jamMulai,
            jamSelesai,
            invoice,
            timestamp;

    double totalPembayaran;

    public Pesanan() {
    }

    public Pesanan(String idPetugas, String idPesanan, String idPemesan, String idLapangan, String statusPesanan, String namaPemesan, String noTelepon, String namaTempatFutsal, String namaLapangan, String tanggalPesan, String jamMulai, String jamSelesai, String invoice, String timestamp, double totalPembayaran) {
        this.idPetugas = idPetugas;
        this.idPesanan = idPesanan;
        this.idPemesan = idPemesan;
        this.idLapangan = idLapangan;
        this.statusPesanan = statusPesanan;
        this.namaPemesan = namaPemesan;
        this.noTelepon = noTelepon;
        this.namaTempatFutsal = namaTempatFutsal;
        this.namaLapangan = namaLapangan;
        this.tanggalPesan = tanggalPesan;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
        this.invoice = invoice;
        this.timestamp = timestamp;
        this.totalPembayaran = totalPembayaran;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public String getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(String idPetugas) {
        this.idPetugas = idPetugas;
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

    public String getIdLapangan() {
        return idLapangan;
    }

    public void setIdLapangan(String idLapangan) {
        this.idLapangan = idLapangan;
    }

    public String getStatusPesanan() {
        return statusPesanan;
    }

    public void setStatusPesanan(String statusPesanan) {
        this.statusPesanan = statusPesanan;
    }

    public String getNamaPemesan() {
        return namaPemesan;
    }

    public void setNamaPemesan(String namaPemesan) {
        this.namaPemesan = namaPemesan;
    }

    public String getNoTelepon() {
        return noTelepon;
    }

    public void setNoTelepon(String noTelepon) {
        this.noTelepon = noTelepon;
    }

    public String getNamaTempatFutsal() {
        return namaTempatFutsal;
    }

    public void setNamaTempatFutsal(String namaTempatFutsal) {
        this.namaTempatFutsal = namaTempatFutsal;
    }

    public String getNamaLapangan() {
        return namaLapangan;
    }

    public void setNamaLapangan(String namaLapangan) {
        this.namaLapangan = namaLapangan;
    }

    public String getTanggalPesan() {
        return tanggalPesan;
    }

    public void setTanggalPesan(String tanggalPesan) {
        this.tanggalPesan = tanggalPesan;
    }

    public String getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(String jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(String jamSelesai) {
        this.jamSelesai = jamSelesai;
    }
}
