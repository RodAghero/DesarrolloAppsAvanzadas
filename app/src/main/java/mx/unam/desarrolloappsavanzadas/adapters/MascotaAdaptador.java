package mx.unam.desarrolloappsavanzadas.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.ConstructorMascotas;
import mx.unam.desarrolloappsavanzadas.Model.Mascota;
import mx.unam.desarrolloappsavanzadas.R;

/**
 * Created by Roy on 12/07/2016.
 */
public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;
    ConstructorMascotas constructorMascotas;


    // Constructor
    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }


    // declarando todos los elementos de cardview_mascota
    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView   imgFotoMascotaCV;
        private ImageView   imgHuesoBlancoMascotaCV;
        private TextView    tvnombreMascotaCV;
        private TextView    tvLikesMascotaCV;
        private ImageButton imgHuesoAmarilloMascotaCV;

            public MascotaViewHolder(View v) {
                super(v);
                imgFotoMascotaCV          = (ImageView) v.findViewById(R.id.imgFotoMascotaCV);
                imgHuesoBlancoMascotaCV   = (ImageView) v.findViewById(R.id.imgHuesoBlancoMascotaCV);
                tvnombreMascotaCV         = (TextView) v.findViewById(R.id.tvNombreMascotaCV);
                tvLikesMascotaCV          = (TextView) v.findViewById(R.id.tvLikesMascotaCV);
                imgHuesoAmarilloMascotaCV = (ImageButton) v.findViewById(R.id.imgHuesoAmarilloMascotaCV);
            }
    }


    // Métodos heredados
    @Override
    public MascotaAdaptador.MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascotas, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaAdaptador.MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);


        mascotaViewHolder.imgFotoMascotaCV.setImageResource(mascota.getFoto());
        mascotaViewHolder.tvnombreMascotaCV.setText(mascota.getNombre());
        mascotaViewHolder.tvLikesMascotaCV.setText(Integer.toString(mascota.getLikes()));

        mascotaViewHolder.imgHuesoAmarilloMascotaCV.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(activity, "Diste like a " + mascota.getNombre(), Toast.LENGTH_LONG).show();

                ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                constructorMascotas.darLikeMascota(mascota);

                mascotaViewHolder.tvLikesMascotaCV.setText(Integer.toString(constructorMascotas.obtenerLikesMascota(mascota)));

            }
        });



    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }
}
