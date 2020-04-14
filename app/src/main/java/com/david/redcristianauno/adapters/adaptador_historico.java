package com.david.redcristianauno.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.david.redcristianauno.POJOs.RegistroCelula;
import com.david.redcristianauno.R;

import java.util.ArrayList;

public class adaptador_historico extends RecyclerView.Adapter<adaptador_historico.ViewHolderDatos> {

    Context c;
    ArrayList<RegistroCelula> listregistro;

    public adaptador_historico(Context c, ArrayList<RegistroCelula> listregistro) {
        this.c = c;
        this.listregistro = listregistro;
    }

    @NonNull
    @Override
    public adaptador_historico.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recicler_card_view,null,false);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptador_historico.ViewHolderDatos holder, int i) {
        holder.txtnombre.setText(listregistro.get(i).getNombre_usuario());
        holder.txtnombreAnfitrion.setText("Anfitrion: " + listregistro.get(i).getNombre_anfitrion());
        holder.txtasistencia.setText("Asistencia: " + listregistro.get(i).getAsistencia_celula());
        holder.txtinvitados.setText("Invitados: " + listregistro.get(i).getInvitados_celula());
        holder.txtninos.setText("Ninos: " + listregistro.get(i).getNinos_celula());
        holder.txtofrenda.setText("Ofrenda: " + listregistro.get(i).getOfrenda_celula());
        //holder.txtnombre_subred.setText("Subred: " + listregistro.get(i).getNombre_subred_card());
        holder.txtnombreDireccion.setText("Celula: " + listregistro.get(i).getDomicilio());
    }

    @Override
    public int getItemCount() {
        return listregistro.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        private TextView txtnombre, txtnombreAnfitrion, txtasistencia, txtinvitados,txtninos, txtofrenda, txtnombreDireccion, txtnombre_subred;

        public ViewHolderDatos(@NonNull View itemView) {

            super(itemView);

            txtnombre = itemView.findViewById(R.id.rc_txtnombre_usuario);
            txtnombreAnfitrion = itemView.findViewById(R.id.rc_txtnombre_anfitrion);
            txtasistencia = itemView.findViewById(R.id.rc_txt_asistencia);
            txtinvitados = itemView.findViewById(R.id.rc_txt_invitados);
            txtninos = itemView.findViewById(R.id.rc_txt_niños);
            txtofrenda = itemView.findViewById(R.id.rc_txt_ofrenda);
            txtnombreDireccion = itemView.findViewById(R.id.rc_txt_celula);
            txtnombre_subred = itemView.findViewById(R.id.rc_txt_subred);


        }

    }
}
