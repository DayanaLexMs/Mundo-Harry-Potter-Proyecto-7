package modelo;

import modelo.TipoCasa;

public class Jugador extends Estudiante {
    private int progreso;
    private Lugar ubicacion;

    public Jugador() {}

    public Jugador(String nombre, TipoCasa casaHogwarts, String nivelMagia,
                   String habilidad, String año, int progreso, Lugar ubicacion) {
        super(nombre, casaHogwarts, nivelMagia, habilidad, año, 0);
        this.progreso = progreso;
        this.ubicacion = ubicacion;
    }

    public int getProgreso() { return progreso; }
    public void setProgreso(int progreso) { this.progreso = progreso; }

    public Lugar getUbicacion() { return ubicacion; }
    public void setUbicacion(Lugar ubicacion) { this.ubicacion = ubicacion; }

    public boolean moverse(Lugar u) {
        this.ubicacion = u;
        return true;
    }

    public void hablar(Personaje p) {}
    public void explorar() {}

    public boolean lanzarHechizo(Hechizo h) {
        return getHechizosAprendidos().contains(h);
    }

    @Override
    public String toString() {
        return "🧙 " + nombre + " | Casa: " + casaHogwarts + " | XP: " + getExperiencia() + " | Progreso: " + progreso + "%";
    }
}
