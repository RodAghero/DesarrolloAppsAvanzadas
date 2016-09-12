package mx.unam.desarrolloappsavanzadas;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;

import mx.unam.desarrolloappsavanzadas.Menus.mConfigurarCuenta;
import mx.unam.desarrolloappsavanzadas.restApi.Endpoints;
import mx.unam.desarrolloappsavanzadas.restApi.adapter.RestApiAdapter;
import mx.unam.desarrolloappsavanzadas.restApi.model.UsuarioResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PUT;



/**
 * Created by Roy on 11/09/2016.
 */
public class AccionesNotificacion extends BroadcastReceiver {

    public Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;

        String ACTION_KEY_01 = "VER_PERFIL";
        String ACTION_KEY_02 = "FOLLOW";
        String ACTION_KEY_03 = "VER_USUARIO";

        String accion = intent.getAction();


        if (ACTION_KEY_01.equals(accion)) {
            Toast.makeText(context, "Ver perfil", Toast.LENGTH_LONG).show();
            verPerfil();

        } else if (ACTION_KEY_02.equals(accion)) {
            Toast.makeText(context, "Follow", Toast.LENGTH_LONG).show();
            followUser();

        } else if (ACTION_KEY_03.equals(accion)) {
            Toast.makeText(context, "Elige tu usuario", Toast.LENGTH_LONG).show();
            verUsuario();
        }

    }

    public void verPerfil() {
        Intent intentVerPerfil = new Intent(context, MainActivity.class);
        //PendingIntent pendingIntentVerPerfil = PendingIntent.getActivity(context, 0, intentVerPerfil, PendingIntent.FLAG_ONE_SHOT);
        //startActivity(intentVerPerfil);
    // NOTA por alguna razón no funciona startActivity para usar el intent
    }


    public void followUser() {
        Log.d("SEGUIR USUARIO", "true");

        // La cuenta que configure aquí RECIBE EL HUESO (la que puedes seguir) --------->

        UsuarioResponse usuarioResponse4 = new UsuarioResponse("-KRQ1DTNnlNvDNx5sDR6", "e9icf4Q4BuA:A", "Rod Aghero", "1290628472327085000_3502625180");
        //UsuarioResponse usuarioResponse4 = new UsuarioResponse("-KRQ2zeKG3xyEMCv03vK", "cUKAkDeV7aQ:AP", "gaby_petfly", "1286542696915236916_3470121575");

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionRestApiInsagramFollow();
        Call<ResponseBody> responseBodyCall = endpoints.followUser(usuarioResponse4.getId(), "follow");

        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody responseBody = response.body();
                String message = response.code() + " : " + response.message();
                Log.d("INSTAGRAM CODE:", Integer.toString(response.code()));
                Log.i("INSTAGRAM MESSAGE:", message);

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("INSTAGRAM FOLLOW", "ERROR");
            }
        });

    }


    public void verUsuario() {
        Intent intentVerUsuario = new Intent(context, mConfigurarCuenta.class);
        //startActivity(intentVerUsuario);
    }


}
