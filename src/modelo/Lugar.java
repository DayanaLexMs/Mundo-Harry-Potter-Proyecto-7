package modelo;

import modelo.TipoUbicacion;

public class Lugar {
    private String nombre;
    private TipoUbicacion tipo;

    public Lugar() {}

    public Lugar(String nombre, TipoUbicacion tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoUbicacion getTipo() { return tipo; }
    public void setTipo(TipoUbicacion tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return nombre + " (" + tipo + ")";
    }
}
