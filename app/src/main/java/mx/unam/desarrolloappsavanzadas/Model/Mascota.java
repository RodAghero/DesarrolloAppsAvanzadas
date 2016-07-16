package mx.unam.desarrolloappsavanzadas.Model;

/**
 * Created by Roy on 12/07/2016.
 */
public class Mascota {

    private int id;
    private String nombre;
    private int foto;
    private int likes;

    // Constructores
    public Mascota(){

    }

    public Mascota(String nombre, int foto){
        this.nombre = nombre;
        this.foto = foto;
    }

    public Mascota(int id, String nombre, int foto, int likes) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.likes = likes;
    }

    // Gets y Sets
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
