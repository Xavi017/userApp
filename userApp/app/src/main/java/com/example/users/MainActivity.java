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

public class MainActivity extends AppCompatActivity {
    Button btn_login,btn_register;
    EditText username,pass;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        btn_login=findViewById(R.id.btn_login);
        btn_register=findViewById(R.id.btn_register);
        username=findViewById(R.id.username);
        pass=findViewById(R.id.password);
        shared=getSharedPreferences("usuario", Context.MODE_PRIVATE);
        String user_cache=shared.getString("username","");
        String pass_cache=shared.getString("password","");


      btn_register.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent i=new Intent(MainActivity.this,register.class);
              startActivity(i);
          }
      });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!username.getText().toString().equals("")&&!pass.getText().toString().equals("")){

                    if(username.getText().toString().equals(user_cache)&&pass.getText().toString().equals(pass_cache)){
                        Intent in=new Intent(MainActivity.this,home.class);
                        startActivity(in);
                    }
                }else{
                    Toast.makeText(MainActivity.this, "Completa los datos", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}