package com.meuf.sosyalkulup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUp extends Activity {

    EditText edit_username;
    EditText edit_email;
    EditText edit_pass;
    Button btn_sign;
    Button btn_login;
    TextView text_login;
    private static final String REGISTER_URL="http://sosyalkulup.hol.es/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        edit_username = (EditText) findViewById(R.id.id_username);
        edit_email = (EditText) findViewById(R.id.id_email);
        edit_pass = (EditText) findViewById(R.id.id_pass);
        btn_sign = (Button) findViewById(R.id.btn_signup);
        btn_login = (Button) findViewById(R.id.btn_login);
        text_login=(TextView) findViewById(R.id.text_login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this,Login.class);
                startActivity(intent);
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = edit_username.getText().toString().trim().toLowerCase();
        if(TextUtils.isEmpty(username)){
            edit_username.setError("Okul Numarası Boş Bıraklımaz.");

        }
        String email = edit_email.getText().toString().trim().toLowerCase();
        if(TextUtils.isEmpty(email)){
            edit_email.setError("Email Boş Bıraklımaz.");
        }
        String password = edit_pass.getText().toString().trim().toLowerCase();
        if(TextUtils.isEmpty(password)){
            edit_pass.setError("Parola Boş Bıraklımaz.");
        }
        register(username,email,password);
    }
    private  void register(String username,String email,String password){
        String urlsuffix = "?username=" + username + "&email=" + email +"&password=" + password;
        class RegisterUser extends AsyncTask<String,Void,String>{

            private ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(SignUp.this,"Lütfen Bekleyiniz",null,true,true);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(),"Kayıt Başarılı.",Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
                String s = params[0];
                BufferedReader bufferReader = null;
                try {
                    URL url = new URL(REGISTER_URL + s);
                    HttpURLConnection con = (HttpURLConnection)url.openConnection();
                    bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result = bufferReader.readLine();
                    return result;

                }catch (Exception e){
                    return null;
                }
            }
        }
        RegisterUser ur= new RegisterUser();
        ur.execute(urlsuffix);
    }
}
