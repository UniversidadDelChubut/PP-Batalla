package ar.edu.udc.pp.guerreros.batalla;

public interface GuerrerosListener {
	public void ataque (InterfazGuerrero atacante, InterfazGuerrero atacado, String nombreArma, int danioCausado);
}
