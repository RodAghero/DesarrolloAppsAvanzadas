package mx.unam.desarrolloappsavanzadas.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;
import mx.unam.desarrolloappsavanzadas.R;
import mx.unam.desarrolloappsavanzadas.restApi.Endpoints;
import mx.unam.desarrolloappsavanzadas.restApi.adapter.RestApiAdapter;
import mx.unam.desarrolloappsavanzadas.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        perfilViewHolder.imgHuesoAmarilloPerfilMascotaCV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(activity, "Diste un hueso", Toast.LENGTH_SHORT).show();
                // Método para dar like/hueso
                Log.d("LIKE_HUESO", "true");

                // La cuenta que configure aquí será la que de el hueso (like)
                //UsuarioResponse usuarioResponse4 = new UsuarioResponse("-KPxsb3r9LiyBfybhf2w", "-KPxsb3r9LiyBfybhf2w", "Rod Aghero", "1290628472327085000_3502625180");
                UsuarioResponse usuarioResponse4 = new UsuarioResponse("-KPxuszIj9eXAwYcz10f", "-KPxuszIj9eXAwYcz10f", "gaby_petfly", "1286542696915236916_3470121575");

                RestApiAdapter restApiAdapter = new RestApiAdapter();
                Endpoints endpoints1 = restApiAdapter.establecerConexionRestApiNHF();

                Call<UsuarioResponse> usuarioResponseCall = endpoints1.likeHueso(usuarioResponse4.getId(), usuarioResponse4.getIdUsuario());

                usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
                    @Override
                    public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                        UsuarioResponse usuarioResponse5 = response.body();
                        Log.d("ID_AUTOGENERADO", usuarioResponse5.getId());
                        Log.d("TOKEN_ID_DISPOSITIVO", usuarioResponse5.getToken());
                        Log.d("ID_USUARIO", usuarioResponse5.getIdUsuario());
                        //Log.d("ID_FOTO", usuarioResponse5.getIdFoto());
                    }

                    @Override
                    public void onFailure(Call<UsuarioResponse> call, Throwable t) {

                    }
                });

            }
        });

    }

    @Override
    public int getItemCount() {
        return perfilMascotas.size();
    }

}
