package Clases;

/**
 * Created by JAVI on 26/11/2015.
 */
public class Juego {

    private int id;
    private String nombre;
    private String plataforma;
    private String genero;
    private boolean finalizado;

    public Juego(String nombre, String plataforma, String genero, boolean finalizado){

        this.nombre = nombre;
        this.plataforma = plataforma;
        this.genero = genero;
        this.finalizado = finalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(boolean finalizado) {
        this.finalizado = finalizado;
    }

    @Override
    public String toString() {
        return "Juego{" +
                "nombre='" + nombre + '\'' +
                ", plataforma='" + plataforma + '\'' +
                ", genero='" + genero + '\'' +
                ", finalizado=" + finalizado +
                '}';
    }
}
