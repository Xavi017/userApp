package com.example.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class actualizar_info extends AppCompatActivity {
    Button btn_actualizar;
    Spinner sp_genero,sp_ciudades;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_info);
        btn_actualizar=findViewById(R.id.btn_actualizar);
        sp_ciudades=findViewById(R.id.sp_paises);

        sp_genero=findViewById(R.id.sp_genero);

        String[]paises={"España","Bolivia","peru","colombia","Nincaragua","Italia","Brasil"};
        String[]genero={"Male","Female"};
        sp_ciudades.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,paises));
        sp_genero.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,genero));

        btn_actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(actualizar_info.this,home.class);
                startActivity(i);
            }
        });
    }
}