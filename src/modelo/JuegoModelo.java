package modelo;

import modelo.*;
import java.util.ArrayList;
import java.util.List;

public class JuegoModelo {
    private Escuela escuela;
    private Jugador jugadorActual;
    private List<Hechizo> hechizosDisponibles;
    private List<ObjetoMagico> objetosDisponibles;
    private List<CriaturaMagica> criaturas;
    private List<String> bitacora;

    public JuegoModelo() {
        bitacora = new ArrayList<>();
        inicializarMundo();
    }

    private void inicializarMundo() {
        escuela = new Escuela("Hogwarts");

        // Casas
        escuela.agregarCasa(new Casa(TipoCasa.GRYFFINDOR));
        escuela.agregarCasa(new Casa(TipoCasa.HUFFLEPUFF));
        escuela.agregarCasa(new Casa(TipoCasa.RAVENCLAW));
        escuela.agregarCasa(new Casa(TipoCasa.SLYTHERIN));

        // Ubicaciones
        escuela.agregarUbicacion(new Lugar("Gran Comedor", TipoUbicacion.COMEDOR));
        escuela.agregarUbicacion(new Lugar("Bosque Prohibido", TipoUbicacion.BOSQUE));
        escuela.agregarUbicacion(new Lugar("Sala de los Menesteres", TipoUbicacion.SALA));

        // Profesores
        escuela.agregarProfesor(new Profesor("Albus Dumbledore", TipoCasa.GRYFFINDOR, "Maestro", "Liderazgo", "1990", 100, Especialidad.TRANSFORMACION));
        escuela.agregarProfesor(new Profesor("Severus Snape", TipoCasa.SLYTHERIN, "Avanzado", "Pociones", "1980", 90, Especialidad.POCION));
        escuela.agregarProfesor(new Profesor("Minerva McGonagall", TipoCasa.GRYFFINDOR, "Avanzado", "Transformaciones", "1985", 95, Especialidad.DEFENSA));

        // Estudiantes NPC
        escuela.agregarEstudiante(new Estudiante("Harry Potter", TipoCasa.GRYFFINDOR, "Alto", "Defensa", "Tercero", 80));
        escuela.agregarEstudiante(new Estudiante("Hermione Granger", TipoCasa.GRYFFINDOR, "Experto", "Memoria", "Tercero", 150));
        escuela.agregarEstudiante(new Estudiante("Ron Weasley", TipoCasa.GRYFFINDOR, "Medio", "Ajedrez", "Tercero", 60));
        escuela.agregarEstudiante(new Estudiante("Draco Malfoy", TipoCasa.SLYTHERIN, "Alto", "Intimidación", "Tercero", 70));

        // Hechizos
        hechizosDisponibles = new ArrayList<>();
        hechizosDisponibles.add(new Hechizo("Expelliarmus", TipoHechizo.DEFENSA, "Desarmar al oponente"));
        hechizosDisponibles.add(new Hechizo("Expecto Patronum", TipoHechizo.DEFENSA, "Invocar un Patronus de luz"));
        hechizosDisponibles.add(new Hechizo("Wingardium Leviosa", TipoHechizo.UTILIDAD, "Levitar objetos"));
        hechizosDisponibles.add(new Hechizo("Lumos", TipoHechizo.UTILIDAD, "Iluminar la varita"));
        hechizosDisponibles.add(new Hechizo("Stupefy", TipoHechizo.ATAQUE, "Aturdir al oponente"));
        hechizosDisponibles.add(new Hechizo("Accio", TipoHechizo.UTILIDAD, "Atraer objetos a distancia"));
        hechizosDisponibles.add(new Hechizo("Riddikulus", TipoHechizo.DEFENSA, "Vencer a un boggart"));
        hechizosDisponibles.add(new Hechizo("Alohomora", TipoHechizo.UTILIDAD, "Abrir cerraduras"));

        // Objetos
        objetosDisponibles = new ArrayList<>();
        objetosDisponibles.add(new ObjetoMagico("Varita de Acebo", TipoObjeto.VARITA));
        objetosDisponibles.add(new ObjetoMagico("Escoba Nimbus 2000", TipoObjeto.ESCOBA));
        objetosDisponibles.add(new ObjetoMagico("Poción Veritaserum", TipoObjeto.POCION));
        objetosDisponibles.add(new ObjetoMagico("Varita de Saúco", TipoObjeto.VARITA));
        objetosDisponibles.add(new ObjetoMagico("Escoba Saeta de Fuego", TipoObjeto.ESCOBA));
        objetosDisponibles.add(new ObjetoMagico("Poción Multijugos", TipoObjeto.POCION));

        // Criaturas
        criaturas = new ArrayList<>();
        criaturas.add(new CriaturaMagica("Dobby", null, "Bajo", "Magia doméstica", TipoCriatura.ELFODOMESTICO));
        criaturas.add(new CriaturaMagica("Buckbeak", null, "Medio", "Vuelo", TipoCriatura.HIPOGRIFO));
        criaturas.add(new CriaturaMagica("Dementor", null, "Alto", "Desesperanza", TipoCriatura.DEMENTOR));
        criaturas.add(new CriaturaMagica("Fawkes", null, "Maestro", "Renacimiento", TipoCriatura.FENIX));
    }

