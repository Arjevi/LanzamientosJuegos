package es.arjevi.applanzamientosjuegos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import BaseDatosHelper.DatabaseHelper;
import Clases.AdaptadorJuego;
import Clases.Juego;


public class MisJuegosActivity extends ActionBarActivity {

    private Spinner spinnerGenero, spinnerPlataforma;
    private ListView lista_juegos;
    private String[] elementos_plataforma = {"Plataforma", "PC", "PS4", "PS3", "XONE", "X360", "3DS", "Wii U"};
    private String[] elementos_genero = {"Genero", "Accion", "Rol", "FPS", "Deportes", "Carreras", "Indie", "Estrategia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_juegos);

        spinnerGenero = (Spinner) findViewById(R.id.MJAspinnerGenero);
        spinnerPlataforma = (Spinner) findViewById(R.id.MJAspinnerPlataforma);
        lista_juegos = (ListView) findViewById(R.id.MJAlistaJuegos);

        final ArrayList<Juego> juegos = rellenarListaBD();
        AdaptadorJuego adaptador = new AdaptadorJuego(this, juegos);
        lista_juegos.setAdapter(adaptador);

        cargarSpinners();
    }


    public ArrayList<Juego> rellenarListaAutomatica() {

        ArrayList<Juego> lista_juegos = new ArrayList<>();

        Juego c1 = new Juego("Starcraft 2", "PC", "Estrategia", true);
        Juego c2 = new Juego("Hitman", "PS3", "Accion", true);
        Juego c3 = new Juego("Animal Crossing", "3DS", "Indie", false);

        Collections.addAll(lista_juegos, c1, c2, c3);

        return lista_juegos;
    }

    public ArrayList<Juego> rellenarListaBD() {

        DatabaseHelper dbh = new DatabaseHelper (this);
        SQLiteDatabase db = dbh.getReadableDatabase();

        Cursor c = db.rawQuery("Select nombre,plataforma,genero,finalizado from juegos;", null);

        ArrayList<Juego> lstJuegos = new ArrayList<>();

        //Comprobamos que hay al menos un registro
        if (c.moveToFirst())
        {
            Juego juego;
            String nombre, plataforma, genero;
            int finalizado;

            //Recorremos el cursor hasta que no haya más registros
            do {
                nombre = c.getString(0);
                Log.i("javi",nombre);
                plataforma = c.getString(1);
                genero = c.getString(2);
                finalizado = c.getInt(3);

                if(finalizado==0){
                    juego = new Juego(nombre,plataforma,genero,false);
                }else{
                    juego = new Juego(nombre,plataforma,genero,true);
                }
                lstJuegos.add(juego);

            } while(c.moveToNext());

            db.close();

        }
        return lstJuegos;

    }


    private void cargarSpinners(){


        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, elementos_plataforma);

        adaptador.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerPlataforma.setAdapter(adaptador);

        ArrayAdapter<String> adaptador2 =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_dropdown_item, elementos_genero);

        adaptador2.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);

        spinnerGenero.setAdapter(adaptador2);
    }

}
