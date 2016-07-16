package mx.unam.desarrolloappsavanzadas;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.adapters.MascotaAdaptador;
import mx.unam.desarrolloappsavanzadas.Model.Mascota;


/**
 * Created by Roy on 12/07/2016.
 */
public interface IRecyclerviewFragment {

    public void generarLinearLayoutVertical();

    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);

}
