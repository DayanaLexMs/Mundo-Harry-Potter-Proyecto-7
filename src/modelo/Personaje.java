package modelo;

import modelo.TipoCasa;

public abstract class Personaje {
    protected String nombre;
    protected TipoCasa casaHogwarts;
    protected String nivelMagia;
    protected String habilidad;

    public Personaje() {}

    public Personaje(String nombre, TipoCasa casaHogwarts, String nivelMagia, String habilidad) {
        this.nombre = nombre;
        this.casaHogwarts = casaHogwarts;
        this.nivelMagia = nivelMagia;
        this.habilidad = habilidad;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public TipoCasa getCasaHogwarts() { return casaHogwarts; }
    public void setCasaHogwarts(TipoCasa casaHogwarts) { this.casaHogwarts = casaHogwarts; }

    public String getNivelMagia() { return nivelMagia; }
    public void setNivelMagia(String nivelMagia) { this.nivelMagia = nivelMagia; }

    public String getHabilidad() { return habilidad; }
    public void setHabilidad(String habilidad) { this.habilidad = habilidad; }

    public abstract void usarObjetoMagico(ObjetoMagico o);
    public abstract void interactuar(Personaje p);

    @Override
    public String toString() {
        return nombre + " [" + casaHogwarts + "]";
    }
}
