package modelo;

import java.util.ArrayList;
import java.util.List;

public class Escuela {
    private String nombre;
    private List<Estudiante> estudiantes;
    private List<Profesor> profesores;
    private List<Casa> casas;
    private List<Lugar> ubicaciones;

    public Escuela() {
        estudiantes = new ArrayList<>();
        profesores = new ArrayList<>();
        casas = new ArrayList<>();
        ubicaciones = new ArrayList<>();
    }

    public Escuela(String nombre) {
        this.nombre = nombre;
        estudiantes = new ArrayList<>();
        profesores = new ArrayList<>();
        casas = new ArrayList<>();
        ubicaciones = new ArrayList<>();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public boolean agregarEstudiante(Estudiante e) {
        if (e != null) { estudiantes.add(e); return true; }
        return false;
    }

    public boolean agregarProfesor(Profesor p) {
        if (p != null) { profesores.add(p); return true; }
        return false;
    }

    public boolean agregarUbicacion(Lugar u) {
        if (u != null) { ubicaciones.add(u); return true; }
        return false;
    }

    public boolean agregarCasa(Casa c) {
        if (c != null) { casas.add(c); return true; }
        return false;
    }

    public Estudiante buscarEstudiante(String nombre) {
        for (Estudiante e : estudiantes)
            if (e.getNombre().equalsIgnoreCase(nombre)) return e;
        return null;
    }

    public Profesor buscarProfesor(String nombre) {
        for (Profesor p : profesores)
            if (p.getNombre().equalsIgnoreCase(nombre)) return p;
        return null;
    }

    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Profesor> getProfesores() { return profesores; }
    public List<Casa> getCasas() { return casas; }
    public List<Lugar> getUbicaciones() { return ubicaciones; }
}
