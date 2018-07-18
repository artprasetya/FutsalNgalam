package com.example.asus.futsalngalam.MenuPesanan;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class ApplicationClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // OneSignal Initialization
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new NotificationOpenedHandler())
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
    }

    private class NotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            OSNotificationAction.ActionType actionType = result.action.type;
            JSONObject data = result.notification.payload.additionalData;
            String launchUrl = result.notification.payload.launchURL; // update docs launchUrl

//            String customKey = null;
//            String avatar = null;
//            String nama = null;
//            String titleTransaksi = null;
//            String jenisTransaksi = null;
//            int openURL;
            Object activityToLaunch = DaftarPesananActivity.class;

//            if (data != null) {
//                try {
//                    //customKey = data.getString("uid");
//                    openURL = data.getInt("click_action");
//                    Log.d("nilai action", "" + openURL);
//                    Log.d("nilai url", "" + openURL);
//                    //if (openURL == "obrolan"){
//                    if (openURL == 1) {
//                        customKey = data.getString("uid");
//                        nama = data.getString("nama_pengirim");
//                        avatar = data.getString("avatar");
//                        activityToLaunch = ObrolanActivity.class;
//                    } else if (openURL == 2) {
//                        titleTransaksi = data.getString("title");
//                        jenisTransaksi = data.getString("transaksi");
//                        activityToLaunch = DaftarTransaksiActivity.class;
//                    } else {
//                        Log.d("nilai url", "open url error " + openURL);
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }

            // The following can be used to open an Activity of your choice.
            // Replace - getApplicationContext() - with any Android Context.
            // Intent intent = new Intent(getApplicationContext(), YourActivity.class);
            Intent intent = new Intent(getApplicationContext(), (Class<?>) activityToLaunch);
            // intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra(Constants.ID_PENJUAL, customKey);
//            intent.putExtra(Constants.NAMA, nama);
//            intent.putExtra(Constants.AVATAR, avatar);
//            intent.putExtra(Constants.TITLE, titleTransaksi);
//            intent.putExtra(Constants.TRANSAKSI, jenisTransaksi);
            Log.d("nilai activity", "= " + activityToLaunch);
            // startActivity(intent);
            startActivity(intent);

            // Add the following to your AndroidManifest.xml to prevent the launching of your main Activity
            //   if you are calling startActivity above.
        /*
           <application ...>
             <meta-data android:name="com.onesignal.NotificationOpened.DEFAULT" android:value="DISABLE" />
           </application>
        */
        }
    }
}
