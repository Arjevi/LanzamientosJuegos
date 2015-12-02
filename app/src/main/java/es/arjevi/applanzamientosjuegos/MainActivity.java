package es.arjevi.applanzamientosjuegos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import BaseDatosHelper.DatabaseHelper;


public class MainActivity extends ActionBarActivity {

    private Button bt_insertar;
    private Button bt_verJuegos;
    private DatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_insertar=(Button) findViewById(R.id.MAbtnRegistro);
        bt_verJuegos = (Button) findViewById(R.id.MAbtnVerJuegos);

        bt_insertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(getApplicationContext(), RegistroActivity.class);
                startActivity(i);
            }
        });

        bt_verJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MisJuegosActivity.class);
                startActivity(i);
            }
        });
    }


}
