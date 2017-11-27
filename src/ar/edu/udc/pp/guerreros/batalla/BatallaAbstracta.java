package ar.edu.udc.pp.guerreros.batalla;

import java.util.LinkedList;
import java.util.List;
public abstract class BatallaAbstracta implements GuerrerosListener {

	protected List<List<InterfazGuerrero>> ejercitos = new LinkedList<>();
	private Ataque ultimoAtaque = null;
	
	
	public abstract InterfazGuerrero [] seleccionarContrincantes ();
	public abstract void preparar ();
	
	public void lanzar(){
		
		if (this.ejercitos.isEmpty()) {
			System.err.println("Antes de lanzar una batalla debe prepararla estableciendo ejércitos - Usar método addEjercito en método Preparar ");
			return;
		}
		
		while (!this.batallaTerminada()) {
			InterfazGuerrero [] guerreros = seleccionarContrincantes();
			if (guerreros== null || guerreros.length != 2 || guerreros[0] == null || guerreros[1] 	== null ){
				System.err.println("Hay un error en la selección de contrincantes");
				return;			
			}
			this.ultimoAtaque = null;
			guerreros[0].atacar(guerreros[1]);
			if (ultimoAtaque != null)
				imprimirAccion (ultimoAtaque.atacante, ultimoAtaque.atacado, ultimoAtaque.nombreArma, ultimoAtaque.danioCausado, guerreros[1].getNivelVida());
			else 
				imprimirAccion (guerreros[0], guerreros[1], null, null, guerreros[1].getNivelVida());
			
		}
		
		
	}
	
	void imprimirAccion(InterfazGuerrero atacante, InterfazGuerrero atacado, String nombreArma, Integer danioCausado, int nivelVidaActual) {
				System.out.println(
						atacante + 
						" ataca a " + atacado +
						(nombreArma != null ? " con " + nombreArma : "") +
						(danioCausado != null ? " daño causado " + danioCausado : "") +
						" - Vida restante " + nivelVidaActual
						);
				
	}
	
	@Override
	public void ataque(InterfazGuerrero atacante, InterfazGuerrero atacado, String nombreArma, int danioCausado) {
		this.ultimoAtaque = new Ataque(atacante, atacado, nombreArma, danioCausado);	
	}
	
	
	
	public void addEjercito (List<InterfazGuerrero> ejercito) {
		this.ejercitos.add(ejercito);
	}
	
	public boolean batallaTerminada() {
		if (this.ejercitos.isEmpty()){
			return true;
		}
				
		if (this.ejercitos.size() == 1){
			int contador = 0; 
			for(InterfazGuerrero guerrero: this.ejercitos.get(0)){
				if(guerrero.estaVivo()){
					contador ++;
				}
			}
			return contador <= 1;
		}
		
		int contadorEjecritosConSobrevivientes = 0;
		
		for(List<InterfazGuerrero> ejercito : this.ejercitos){
			
			for(InterfazGuerrero guerrero: ejercito){
				if(guerrero.estaVivo()){
					contadorEjecritosConSobrevivientes ++;
					break;
				}
			}
		}
		return contadorEjecritosConSobrevivientes <= 1;
	}
	
	class Ataque {
		private InterfazGuerrero atacante;
		private InterfazGuerrero atacado;
		private String nombreArma;
		private int danioCausado;
		public Ataque(InterfazGuerrero atacante, InterfazGuerrero atacado, String nombreArma, int danioCausado) {
			super();
			this.atacante = atacante;
			this.atacado = atacado;
			this.nombreArma = nombreArma;
			this.danioCausado = danioCausado;
		}
		
		public InterfazGuerrero getAtacado() {
			return atacado;
		}
		
		public InterfazGuerrero getAtacante() {
			return atacante;
		}
		
		public int getDanioCausado() {
			return danioCausado;
		}
		
		public String getNombreArma() {
			return nombreArma;
		}
		
	}
}
