/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.TipoCasa;
import modelo.*;
import vista.PantallaInicio;
import vista.PantallaJuego;

import java.util.List;

public class JuegoControlador {

    private JuegoModelo modelo;
    private PantallaInicio vistaInicio;
    private PantallaJuego vistaJuego;

    public JuegoControlador() {
        modelo = new JuegoModelo();
    }

    public void iniciar() {
        vistaInicio = new PantallaInicio(this);
        vistaInicio.setVisible(true);
    }

    public void crearJugador(String nombre, TipoCasa casa, String objeto) {
        modelo.crearJugador(nombre, casa, objeto);
        vistaInicio.setVisible(false);
        vistaJuego = new PantallaJuego(this);
        vistaJuego.setVisible(true);
        vistaJuego.actualizarEstado();
    }

    public void aprenderHechizo(String nombreHechizo) {
        modelo.aprenderHechizo(nombreHechizo);
        vistaJuego.actualizarEstado();
    }

    public void moverse(String lugar) {
        modelo.moverse(lugar);
        vistaJuego.actualizarEstado();
    }

    public void interactuar(String personaje) {
        String resultado = modelo.interactuarCon(personaje);
        vistaJuego.mostrarMensaje(resultado);
        vistaJuego.actualizarEstado();
    }

    public void realizarDuelo(String hechizo) {
        String resultado = modelo.duelo(hechizo);
        vistaJuego.mostrarMensaje(resultado);
        vistaJuego.actualizarEstado();
    }

    // Getters para la vista
    public Jugador getJugador() {
        return modelo.getJugadorActual();
    }

    public List<Hechizo> getHechizosDisponibles() {
        return modelo.getHechizosDisponibles();
    }

    public List<ObjetoMagico> getObjetosDisponibles() {
        return modelo.getObjetosDisponibles();
    }

    public List<String> getBitacora() {
        return modelo.getBitacora();
    }

    public Escuela getEscuela() {
        return modelo.getEscuela();
    }

    public List<CriaturaMagica> getCriaturas() {
        return modelo.getCriaturas();
    }
}
