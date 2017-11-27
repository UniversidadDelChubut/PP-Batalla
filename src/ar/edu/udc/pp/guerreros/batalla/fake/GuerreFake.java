package ar.edu.udc.pp.guerreros.batalla.fake;

import ar.edu.udc.pp.guerreros.batalla.InterfazGuerrero;

public class GuerreFake implements InterfazGuerrero {
	
	private int nivelVida = 1;
	private String nombre;
	
	
	public GuerreFake(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	@Override
	public void atacar(InterfazGuerrero contrincante) {
		((GuerreFake)contrincante).nivelVida = 0;
	}
	
	@Override
	public boolean estaVivo() {
		return this.nivelVida > 0;
	}
	
	@Override
	public int getNivelVida() {
		return nivelVida;
	}
	
	@Override
	public String toString() {
		return this.getNombre();
	}
	

}
