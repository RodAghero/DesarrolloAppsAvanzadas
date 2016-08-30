package mx.unam.desarrolloappsavanzadas.restApi;

/**
 * Created by Roy on 14/07/2016.
 */
public class ConstantesRestApi {

    public static final String VERSION      = "/v1/";
    public static final String ROOT_URL     = "https://api.instagram.com" + VERSION;
    public static final String ACCESS_TOKEN = "3502625180.755806d.df37b1a407bf439cb4f5e6f3418f2aa9";

    // https://api.instagram.com/v1/users/{user-id}/media/recent/?access_token=ACCESS-TOKEN
    public static final String KEY_ACCESS_TOKEN             = "?access_token=";
    public static final String KEY_GET_RECENT_MEDIA_USER    = "users/{user-id}/media/recent/";

    // url completo
    public static final String URL_GET_RECENT_MEDIA_USER = KEY_GET_RECENT_MEDIA_USER + KEY_ACCESS_TOKEN + ACCESS_TOKEN;

    // Para notificaciones / heroku / firebase

    public static final String ROOT_URL_NHF = "https://safe-shelf-98430.herokuapp.com/";
    public static final String KEY_POST_ID_TOKEN_USUARIO = "registrar-usuario/";

    // Para like-hueso  (usa idAutogenerado y idUsuario)
    public static final String KEY_LIKE_HUESO = "like-hueso/{id}/{idUsuario}/";

}
