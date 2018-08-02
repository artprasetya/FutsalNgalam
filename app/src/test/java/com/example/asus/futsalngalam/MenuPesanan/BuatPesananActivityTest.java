package com.example.asus.futsalngalam.MenuPesanan;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

//
//@RunWith(RobolectricTestRunner.class)
//@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class BuatPesananActivityTest {

    @Test
    public void totalPembayaran() throws Exception {

        int jamMulai = 7;
        int jamSelesai = 10;
        int durasi = jamSelesai - jamMulai;
        int hargaSewa = 150000;

        assertEquals(3, jamSelesai-jamMulai);
        assertEquals(450000, durasi * hargaSewa);
    }


}






