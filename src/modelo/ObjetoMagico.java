package modelo;

import modelo.TipoObjeto;

public class ObjetoMagico {
    private String nombre;
    private TipoObjeto tipo;

    public ObjetoMagico() {}

    public ObjetoMagico(String nombre, TipoObjeto tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoObjeto getTipo() { return tipo; }
    public void setTipo(TipoObjeto tipo) { this.tipo = tipo; }

    @Override
    public String toString() {
        return nombre + " [" + tipo + "]";
    }
}
