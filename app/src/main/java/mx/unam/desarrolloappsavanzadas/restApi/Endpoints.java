package mx.unam.desarrolloappsavanzadas.restApi;

import mx.unam.desarrolloappsavanzadas.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Roy on 14/07/2016.
 */
public interface Endpoints {

    @GET(ConstantesRestApi.URL_GET_RECENT_MEDIA_USER)
    Call<MascotaResponse> getRecentMedia(@Path("user-id") String userId);

}
