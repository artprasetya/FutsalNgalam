package com.example.asus.futsalngalam.MenuPesanan;

import android.os.Build;

import com.example.asus.futsalngalam.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
public class BuatPesananActivityTest {

    BuatPesananActivity activity = new BuatPesananActivity();

    @Before
    public void setup() {
        activity = Robolectric.buildActivity(BuatPesananActivity.class).create().get();
    }

    @Test
    public void cekJadwal() throws Exception {
        String tempatFutsal = "Viva Futsal";
        String lapangan = "Lapangan 1";
        String tanggalPesan = "17-08-2018";
        int jamMulai= 6;
        int jamSelesai= 8;

        

    }
}