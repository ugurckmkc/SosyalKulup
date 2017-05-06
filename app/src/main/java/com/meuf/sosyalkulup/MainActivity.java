package com.meuf.sosyalkulup;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

public class MainActivity extends Activity {

    CircleMenu circleMenu;
    public ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleMenu = (CircleMenu) findViewById(R.id.circle_menu);
        imageView = (ImageView) findViewById(R.id.imageView2);



        circleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.mipmap.icon_cancel);
        circleMenu.addSubMenu(Color.parseColor("#FFFFFF"), R.drawable.iconpng)
                .addSubMenu(Color.parseColor("#FFFFFF"), R.mipmap.icon_chat)
                .addSubMenu(Color.parseColor("#FFFFFF"), R.mipmap.icon_notify);


        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener(){

            @Override
            public void onMenuSelected(int index) {

                switch (index) {

                    case 0:
                        Intent intent = new Intent(MainActivity.this, KulupListActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intent = new Intent(this, RegistrationIntentService.class);
                        startService(intent);
                        Intent intent1 = new Intent(MainActivity.this, KulupSohbetActivity.class);
                        startActivity(intent1);
                        break;
                    case 2:
                        Intent intent2 = new Intent(MainActivity.this, KulupDuyuruActivity.class);
                        startActivity(intent2);
                        break;

                }
            }

        });

    }

    @Override
    public void onBackPressed() {
        if (circleMenu.isOpened()){

            imageView.setVisibility(View.VISIBLE);
            circleMenu.closeMenu();
            }

        else
            finish();
    }
}
