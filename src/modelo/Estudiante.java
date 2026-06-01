package modelo;

import modelo.TipoCasa;
import java.util.ArrayList;
import java.util.List;

public class Estudiante extends Personaje {
    private String año;
    private int experiencia;
    private List<Hechizo> hechizosAprendidos;
    private List<ObjetoMagico> objetos;

    public Estudiante() {
        hechizosAprendidos = new ArrayList<>();
        objetos = new ArrayList<>();
    }

    public Estudiante(String nombre, TipoCasa casaHogwarts, String nivelMagia,
                      String habilidad, String año, int experiencia) {
        super(nombre, casaHogwarts, nivelMagia, habilidad);
        this.año = año;
        this.experiencia = experiencia;
        this.hechizosAprendidos = new ArrayList<>();
        this.objetos = new ArrayList<>();
    }

    public String getAño() { return año; }
    public void setAño(String año) { this.año = año; }

    public int getExperiencia() { return experiencia; }
    public void setExperiencia(int experiencia) { this.experiencia = experiencia; }

    public List<Hechizo> getHechizosAprendidos() { return hechizosAprendidos; }

    public boolean aprenderHechizo(Hechizo h) {
        if (h != null && !hechizosAprendidos.contains(h)) {
            hechizosAprendidos.add(h);
            experiencia += 10;
            return true;
        }
        return false;
    }

    public boolean hechizoAprendido(Hechizo h) {
        return hechizosAprendidos.contains(h);
    }

    public void asistirClase(Object c) {
        experiencia += 5;
    }

    @Override
    public void usarObjetoMagico(ObjetoMagico o) {
        if (o != null) objetos.add(o);
    }

    @Override
    public void interactuar(Personaje p) {
        // interacción con otro personaje
    }

    public List<ObjetoMagico> getObjetos() { return objetos; }

    @Override
    public String toString() {
        return nombre + " | Casa: " + casaHogwarts + " | Año: " + año + " | XP: " + experiencia;
    }
}
