package com.meuf.sosyalkulup.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.meuf.sosyalkulup.Fragments.MainActivity;
import com.meuf.sosyalkulup.R;

public class Login extends AppCompatActivity {

    private EditText inputschoolno, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button btnSignup, btnLogin, btnReset;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser()!= null){
            finish();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        setContentView(R.layout.activity_login);
        inputschoolno = (EditText) findViewById(R.id.schoolno);
        inputPassword = (EditText) findViewById(R.id.password);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignup = (Button) findViewById(R.id.btn_signup);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnReset = (Button) findViewById(R.id.btn_reset_password);

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, SignUp.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ResetPasswordActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String schoolno = inputschoolno.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(schoolno)) {
                    Toast.makeText(getApplicationContext(), "Öğrenci numaranızı giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Şifrenizi Giriniz!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //progressBar.setVisibility(View.VISIBLE);

                //authenticate user
                auth.signInWithEmailAndPassword(schoolno + "@dogus.edu.tr", password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseUser user = auth.getCurrentUser();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                //progressBar.setVisibility(View.GONE);
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        inputPassword.setError("Lütfen, En Az 6 Haneli Bir Şifre Giriniz!");
                                    } else {
                                        Toast.makeText(Login.this, "Giriş Başarısız. \n Lütfen Okul Numaranızı Kontrol Ediniz.", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    try{
                                        if(user.isEmailVerified()){
                                            Intent intent = new Intent(Login.this, MainActivity.class);
                                            startActivity(intent);
                                            finish();
                                        }else
                                            Toast.makeText(Login.this,"Email Adresi Doğrulanmamış. \n Gelen Kutunuzu Kontrol Edin.",Toast.LENGTH_SHORT).show();
                                    }catch (NullPointerException e){

                                    }
                                }
                            }
                        });
            }
        });
    }
}
