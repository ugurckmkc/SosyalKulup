package com.meuf.sosyalkulup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


public class ProfilActivity extends Activity {

    private FirebaseDatabase fbdatabase;
    private FirebaseAuth auth;

    private TextView user_profile_name;
    private TextView user_profile_short_bio;
    private EditText profil_name_edit;
    private EditText profil_lastname_edit;
    private EditText profil_department_edit;
    private EditText profil_phone_edit;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private CheckBox checkBox5;
    private CheckBox checkBox6;
    private CheckBox checkBox7;
    private CheckBox checkBox8;
    private CheckBox checkBox9;
    private CheckBox checkBox10;
    private CheckBox checkBox11;
    private CheckBox checkBox12;

    private Button profil_save;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        checkBox1 = (CheckBox) findViewById(R.id.checkBox);
        checkBox2 = (CheckBox) findViewById(R.id.checkBox2);
        checkBox3 = (CheckBox) findViewById(R.id.checkBox3);
        checkBox4 = (CheckBox) findViewById(R.id.checkBox4);
        checkBox5 = (CheckBox) findViewById(R.id.checkBox5);
        checkBox6 = (CheckBox) findViewById(R.id.checkBox6);
        checkBox7 = (CheckBox) findViewById(R.id.checkBox7);
        checkBox8 = (CheckBox) findViewById(R.id.checkBox8);
        checkBox9 = (CheckBox) findViewById(R.id.checkBox9);
        checkBox10 = (CheckBox) findViewById(R.id.checkBox10);
        checkBox11 = (CheckBox) findViewById(R.id.checkBox11);
        checkBox12 = (CheckBox) findViewById(R.id.checkBox12);

        user_profile_name = (TextView) findViewById(R.id.user_profile_name);
        user_profile_short_bio = (TextView) findViewById(R.id.user_profile_short_bio);
        profil_name_edit = (EditText) findViewById(R.id.profil_name_edit);
        profil_lastname_edit = (EditText) findViewById(R.id.profil_lastname_edit);
        profil_department_edit = (EditText) findViewById(R.id.profil_department_edit);
        profil_phone_edit = (EditText) findViewById(R.id.profil_phone_edit);
        profil_save = (Button) findViewById(R.id.profil_save);

        final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("KulupMember");

        profil_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loading = ProgressDialog.show(ProfilActivity.this,"Lütfen Bekleyiniz",null,true,true);

                loading.show();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        loading.dismiss();
                    }
                }, 1000);

                final HashMap<String, String> user = new HashMap<String, String>();
                user.put("ProfilName", profil_name_edit.getText().toString().trim());
                user.put("ProfilLastName", profil_lastname_edit.getText().toString().trim());
                user.put("ProfilDepartment", profil_department_edit.getText().toString().trim());
                user.put("ProfilPhone", profil_phone_edit.getText().toString().trim());

                final FirebaseUser firuser = FirebaseAuth.getInstance().getCurrentUser();

                String Title1 = profil_name_edit.getText().toString();
                Log.d("myTag", Title1);

            if(checkBox1.isChecked() == true){
                dbref.child("IEEE").child(firuser.getUid()).setValue(user);
            }
            if(checkBox11.isChecked() == true){
                dbref.child("Muzik").child(firuser.getUid()).setValue(user);
            }
            if(checkBox10.isChecked() == true){
                    dbref.child("Bilisim").child(firuser.getUid()).setValue(user);
            }
            if(checkBox9.isChecked() == true){
                    dbref.child("Girisimcilik").child(firuser.getUid()).setValue(user);
            }
            if(checkBox8.isChecked() == true){
                dbref.child("E-Spor").child(firuser.getUid()).setValue(user);
            }
            if(checkBox7.isChecked() == true){
                dbref.child("Yelken").child(firuser.getUid()).setValue(user);
            }
            if(checkBox6.isChecked() == true){
                dbref.child("Sanat").child(firuser.getUid()).setValue(user);
            }
            if(checkBox5.isChecked() == true){
                dbref.child("Dans").child(firuser.getUid()).setValue(user);
            }
            if(checkBox4.isChecked() == true){
                dbref.child("Bilim Kurgu").child(firuser.getUid()).setValue(user);
            }
            if(checkBox3.isChecked() == true){
                dbref.child("Toplum Gonulleri").child(firuser.getUid()).setValue(user);
            }
            if(checkBox2.isChecked() == true){
                dbref.child("Hukuk").child(firuser.getUid()).setValue(user);
            }
            if(checkBox12.isChecked() == true){
                dbref.child("DEMK").child(firuser.getUid()).setValue(user);
            }
                finish();
            Toast.makeText(ProfilActivity.this,"Kulüp kaydınız başarı ile tamamlanmıştır.",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
