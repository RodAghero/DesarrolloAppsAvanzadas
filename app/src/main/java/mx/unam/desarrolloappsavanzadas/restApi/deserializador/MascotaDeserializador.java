package mx.unam.desarrolloappsavanzadas.restApi.deserializador;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;
import mx.unam.desarrolloappsavanzadas.restApi.JsonKeys;
import mx.unam.desarrolloappsavanzadas.restApi.model.MascotaResponse;

/**
 * Created by Roy on 14/07/2016.
 */
public class MascotaDeserializador implements JsonDeserializer<MascotaResponse> {

    @Override
    public MascotaResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Gson gson = new Gson();
        MascotaResponse mascotaResponse     = gson.fromJson(json, MascotaResponse.class);
        JsonArray mascotaResponseData       = json.getAsJsonObject().getAsJsonArray(JsonKeys.MEDIA_RESPONSE_ARRAY);

        mascotaResponse.setPerfilMascotas(deserializarMascotasDeJson(mascotaResponseData));
        return mascotaResponse;
    }

    private ArrayList<PerfilMascota> deserializarMascotasDeJson(JsonArray mascotaResponseData){
        ArrayList<PerfilMascota> perfilMascotas = new ArrayList<>();

        for(int i = 0; i < mascotaResponseData.size(); i++){
            JsonObject mascotaResponseDataObject = mascotaResponseData.get(i).getAsJsonObject();

            JsonObject userJson      = mascotaResponseDataObject.getAsJsonObject(JsonKeys.USER);

            if( i == 0) {

                String idUsuario                = userJson.get(JsonKeys.USER_ID).getAsString();
                String nombreCompletoUsuario    = userJson.get(JsonKeys.USER_FULL_NAME).getAsString();
                String fotoUsuario              = userJson.get(JsonKeys.USER_PICTURE).getAsString();

                PerfilMascota.idUsuario         = idUsuario;
                PerfilMascota.nombreUsuario     = nombreCompletoUsuario;
                PerfilMascota.fotoUsuario       = fotoUsuario;

            }

            JsonObject imageJson            = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_IMAGES);
            JsonObject stdResolutionJson    = imageJson.getAsJsonObject(JsonKeys.MEDIA_STANDARD_RESOLUTION);
            String urlFoto                  = stdResolutionJson.get(JsonKeys.MEDIA_URL).getAsString();

            JsonObject likesJson    = mascotaResponseDataObject.getAsJsonObject(JsonKeys.MEDIA_LIKES);
            int likes               = likesJson.get(JsonKeys.MEDIA_LIKES_COUNT).getAsInt();

            PerfilMascota perfilMascotaActual = new PerfilMascota();

            perfilMascotaActual.setUrlFoto(urlFoto);
            perfilMascotaActual.setLikes(likes);

            perfilMascotas.add(perfilMascotaActual);

        }
        return perfilMascotas;
    }


}
