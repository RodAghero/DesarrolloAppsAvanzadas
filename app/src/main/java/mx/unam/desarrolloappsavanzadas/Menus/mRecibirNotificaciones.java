package mx.unam.desarrolloappsavanzadas.Menus;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdInternalReceiver;

import mx.unam.desarrolloappsavanzadas.R;
import mx.unam.desarrolloappsavanzadas.restApi.Endpoints;
import mx.unam.desarrolloappsavanzadas.restApi.adapter.RestApiAdapter;
import mx.unam.desarrolloappsavanzadas.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class mRecibirNotificaciones extends AppCompatActivity {

    private Toolbar toolbar;

    // private static final String USUARIO = "Rod Aghero";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_recibir_notificaciones);

        toolbar = (Toolbar) findViewById(R.id.actionBar);
        setSupportActionBar(toolbar);
        ((TextView) findViewById(R.id.tvTituloAppbar)).setText("Notificaciones");

    }

    public void lanzarNotificacionLocal(View v){
        Intent intent = new Intent(this, mRecibirNotificaciones.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationCompat = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.dog_footprint_52)
                .setContentTitle("Notificacion")
                .setContentText("Local")
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationCompat.build());

    }

    public void lanzarNotificacionToken(View v) {
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);
    }

    private void enviarTokenRegistro(String token) {
        Log.d("TOKEN", token);
    }

    // Para notificaciones / heroku / firebase

    public void enviarTokenUsuario(View v){

        String token = FirebaseInstanceId.getInstance().getToken();
        //String idUsuario = FirebaseInstanceId.getInstance().getId();

        Log.d("TOKEN_DISPOSITIVO", token);
        //Log.d("ID", idUsuario);

        final UsuarioResponse usuarioResponse = new UsuarioResponse("-KNT3xXZXoqSU8cRGpdu", token, "Rod Aghero");

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Endpoints endpoints = restApiAdapter.establecerConexionRestApiNHF();

        Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenUsuarioID(usuarioResponse.getToken(), usuarioResponse.getIdUsuario());
        //Call<UsuarioResponse> usuarioResponseCall = endpoints.registrarTokenUsuarioID(token, USUARIO);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
// PROBLEMA --> no me permite obtener los datos con el objeto usuarioResponse1, por lo que pasan directo desde usuarioResponse
                // pero por lo mismo no actualiza el id autogenerado por Firebase
                // RESUELTO: se deben tener los mismos nombres en la respuesta de index y en la clase UsuarioResponse
                UsuarioResponse usuarioResponse1 = response.body();

                Log.d("ID_AUTOGENERADO", usuarioResponse1.getId());
                Log.d("TOKEN_ID_DISPOSITIVO", usuarioResponse1.getToken());
                Log.d("ID_USUARIO", usuarioResponse1.getIdUsuario());

            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                Log.d("ERROR", "Error al obtener datos");
            }
        });

    }




}
