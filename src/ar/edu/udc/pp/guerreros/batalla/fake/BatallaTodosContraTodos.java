package ar.edu.udc.pp.guerreros.batalla.fake;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ar.edu.udc.pp.guerreros.batalla.BatallaAbstracta;
import ar.edu.udc.pp.guerreros.batalla.GuerrerosListener;
import ar.edu.udc.pp.guerreros.batalla.InterfazGuerrero;

public class BatallaTodosContraTodos extends BatallaAbstracta {

	private static Random rnd = new Random(new Date().getTime()); 
	
	@Override
	public InterfazGuerrero[] seleccionarContrincantes() {
		InterfazGuerrero [] contrincantes = new InterfazGuerrero [2];
		
		int cantidadVivos = 0;
		for (InterfazGuerrero g: this.ejercitos.get(0)){
			if (g.estaVivo()) {
				cantidadVivos++;
			}
		}
		if (cantidadVivos < 2) {
			return null;
		}
		
		do {
			contrincantes[0] = this.ejercitos.get(0).get(rnd.nextInt(this.ejercitos.get(0).size()));
		} while (! contrincantes[0].estaVivo());
		
		do {
			contrincantes[1] = this.ejercitos.get(0).get(rnd.nextInt(this.ejercitos.get(0).size()));
		} while (contrincantes[0].equals(contrincantes[1]) || !contrincantes[1].estaVivo());
		
		return contrincantes;
	}
	
	@Override
	public void preparar() {
		// TODO Auto-generated method stub
		
		String [] nombresGuerreros = 
			{
				"Gengis Kan",
				"Eric El Rojo",
				"Nippur de Lagash",
				"John Rambo",
				"Aragorn",
				"Sir Lancelot",
				"Conan El Bárbaro",
				"El Cid Campeador",
				"Che Guevara",
				"William Walace",
				"Saladino",
				"Barbanegra"
				};
		
		List<InterfazGuerrero> guerreros = new LinkedList<>();
		for (int i = 0; i < nombresGuerreros.length ; i++) {
			guerreros.add(new GuerreFake(nombresGuerreros[i]));
		}
		this.addEjercito(guerreros);
		
		GuerreFake.addListener(this);
	}
	

	public InterfazGuerrero getGanador() {
		InterfazGuerrero ganador = null;
		int cantidadVivos = 0;
		for (InterfazGuerrero g:  this.ejercitos.get(0)) {
			if (g.estaVivo()) {
				ganador = g;
				cantidadVivos++;
				if (cantidadVivos > 1) {
					return null;
				}
			}
		}
		return ganador;
	}

	public static void main(String[] args) {
		BatallaTodosContraTodos batalla = new BatallaTodosContraTodos();
		batalla.preparar();
		batalla.lanzar();
		System.out.println( "El ganador es " + batalla.getGanador() + "!!!"); 
	}
}


