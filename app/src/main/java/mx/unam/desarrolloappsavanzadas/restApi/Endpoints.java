package mx.unam.desarrolloappsavanzadas.restApi;

import mx.unam.desarrolloappsavanzadas.restApi.model.MascotaResponse;
import mx.unam.desarrolloappsavanzadas.restApi.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Roy on 14/07/2016.
 */
public interface Endpoints {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia(@Path("user-id") String userId);

    // Para notificaciones / heroku / firebase

    @FormUrlEncoded
    @POST(ConstantesRestApi.KEY_POST_ID_TOKEN_USUARIO)

    Call<UsuarioResponse> registrarTokenUsuarioID(@Field("token") String token, @Field("idUsuario") String idUsuario);

}
