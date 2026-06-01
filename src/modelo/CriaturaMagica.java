package modelo;

import modelo.TipoCasa;
import modelo.TipoCriatura;

public class CriaturaMagica extends Personaje {
    private TipoCriatura tipo;

    public CriaturaMagica() {}

    public CriaturaMagica(String nombre, TipoCasa casaHogwarts, String nivelMagia,
                          String habilidad, TipoCriatura tipo) {
        super(nombre, casaHogwarts, nivelMagia, habilidad);
        this.tipo = tipo;
    }

    public TipoCriatura getTipo() { return tipo; }
    public void setTipo(TipoCriatura tipo) { this.tipo = tipo; }

    public void atacarPersona(Personaje p) {}

    @Override
    public void usarObjetoMagico(ObjetoMagico o) {}

    @Override
    public void interactuar(Personaje p) {}

    @Override
    public String toString() {
        return nombre + " [" + tipo + "]";
    }
}
