package mx.unam.desarrolloappsavanzadas;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.CuentaInstagram;
import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;
import mx.unam.desarrolloappsavanzadas.adapters.PerfilMascotaAdaptador;

import mx.unam.desarrolloappsavanzadas.presentador.IPerfilPresentador;
import mx.unam.desarrolloappsavanzadas.presentador.PerfilPresentador;

public class PerfilFragment extends Fragment implements IPerfilFragment  {

    private RecyclerView rvPerfilMascota;
    private ImageView imgFotoPerfil;
    private TextView tvNombrePerfil;
    private IPerfilPresentador perfilPresentador;
    private Activity activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        activity = getActivity();

        rvPerfilMascota = (RecyclerView) v.findViewById(R.id.rvPerfilMascota);

        imgFotoPerfil = (ImageView) v.findViewById(R.id.imgFotoPerfil);
        tvNombrePerfil = (TextView) v.findViewById(R.id.tvNombrePerfil);

        perfilPresentador = new PerfilPresentador(this, getContext());
        return v;

    }


    // Método para cambio de cuenta
    @Override
    public void onResume(){
        super.onResume();

        if(CuentaInstagram.perfilUsuario != CuentaInstagram.seleccionUsuario) {
            Log.d("MainActivity", "Cambiando de cuenta");
            CuentaInstagram.perfilUsuario = CuentaInstagram.seleccionUsuario;
            perfilPresentador = new PerfilPresentador(this, getContext());
        }

    }


    // Métodos de la interfaz
    @Override
    public void generarGridLayout() {
        GridLayoutManager glm = new GridLayoutManager(getActivity(), 3);
        glm.setOrientation(GridLayoutManager.VERTICAL);
        rvPerfilMascota.setLayoutManager(glm);

        Picasso.with(activity)
                .load(PerfilMascota.fotoUsuario)
                .placeholder(R.drawable.grumpy_cat_head)
                .into(imgFotoPerfil);

        tvNombrePerfil.setText(PerfilMascota.nombreUsuario);
    }

    @Override
    public PerfilMascotaAdaptador crearAdaptador(ArrayList<PerfilMascota> perfilMascotas) {
        PerfilMascotaAdaptador adaptador = new PerfilMascotaAdaptador(perfilMascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(PerfilMascotaAdaptador adaptador) {
        rvPerfilMascota.setAdapter(adaptador);
    }
}
