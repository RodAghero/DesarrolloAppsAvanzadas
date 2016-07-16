package mx.unam.desarrolloappsavanzadas.Model;

/**
 * Created by Roy on 13/07/2016.
 */
public class PerfilMascota {
    private String urlFoto;
    private int likes;
    public static String idUsuario;
    public static String nombreUsuario;
    public static String fotoUsuario;

    // Constructores
    public PerfilMascota(){

    }

    public PerfilMascota(String urlFoto){
        this.urlFoto = urlFoto;
    }

    public PerfilMascota(String urlFoto, int likes){
        this.urlFoto = urlFoto;
        this.likes = likes;
    }

    // Gets y Sets
    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public static String getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(String idUsuario) {
        PerfilMascota.idUsuario = idUsuario;
    }

    public static String getNombreUsuario() {
        return nombreUsuario;
    }

    public static void setNombreUsuario(String nombreUsuario) {
        PerfilMascota.nombreUsuario = nombreUsuario;
    }

    public static String getFotoUsuario() {
        return fotoUsuario;
    }

    public static void setFotoUsuario(String fotoUsuario) {
        PerfilMascota.fotoUsuario = fotoUsuario;
    }
}
