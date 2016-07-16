package mx.unam.desarrolloappsavanzadas.Model;

import java.util.ArrayList;

/**
 * Created by Roy on 14/07/2016.
 */
public class CuentaInstagram {

    private String idUsuario;
    private String nombreUsuario;
    private String nombreCompletoUsuario;
    private String fotoUsuario;
    public static ArrayList<Object> listaCuentas = new ArrayList<Object>();
    public static String perfilUsuario;
    public static String seleccionUsuario;

    // Constructor
    public CuentaInstagram(String idUsuario, String nombreUsuario, String nombreCompletoUsuario, String fotoUsuario) {
        setItem(this);
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.nombreCompletoUsuario = nombreCompletoUsuario;
        this.fotoUsuario = fotoUsuario;
    }

    // Gets y Sets
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCompletoUsuario() {
        return nombreCompletoUsuario;
    }

    public void setNombreCompletoUsuario(String nombreCompletoUsuario) {
        this.nombreCompletoUsuario = nombreCompletoUsuario;
    }

    public String getFotoUsuario() {
        return fotoUsuario;
    }

    public void setFotoUsuario(String fotoUsuario) {
        this.fotoUsuario = fotoUsuario;
    }

    public static ArrayList<Object> getListaCuentas() {
        return listaCuentas;
    }

    public static void setListaCuentas(ArrayList<Object> listaCuentas) {
        CuentaInstagram.listaCuentas = listaCuentas;
    }

    public static String getPerfilUsuario() {
        return perfilUsuario;
    }

    public static void setPerfilUsuario(String perfilUsuario) {
        CuentaInstagram.perfilUsuario = perfilUsuario;
    }

    public static String getSeleccionUsuario() {
        return seleccionUsuario;
    }

    public static void setSeleccionUsuario(String seleccionUsuario) {
        CuentaInstagram.seleccionUsuario = seleccionUsuario;
    }

    public static CuentaInstagram getItem(int indice){
        return (CuentaInstagram) listaCuentas.get(indice);
    }

    public static void setItem(Object nuevoObjeto){
        listaCuentas.add(nuevoObjeto);
    }

}
