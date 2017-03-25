package com.meuf.sosyalkulup;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignUp extends Activity {

    EditText edit_username = (EditText) findViewById(R.id.id_username);
    EditText edit_email = (EditText) findViewById(R.id.id_email);
    EditText edit_pass = (EditText) findViewById(R.id.id_pass);
    Button btn_sign = (Button) findViewById(R.id.btn_signup);
    private static final String REGISTER_URL="http://sosyalkulup.hol.es/UserRegistration/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        Intent intent = getIntent();

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = edit_username.getText().toString().trim().toLowerCase();
        String email = edit_email.getText().toString().trim().toLowerCase();
        String password = edit_pass.getText().toString().trim().toLowerCase();
        register(username,email,password);
    }
    private  void register(String username,String password,String email){
        String urlsuffix = "?username=" + username +"&password=" + password + "&email=" + email;
        class RegisterUser extends AsyncTask<String,Void,String>{
            ProgressDialog loading;

            @Override
            protected void onPreExecute(){
                super.onPreExecute();
                loading = ProgressDialog.show(SignUp.this,"Lütfen Bekleyiniz",null, true,true);
            }
            @Override
            protected void onPostExecute(String s){
                super.onPostExecute(s);
                Toast.makeText(getApplicationContext(),"Internet bağlantınızı kontrol ediniz.",Toast.LENGTH_SHORT).show();
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
