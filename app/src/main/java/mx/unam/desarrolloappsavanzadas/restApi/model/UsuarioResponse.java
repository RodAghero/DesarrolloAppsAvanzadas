package mx.unam.desarrolloappsavanzadas.restApi.model;

/**
 * Created by Roy on 24/07/2016.
 */
public class UsuarioResponse {

    private String id;
    private String token;           // id_dispositivo
    private String idUsuario;

    // Constructores
    public UsuarioResponse(String id, String token, String idUsuario) {
        this.id = id;
        this.token = token;
        this.idUsuario = idUsuario;
    }

    public UsuarioResponse() {

    }

    // Getters, Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

}
