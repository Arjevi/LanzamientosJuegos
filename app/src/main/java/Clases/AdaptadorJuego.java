package Clases;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import es.arjevi.applanzamientosjuegos.R;

/**
 * Created by JAVI on 28/11/2015.
 */
public class AdaptadorJuego extends ArrayAdapter {

    Activity context;
    ArrayList<Juego> lstJuegos;

    public AdaptadorJuego(Activity context, ArrayList <Juego> lstJuego) {
        super(context, R.layout.item_juego_lista, lstJuego);
        this.lstJuegos=lstJuego;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();

        View item = inflater.inflate(R.layout.item_juego_lista, null);

        TextView nombre = (TextView)item.findViewById(R.id.itemTxtNombre);
        nombre.setText(lstJuegos.get(position).getNombre());

        TextView plataforma = (TextView)item.findViewById(R.id.itemTxtPlataforma);
        plataforma.setText(lstJuegos.get(position).getPlataforma());

        TextView genero = (TextView)item.findViewById(R.id.itemTxtGenero);
        genero.setText(lstJuegos.get(position).getGenero());

        TextView finalizado = (TextView)item.findViewById(R.id.itemTxtFinalizado);

        if(lstJuegos.get(position).getFinalizado()){
            finalizado.setText("Completado");
        }else{
            finalizado.setText("Incompleto");
        }

        return(item);
    }
}

