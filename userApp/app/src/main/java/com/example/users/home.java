package com.example.users;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class home extends AppCompatActivity {
    ArrayList<User> users= new ArrayList<>();
    RecyclerView recyclerView;
    AdapterPerson adapterPerson;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setTitle("User list");
        recyclerView=findViewById(R.id.recycler_users);
         users.add(new User("ezequiel","ezequiel@gmail.com"));
        users.add(new User("fernanda","fer245s@gmail.com"));
        users.add(new User("monica","ramirez_mon13@gmail.com"));
        users.add(new User("jorge","jorge45@gmail.com"));
        users.add(new User("manuel","manuel@gmail.com"));
        users.add(new User("lorena","lorena@gmail.com"));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        LinearLayoutManager lm=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        adapterPerson= new AdapterPerson(users,this);
        recyclerView.setAdapter(adapterPerson);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_users, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.about:
                Intent in=new Intent(home.this,about.class);
                startActivity(in);
                return true;
            case R.id.logout:
                Intent inte=new Intent(home.this,MainActivity.class);
                startActivity(inte);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}