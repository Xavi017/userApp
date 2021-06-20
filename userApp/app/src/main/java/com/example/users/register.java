package com.example.users;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText name,lastname,username,pass,conf_pass;
    Button btn_register;
    String name_s,lastname_s,username_s,pass_s,conf_pass_s;
    SharedPreferences shared;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        name=findViewById(R.id.register_name);
        lastname=findViewById(R.id.register_lastname);
        username=findViewById(R.id.register_username);
        pass=findViewById(R.id.register_pass);
        conf_pass=findViewById(R.id.register_conf_pass);
        btn_register=findViewById(R.id.btn_register);





        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrarUsuarios();
            }
        });

    }

    private void registrarUsuarios() {
        name_s=name.getText().toString();
        lastname_s=lastname.getText().toString();
        username_s=username.getText().toString();
        pass_s=pass.getText().toString();
        conf_pass_s=conf_pass.getText().toString();

        if(!name_s.equals("")&&!lastname_s.equals("")&&!username_s.equals("")&&!pass_s.equals("")&&!conf_pass_s.equals("")){
            if(pass_s.equals(conf_pass_s)){
                shared=getSharedPreferences("usuario", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=shared.edit();
                editor.putString("username",username_s);
                editor.putString("password",pass_s);
                editor.commit();
                editor.clear();
                Intent i= new Intent(register.this,MainActivity.class);
                startActivity(i);
            }else{
                Toast.makeText(this, "Contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Complete los datos", Toast.LENGTH_SHORT).show();
        }

    }
}