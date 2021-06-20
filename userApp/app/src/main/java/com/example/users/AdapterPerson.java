package com.example.users;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterPerson extends RecyclerView.Adapter<AdapterPerson.ViewHolderP> {
    ArrayList<User> users;
    private Context context;

    public AdapterPerson(ArrayList<User> users, Context context) {
        this.users = users;
        this.context = context;
        Log.d("MENSAJEEE", users.toString());
    }

    @NonNull
    @Override
    public ViewHolderP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        int layoutId=R.layout.user_card;
        LayoutInflater inflaterlayout= LayoutInflater.from(context);
        View view=inflaterlayout.inflate(layoutId,parent,false);
        ViewHolderP viewHol=new ViewHolderP(view);

        return viewHol;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderP holder, int position) {
        holder.datos(users.get(position));

    }



    @Override
    public int getItemCount() {
        return users.size();
    }
    public class ViewHolderP extends RecyclerView.ViewHolder {

        TextView nombre,correo;

        public ViewHolderP(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.card_name);
            correo=itemView.findViewById(R.id.card_correo);

        }


        public void datos(User user) {
            nombre.setText(user.getName());
            correo.setText(user.getUsername());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i= new Intent(context,actualizar_info.class);
                    i.putExtra("name",nombre.getText().toString());
                    i.putExtra("email",correo.getText().toString());
                    context.startActivity(i);



                }
            });
        }
    }
}