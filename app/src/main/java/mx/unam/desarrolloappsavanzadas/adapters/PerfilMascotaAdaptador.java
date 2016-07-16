package mx.unam.desarrolloappsavanzadas.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;
import mx.unam.desarrolloappsavanzadas.R;

/**
 * Created by Roy on 12/07/2016.
 */
public class PerfilMascotaAdaptador extends RecyclerView.Adapter<PerfilMascotaAdaptador.PerfilViewHolder> {

    ArrayList<PerfilMascota> perfilMascotas;
    Activity activity;


    // Constructor
    public PerfilMascotaAdaptador(ArrayList<PerfilMascota> perfilMascotas, Activity activity) {
        this.perfilMascotas = perfilMascotas;
        this.activity = activity;
    }


    // declarando todos los elementos de cardview_mascota
    public static class PerfilViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgFotoPerfilMascotaCV;
        private TextView tvLikesPerfilMascotaCV;
        private ImageView imgHuesoAmarilloPerfilMascotaCV;

        public PerfilViewHolder(View v) {
            super(v);
            imgFotoPerfilMascotaCV          = (ImageView) v.findViewById(R.id.imgFotoPerfilMascotaCV);
            tvLikesPerfilMascotaCV          = (TextView) v.findViewById(R.id.tvLikesPerfilMascotaCV);
            imgHuesoAmarilloPerfilMascotaCV = (ImageView) v.findViewById(R.id.imgHuesoAmarilloPerfilMascotaCV);
        }
    }


    // Métodos heredados
    @Override
    public PerfilMascotaAdaptador.PerfilViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_perfil_mascota, parent, false);
        return new PerfilViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PerfilMascotaAdaptador.PerfilViewHolder perfilViewHolder, int position) {
        PerfilMascota perfilMascota = perfilMascotas.get(position);

        Picasso.with(activity)
                .load(perfilMascota.getUrlFoto())
                .placeholder(R.drawable.grumpy_cat_head)
                .into(perfilViewHolder.imgFotoPerfilMascotaCV);

        perfilViewHolder.tvLikesPerfilMascotaCV.setText(String.valueOf(perfilMascota.getLikes()));

    }

    @Override
    public int getItemCount() {
        return perfilMascotas.size();
    }

}
