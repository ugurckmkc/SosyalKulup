package com.meuf.sosyalkulup.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.meuf.sosyalkulup.R;


public class StartScreen extends Activity {

    private String Title,Message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start_screen);



        Thread th = new Thread() {
                @Override
                public void run() {
                    try {
                        sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        SharedPreferences sharedPref = getSharedPreferences("com.meuf.sosyalkulup", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPref.edit();

                        if(getIntent().getExtras() !=null){
                            for(String key : getIntent().getExtras().keySet()){
                                if(key.equals("title"))
                                    Title = getIntent().getExtras().getString(key);
                                else if(key.equals("message"))
                                    Message = getIntent().getExtras().getString(key);
                            }
                        }
                        editor.putString("title", Title);
                        editor.putString("message", Message);
                        editor.commit();

                        Intent obj = new Intent(StartScreen.this, Login.class);
                        startActivity(obj);
                    }
                }

            };
            th.start();
        }



    @Override
    protected void onResume() {
        super.onResume();

       // sharedPref.edit().putString("title",Title).apply();
       // sharedPref.edit().putString("message", Message).apply();

       // KulupDuyuruFragment KulupDuyuru = new KulupDuyuruFragment();

        /*Bundle bundle = new Bundle();
        bundle.putString("title",Title);
        bundle.putString("message",Message);
        KulupDuyuru.setArguments(bundle);*/

    }

    @Override
    protected void onPause(){
        super.onPause();
        finish();
    }
}

