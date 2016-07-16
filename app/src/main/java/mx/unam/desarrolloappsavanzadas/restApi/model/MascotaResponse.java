package mx.unam.desarrolloappsavanzadas.restApi.model;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.Mascota;
import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;

/**
 * Created by Roy on 14/07/2016.
 */
public class MascotaResponse {

    ArrayList<PerfilMascota> perfilMascotas;

    public ArrayList<PerfilMascota> getPerfilMascotas(){
        return perfilMascotas;
    }

    public void setPerfilMascotas(ArrayList<PerfilMascota> perfilMascotas){
        this.perfilMascotas = perfilMascotas;
    }

}
