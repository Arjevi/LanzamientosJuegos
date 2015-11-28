package es.arjevi.applanzamientosjuegos;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;

import Clases.AdaptadorJuego;
import Clases.Juego;


public class MisJuegosActivity extends ActionBarActivity {

    private Spinner spinnerGenero,spinnerPlataforma;
    private ListView lista_juegos;
    private String[] elementos_plataforma = {"Plataforma","PC","PS4","PS3","XONE","X360","3DS","Wii U"};
    private String[] elementos_genero = {"Genero","Accion","Rol","FPS","Deportes","Carreras","Indie","Estrategia"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_juegos);

        spinnerGenero = (Spinner) findViewById(R.id.MJAspinnerGenero);
        spinnerPlataforma = (Spinner) findViewById(R.id.MJAspinnerPlataforma);
        lista_juegos = (ListView) findViewById(R.id.MJAlistaJuegos);

        final ArrayList<Juego> juegos = rellenarLista();
        AdaptadorJuego adaptador = new AdaptadorJuego(this,juegos);
        lista_juegos.setAdapter(adaptador);

        cargarSpinners();
    }


    public ArrayList<Juego> rellenarLista(){

        ArrayList<Juego> lista_juegos = new ArrayList<>();

        Juego c1 = new Juego("Starcraft 2","PC","Estrategia",true);
        Juego c2 = new Juego("Hitman","PS3","Accion",true);
        Juego c3 = new Juego("Animal Crossing","3DS","Indie",false);

        Collections.addAll(lista_juegos, c1, c2, c3);

        return lista_juegos;
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
