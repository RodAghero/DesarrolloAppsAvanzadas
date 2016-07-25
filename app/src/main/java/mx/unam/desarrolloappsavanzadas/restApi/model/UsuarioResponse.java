package mx.unam.desarrolloappsavanzadas.restApi.model;

/**
 * Created by Roy on 24/07/2016.
 */
public class UsuarioResponse {

    private String idAutoGenerado;
    private String token;           // id_dispositivo
    private String idUsuario;

    // Constructores
    public UsuarioResponse(String idAutoGenerado, String token, String idUsuario) {
        this.idAutoGenerado = idAutoGenerado;
        this.token = token;
        this.idUsuario = idUsuario;
    }

    public UsuarioResponse() {

    }

    // Getters, Setters
    public String getIdAutoGenerado() {
        return idAutoGenerado;
    }

    public void setIdAutoGenerado(String idAutoGenerado) {
        this.idAutoGenerado = idAutoGenerado;
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
