package com.example.mypp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class MainHosgeldiniz extends AppCompatActivity {
     public Button btn_kayıtet;
     public EditText k_email,k_adsoyad,k_tarıh,k_hatno,k_sıkayet;
     public Firebase RealtimeDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_hosgeldiniz);
        btn_kayıtet =findViewById(R.id.btnKayitOlustur);
        k_adsoyad = findViewById(R.id.editTextAdSoyad);
        k_tarıh = findViewById(R.id.editTextTarıh);
        k_sıkayet = findViewById(R.id.editTextTextSıkayet);

        btn_kayıtet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String adsoyad = k_adsoyad.getText().toString().trim();
                String tarıh = k_tarıh.getText().toString().trim();
                String sıkayet = k_sıkayet.getText().toString().trim();

                if(TextUtils.isEmpty(adsoyad)) {
                    k_adsoyad.setError("Bu Alan Boş Olamaz !");
                    return;
                }
                if(TextUtils.isEmpty(tarıh)) {
                    k_tarıh.setError("Email Boş Olamaz !");
                    return;
                }
                if(TextUtils.isEmpty(sıkayet)) {
                    k_sıkayet.setError("Email Boş Olamaz !");
                    return;
                }
                else {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("veriler");

                    myRef.setValue(sıkayet);

                }



            }
        });
    }

}