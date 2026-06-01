package modelo;

import modelo.TipoCasa;

public class Casa {
    private TipoCasa nombre;

    public Casa() {}

    public Casa(TipoCasa nombre) {
        this.nombre = nombre;
    }

    public TipoCasa getNombre() { return nombre; }
    public void setNombre(TipoCasa nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return nombre.toString();
    }
}
