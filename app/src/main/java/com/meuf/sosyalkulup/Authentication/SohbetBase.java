package com.meuf.sosyalkulup.Authentication;

import android.app.Application;

import com.sendbird.android.SendBird;

/**
 * Created by UgurCkmkc on 01/05/2017.
 */

public class SohbetBase extends Application {

    private static final String APP_ID = "D1F93CBD-3E1C-40A1-A4B1-546B6AA98344";
    public static final String VERSION = "3.0.30";

    @Override
    public void onCreate() {
        super.onCreate();
        SendBird.init(APP_ID, getApplicationContext());
    }
}