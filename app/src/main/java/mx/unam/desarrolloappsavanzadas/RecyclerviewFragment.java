package mx.unam.desarrolloappsavanzadas;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.Mascota;
import mx.unam.desarrolloappsavanzadas.adapters.MascotaAdaptador;
import mx.unam.desarrolloappsavanzadas.presentador.IRecyclerviewFragmentPresenter;
import mx.unam.desarrolloappsavanzadas.presentador.RecyclerviewFragmentPresenter;


public class RecyclerviewFragment extends Fragment implements IRecyclerviewFragment {


    public RecyclerviewFragment(){

    }

    ArrayList<Mascota> mascotas;
    private RecyclerView rvListaMascotas;
    private IRecyclerviewFragmentPresenter presenter;
    private Activity activity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        activity = getActivity();

        rvListaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);

        presenter = new RecyclerviewFragmentPresenter(this, getContext());

        return v;

    }

    // Métodos de la interfaz
    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rvListaMascotas.setLayoutManager(llm);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador= new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvListaMascotas.setAdapter(adaptador);
    }
}
