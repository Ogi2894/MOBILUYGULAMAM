package com.example.mypp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainKayitOl extends AppCompatActivity {

    public Button btn_Kaydet;
    public Button btn_GiriseDon;
    public EditText txtEmail ,txtPassword;
    public FirebaseAuth fAuth;
    String email;
    String password;


     public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_kayit_ol);

        txtEmail = (EditText) findViewById(R.id.kayitKullaniciAdi);
        txtPassword = (EditText) findViewById(R.id.kayitParola);
        fAuth = FirebaseAuth.getInstance();
        btn_Kaydet=findViewById(R.id.btnKaydet);


        btn_Kaydet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)) {
                    txtEmail.setError("Email Boş Olamaz !");
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    txtPassword.setError("Paralo Boş Olamaz !");
                    return;
                }

                if(password.length() < 6){
                    txtPassword.setError("Şifreniz 6 Karkterli Olmalıdır");
                    return;
                }
                else {
                    kaydol();

                }

            }
        });


        btn_GiriseDon=findViewById(R.id.btnGiriseDon);
        btn_GiriseDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent girisdon = new Intent(MainKayitOl.this,MainActivity.class);
            startActivity(girisdon);
            }
        });
    }
    private void kaydol (){
         fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
             @Override
             public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful())
                 {
                     startActivity(new Intent(MainKayitOl.this,MainActivity.class));
                     finish();
                 }

             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception hata) {
                 Toast.makeText(MainKayitOl.this, ""+hata, Toast.LENGTH_LONG).show();

             }
         });
    }

}