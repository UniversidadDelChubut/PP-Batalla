package ar.edu.udc.pp.guerreros.batalla;

public interface InterfazGuerrero {
	public boolean estaVivo();
	public void atacar(InterfazGuerrero contrincante);
	public int getNivelVida();
}
