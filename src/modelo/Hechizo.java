package modelo;

import modelo.TipoHechizo;

public class Hechizo {
    private String nombre;
    private TipoHechizo tipo;
    private String poder;

    public Hechizo() {}

    public Hechizo(String nombre, TipoHechizo tipo, String poder) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.poder = poder;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoHechizo getTipo() { return tipo; }
    public void setTipo(TipoHechizo tipo) { this.tipo = tipo; }

    public String getPoder() { return poder; }
    public void setPoder(String poder) { this.poder = poder; }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ") - Poder: " + poder;
    }
}
