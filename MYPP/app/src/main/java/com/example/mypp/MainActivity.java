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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public Button btn_Kayıt;
    public Button btn_Giris;
    public FirebaseAuth fAuth;
    public EditText edtEmail,edtSifre;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fAuth = FirebaseAuth.getInstance();
        edtEmail = findViewById(R.id.girisKullanıcıAdi);
        edtSifre = findViewById(R.id.girissParola);
        btn_Giris=findViewById(R.id.btnGiris);
        btn_Kayıt=findViewById(R.id.btnKayitOl);

        btn_Kayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent kayit = new Intent(MainActivity.this,MainKayitOl.class);
                startActivity(kayit);
            }
        });

        btn_Giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = edtEmail.getText().toString().trim();
                password = edtSifre.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    edtEmail.setError("Email Alanı Boş Olamaz");
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    edtSifre.setError(("Parola Boş Olamaz"));
                }
                else {
                    girisYap();
                }
            }
        });


            }

    private void girisYap() {
        fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> girisGorevi) {
                if(girisGorevi.isSuccessful())
                {
                startActivity(new Intent(MainActivity.this,MainHosgeldiniz.class));
                finish();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception hata) {
                Toast.makeText(MainActivity.this,""+hata,Toast.LENGTH_SHORT).show();

            }
        });
    }


}
