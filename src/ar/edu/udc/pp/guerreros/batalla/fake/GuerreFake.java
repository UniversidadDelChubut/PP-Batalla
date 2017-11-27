package ar.edu.udc.pp.guerreros.batalla.fake;

import java.util.LinkedList;
import java.util.List;

import ar.edu.udc.pp.guerreros.batalla.GuerrerosListener;
import ar.edu.udc.pp.guerreros.batalla.InterfazGuerrero;

public class GuerreFake implements InterfazGuerrero {
	
	private int nivelVida = 1;
	private String nombre;
	
	private static List<GuerrerosListener> listeners =new LinkedList<GuerrerosListener>();
	
	public static void addListener (GuerrerosListener listener){
		GuerreFake.listeners.add (listener);
	}
	
	
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
		for(GuerrerosListener l: listeners) {
			l.ataque(this, contrincante, "Cuchillo", 2);
		}
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
