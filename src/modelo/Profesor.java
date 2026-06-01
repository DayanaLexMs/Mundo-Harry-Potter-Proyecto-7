package modelo;

import modelo.Especialidad;
import modelo.TipoCasa;

public class Profesor extends Personaje {
    private Especialidad especialidad;

    public Profesor() {}

    public Profesor(String nombre, TipoCasa casaHogwarts, String nivelMagia,
                    String habilidad, String año, int experiencia, Especialidad especialidad) {
        super(nombre, casaHogwarts, nivelMagia, habilidad);
        this.especialidad = especialidad;
    }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public void enseñar(Object c) {}

    public boolean lanzarHechizo() { return true; }

    @Override
    public void usarObjetoMagico(ObjetoMagico o) {}

    @Override
    public void interactuar(Personaje p) {}

    @Override
    public String toString() {
        return "Prof. " + nombre + " [" + especialidad + "]";
    }
}
