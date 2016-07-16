package mx.unam.desarrolloappsavanzadas.restApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import mx.unam.desarrolloappsavanzadas.restApi.ConstantesRestApi;
import mx.unam.desarrolloappsavanzadas.restApi.Endpoints;
import mx.unam.desarrolloappsavanzadas.restApi.model.MascotaResponse;
import mx.unam.desarrolloappsavanzadas.restApi.deserializador.MascotaDeserializador;

/**
 * Created by Roy on 14/07/2016.
 */
public class RestApiAdapter {

    public Endpoints establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(Endpoints.class);
    }

    public Gson construyendoGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

}
