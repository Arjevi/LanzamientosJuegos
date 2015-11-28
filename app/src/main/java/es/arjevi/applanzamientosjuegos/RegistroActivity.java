package es.arjevi.applanzamientosjuegos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import BaseDatosHelper.DatabaseHelper;
import Clases.Juego;


public class RegistroActivity extends ActionBarActivity {

    private EditText nombreJuego;
    private Spinner spinnerPlataforma, spinnerGenero;
    private CheckBox chkFinalizado;
    private Button btnRegistrar;
    private String[] elementos_plataforma = {"PC","PS4","PS3","XONE","X360","3DS","Wii U"};
    private String[] elementos_genero = {"Accion","Rol","FPS","Deportes","Carreras","Indie","Estrategia"};

    public DatabaseHelper dbh;
    public SQLiteDatabase db;
    public Cursor c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        nombreJuego=(EditText) findViewById(R.id.RAtxtJuego);
        spinnerGenero=(Spinner) findViewById(R.id.RAspineerGenero);
        spinnerPlataforma = (Spinner) findViewById(R.id.RAspinnerPlataforma);
        chkFinalizado = (CheckBox) findViewById(R.id.RAchkFinalizado);
        btnRegistrar = (Button) findViewById(R.id.RAbtnRegistrar);

        cargarSpinners();
        eventos();

        //Carga de la base de datos: Esto se hará solo 1 vez, porque aquí comprobamos que exista o no unaversion nueva

        /*dbh=new DatabaseHelper(this);
        try{

            dbh.createDataBase();
        }
        catch(IOException e){

            e.printStackTrace();
        }
        dbh=new DatabaseHelper(this);
        db=dbh.getReadableDatabase();
        c=db.rawQuery("SELECT * FROM juego", null);
        String nombre, plataforma, genero;
        int id, finalizado;

        if(c.moveToFirst()){

            do{
                id=c.getInt(1);
                nombre=c.getString(2);
                plataforma=c.getString(3);
                genero=c.getString(4);
                finalizado=c.getInt(5);

                Log.i("-------i", c.toString());
            }
            while(c.moveToNext());

            Log.i("sadasdas", "id devuelta"+id);*/
        }

    private void eventos(){

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nombreJuego.getText().toString();
                String genero = spinnerGenero.getSelectedItem().toString();
                String plataforma = spinnerPlataforma.getSelectedItem().toString();
                boolean finalizado = chkFinalizado.isChecked();

                if(nombre.matches("")){
                    //Mostrar cuadro de dialogo en caso de dejar campos vacios
                    AlertDialog ad = new AlertDialog.Builder(v.getContext())
                            .create();
                    ad.setCancelable(false);
                    ad.setTitle("Error");
                    ad.setMessage("No puede dejar campos vacios");
                    ad.setButton(v.getContext().getString(R.string.btnCuadroDialogo), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    ad.show();
                }else {

                    Juego juego = new Juego(nombre, genero, plataforma, finalizado);

                   //Insertar en BD
                }

            }
        });

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
