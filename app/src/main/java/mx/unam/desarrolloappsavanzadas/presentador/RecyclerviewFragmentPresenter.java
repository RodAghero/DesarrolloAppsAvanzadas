package mx.unam.desarrolloappsavanzadas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.IRecyclerviewFragment;
import mx.unam.desarrolloappsavanzadas.Model.ConstructorMascotas;
import mx.unam.desarrolloappsavanzadas.Model.CuentaInstagram;
import mx.unam.desarrolloappsavanzadas.Model.Mascota;
import mx.unam.desarrolloappsavanzadas.presentador.IRecyclerviewFragmentPresenter;
import mx.unam.desarrolloappsavanzadas.restApi.Endpoints;
import mx.unam.desarrolloappsavanzadas.restApi.adapter.RestApiAdapter;
import mx.unam.desarrolloappsavanzadas.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roy on 13/07/2016.
 */
public class RecyclerviewFragmentPresenter implements IRecyclerviewFragmentPresenter {

    private IRecyclerviewFragment iRecyclerviewFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;
    private CuentaInstagram cuentaInstagram;
    private static final String TAG = "MainActivity";

    public RecyclerviewFragmentPresenter(IRecyclerviewFragment iRecyclerviewFragment, Context context) {
        this.iRecyclerviewFragment = iRecyclerviewFragment;
        this.context = context;

    }

    // Métodos de la interfa<
    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }


    @Override
    public void mostrarMascotasRV() {
        iRecyclerviewFragment.inicializarAdaptadorRV(iRecyclerviewFragment.crearAdaptador(mascotas));
        iRecyclerviewFragment.generarLinearLayoutVertical();
    }
}
