package mx.unam.desarrolloappsavanzadas.Model;

import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;

import mx.unam.desarrolloappsavanzadas.R;
import mx.unam.desarrolloappsavanzadas.db.BaseDatos;
import mx.unam.desarrolloappsavanzadas.db.ConstantesBaseDatos;

/**
 * Created by Roy on 14/07/2016.
 */
public class ConstructorMascotas {

    private Context context;
    private static final int LIKE = 1;

    // Constructor
    public ConstructorMascotas(Context context) {
        this.context = context;
    }

    public ArrayList<Mascota> obtenerDatos(){
    /*
        ArrayList<Mascota> mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(0, "Dog01", R.drawable.dog01, 4));
        mascotas.add(new Mascota(1, "Cat01", R.drawable.cat_head, 4));
        mascotas.add(new Mascota(2, "Dog02", R.drawable.dog03, 4));
        mascotas.add(new Mascota(3, "Llama01", R.drawable.llama_head, 4));
        mascotas.add(new Mascota(4, "Cat02", R.drawable.grumpy_cat_head, 4));
        return mascotas;
    */


        BaseDatos db = new BaseDatos(context);
        insertarCincoMascotas(db);
        return db.obtenerTodasLasMascotas();
    }

    public void insertarCincoMascotas(BaseDatos db){
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Dog01");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog01);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Cat01");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.cat_head);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Dog02");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.dog03);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Llama01");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.llama_head);
        db.insertarMascota(contentValues);

        contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_NOMBRE, "Cat02");
        contentValues.put(ConstantesBaseDatos.TABLE_MASCOTAS_FOTO, R.drawable.grumpy_cat_head);
        db.insertarMascota(contentValues);
    }

    public void darLikeMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        ContentValues contentValues = new ContentValues();
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_ID_MASCOTA, mascota.getId());
        contentValues.put(ConstantesBaseDatos.TABLE_LIKES_MASCOTAS_NUMERO_LIKES, LIKE);
        db.insertarLikeMascotas(contentValues);
    }

    public int obtenerLikesMascota(Mascota mascota){
        BaseDatos db = new BaseDatos(context);
        return db.obtenerLikesMascotaBD(mascota);
    }

}