    public void crearJugador(String nombre, TipoCasa casa, String objeto) {
        Lugar lugarInicial = escuela.getUbicaciones().get(0);
        jugadorActual = new Jugador(nombre, casa, "Principiante", "Exploración", "Primero", 0, lugarInicial);

        // Dar objeto inicial
        ObjetoMagico obj = objetosDisponibles.stream()
                .filter(o -> o.getNombre().equals(objeto))
                .findFirst().orElse(objetosDisponibles.get(0));
        jugadorActual.usarObjetoMagico(obj);

        escuela.agregarEstudiante(jugadorActual);
        registrar("¡Bienvenido a Hogwarts, " + nombre + "! Has sido asignado a la casa " + casa + ".");
        registrar("Has recibido: " + obj.getNombre());
    }

    public boolean aprenderHechizo(String nombreHechizo) {
        if (jugadorActual == null) return false;
        Hechizo h = hechizosDisponibles.stream()
                .filter(hz -> hz.getNombre().equals(nombreHechizo))
                .findFirst().orElse(null);
        if (h != null && jugadorActual.aprenderHechizo(h)) {
            registrar("✨ ¡Has aprendido " + h.getNombre() + "! (+" + 10 + " XP)");
            actualizarNivelMagia();
            return true;
        }
        registrar("⚠️ Ya conoces ese hechizo o no existe.");
        return false;
    }

    public boolean moverse(String nombreLugar) {
        if (jugadorActual == null) return false;
        Lugar destino = escuela.getUbicaciones().stream()
                .filter(l -> l.getNombre().equals(nombreLugar))
                .findFirst().orElse(null);
        if (destino != null) {
            jugadorActual.moverse(destino);
            registrar("🗺️ Te has movido a: " + destino.getNombre());
            jugadorActual.setProgreso(jugadorActual.getProgreso() + 5);
            return true;
        }
        return false;
    }

    public String interactuarCon(String nombrePersonaje) {
        if (jugadorActual == null) return "";
        Estudiante est = escuela.buscarEstudiante(nombrePersonaje);
        if (est != null) {
            String msg = est.getNombre() + " dice: \"¡Hola, " + jugadorActual.getNombre() + "! Soy de " + est.getCasaHogwarts() + ".\"";
            registrar("💬 " + msg);
            jugadorActual.setExperiencia(jugadorActual.getExperiencia() + 3);
            return msg;
        }
        Profesor prof = escuela.buscarProfesor(nombrePersonaje);
        if (prof != null) {
            String msg = prof.getNombre() + " dice: \"Estudia mucho, " + jugadorActual.getNombre() + ". La magia requiere dedicación.\"";
            registrar("💬 " + msg);
            jugadorActual.setExperiencia(jugadorActual.getExperiencia() + 5);
            return msg;
        }
        return "No se encontró a " + nombrePersonaje;
    }

    public String duelo(String nombreHechizo) {
        if (jugadorActual == null) return "";
        boolean conoce = jugadorActual.getHechizosAprendidos().stream()
                .anyMatch(h -> h.getNombre().equals(nombreHechizo));
        if (!conoce) {
            String m = "⚠️ No conoces el hechizo " + nombreHechizo + ". ¡Aprende más antes de duelos!";
            registrar(m);
            return m;
        }
        Hechizo h = jugadorActual.getHechizosAprendidos().stream()
                .filter(hz -> hz.getNombre().equals(nombreHechizo)).findFirst().get();
        String[] rivales = {"Draco Malfoy", "Tom Riddle", "Bellatrix Lestrange"};
        String rival = rivales[(int)(Math.random() * rivales.length)];
        boolean gana = Math.random() > 0.35;
        String resultado;
        if (gana) {
            jugadorActual.setExperiencia(jugadorActual.getExperiencia() + 20);
            jugadorActual.setProgreso(jugadorActual.getProgreso() + 10);
            resultado = "⚔️ ¡Usaste " + h.getNombre() + " contra " + rival + " y GANASTE el duelo! (+20 XP)";
        } else {
            resultado = "⚔️ Usaste " + h.getNombre() + " contra " + rival + " pero perdiste. Sigue practicando.";
        }
        registrar(resultado);
        actualizarNivelMagia();
        return resultado;
    }

    private void actualizarNivelMagia() {
        if (jugadorActual == null) return;
        int xp = jugadorActual.getExperiencia();
        if (xp >= 200) jugadorActual.setNivelMagia("Maestro");
        else if (xp >= 100) jugadorActual.setNivelMagia("Avanzado");
        else if (xp >= 50) jugadorActual.setNivelMagia("Intermedio");
        else jugadorActual.setNivelMagia("Principiante");
    }

    public void registrar(String evento) {
        bitacora.add(evento);
    }

    // Getters
    public Escuela getEscuela() { return escuela; }
    public Jugador getJugadorActual() { return jugadorActual; }
    public List<Hechizo> getHechizosDisponibles() { return hechizosDisponibles; }
    public List<ObjetoMagico> getObjetosDisponibles() { return objetosDisponibles; }
    public List<CriaturaMagica> getCriaturas() { return criaturas; }
    public List<String> getBitacora() { return bitacora; }
}
