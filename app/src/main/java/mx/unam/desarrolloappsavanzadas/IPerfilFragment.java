package mx.unam.desarrolloappsavanzadas;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.Model.Mascota;
import mx.unam.desarrolloappsavanzadas.Model.PerfilMascota;
import mx.unam.desarrolloappsavanzadas.adapters.MascotaAdaptador;
import mx.unam.desarrolloappsavanzadas.adapters.PerfilMascotaAdaptador;

/**
 * Created by Roy on 14/07/2016.
 */
public interface IPerfilFragment {

    public void generarGridLayout();

    public PerfilMascotaAdaptador crearAdaptador(ArrayList<PerfilMascota> perfilMascotas);

    public void inicializarAdaptadorRV(PerfilMascotaAdaptador adaptador);

}
