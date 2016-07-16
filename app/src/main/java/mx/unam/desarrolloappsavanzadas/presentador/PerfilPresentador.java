package mx.unam.desarrolloappsavanzadas.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.IPerfilFragment;
import mx.unam.desarrolloappsavanzadas.Model.CuentaInstagram;
import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;
import mx.unam.desarrolloappsavanzadas.presentador.IPerfilPresentador;
import mx.unam.desarrolloappsavanzadas.restApi.Endpoints;
import mx.unam.desarrolloappsavanzadas.restApi.adapter.RestApiAdapter;
import mx.unam.desarrolloappsavanzadas.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Roy on 14/07/2016.
 */
public class PerfilPresentador implements IPerfilPresentador {

    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ArrayList<PerfilMascota> perfilMascotas;
    private static final String TAG = "MainActivity";
    private CuentaInstagram cuentaInstagram;

    // Constructor
    public PerfilPresentador(IPerfilFragment iPerfilFragment, Context context) {
        this.iPerfilFragment = iPerfilFragment;
        this.context = context;

        if(CuentaInstagram.listaCuentas.size() == 0) {
            obtenerPerfil();
        }

        obtenerMediosRecientes();
    }


    @Override
    public void obtenerPerfil() {

        CuentaInstagram.perfilUsuario = "3502625180";

        cuentaInstagram = new CuentaInstagram("3502625180", "rodaghero7102", "Rod Aghero", "https://scontent.cdninstagram.com/t51.2885-19/s150x150/13549451_1924392194454213_640488733_a.jpg");
        cuentaInstagram = new CuentaInstagram("3470121575", "gaby_petfly", "Gaby Nuñez", "https://scontent.cdninstagram.com/t51.2885-19/s150x150/13557166_1637974566521422_1967247359_a.jpg");
        cuentaInstagram = new CuentaInstagram("1367162880", "flyn139566", "Isaac", "https://scontent.cdninstagram.com/t51.2885-19/s150x150/13534406_1048087365282770_1025401511_a.jpg");

    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyendoGsonDeserializadorMediaRecent();
        Endpoints endpoints = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        Call<MascotaResponse> mascotaResponseCall = endpoints.getRecentMedia(CuentaInstagram.perfilUsuario);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                Log.v(TAG, "Conexión establecida");
                MascotaResponse mascotaResponse = response.body();
                perfilMascotas = mascotaResponse.getPerfilMascotas();
                mostrarPerfilMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Problema de conexión, intenta de nuevo", Toast.LENGTH_LONG).show();
                Log.e("FALLO EN LA CONEXIÓN", t.toString());

            }
        });


    }

    @Override
    public void mostrarPerfilMascotasRV() {
        iPerfilFragment.inicializarAdaptadorRV(iPerfilFragment.crearAdaptador(perfilMascotas));
        iPerfilFragment.generarGridLayout();
    }

}
