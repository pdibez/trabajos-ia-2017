package a_star;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BusquedaA {
	private Nodo nodoInicial;
	private Nodo nodoObjetivo;
	
	// O(1) para operaciones add y contains
	private Set<Nodo> visitados = new HashSet<Nodo>();
	
	// O(log n) para add y remove, O(n) para contains
	// Una alternativa viable sería sacrificar espacio
	// para obtener mejor performance en tiempo
	// manteniendo nodos frontera en un HashSet
	// además de la PriorityQueue
	private Queue<Nodo> frontera = new PriorityQueue<Nodo>();
	
	public BusquedaA() {
		this.addNodos();
	}
	
	public Nodo busqueda() {
		frontera.add(this.nodoInicial);
		Nodo nodoActual;
		int costeActual;
		int fActual;
		boolean visto;
		
		while(!frontera.isEmpty()) {
			nodoActual = frontera.remove();
			
			if(nodoActual.equals(nodoObjetivo)) { return nodoActual; }
			
			Set<Nodo> hijos = nodoActual.getHijos();
			
			for(Nodo hijo: hijos) {
				costeActual = nodoActual.coste + nodoActual.getCosteHijo(hijo);
				
				visto = visitados.contains(hijo) || frontera.contains(hijo);
				fActual = costeActual + nodoActual.h;
				if(visto && hijo.getF() <= fActual) {
					continue;
				}
				
				hijo.coste = costeActual;
				frontera.remove(hijo);
				frontera.add(hijo);
				
				hijo.padre = nodoActual;
			}
			
			visitados.add(nodoActual);
		}
		
		return null; // No encontrado
	}
	
	public void mostrarCamino(Nodo nodo) {
		if(nodo.padre != null) {
			mostrarCamino(nodo.padre);
		}
		String lineaRespuesta = String.format("%s, Distancia Recorrida: %dkm", nodo.nombre, nodo.coste);
		System.out.println(lineaRespuesta);
	}
	
	private void addNodos() {
		// Ciudades y sus distancias a Tucumán
		Nodo ushuaia = Nodo.crearNodo("Ushuaia", 3152);
		Nodo tucuman = Nodo.crearNodo("Tucumán", 0);
		Nodo sanLuis = Nodo.crearNodo("San Luis", 730);
		Nodo rioGallegos = Nodo.crearNodo("Río Gallegos", 2780);
		Nodo rosario = Nodo.crearNodo("Rosario", 813);
		Nodo neuquen = Nodo.crearNodo("Neuquen", 1386);
		Nodo mendoza = Nodo.crearNodo("Mendoza", 764);
		Nodo cordoba = Nodo.crearNodo("Córdoba", 525);
		Nodo buenosAires = Nodo.crearNodo("Buenos Aires", 1085);
		Nodo comodoroRivadavia = Nodo.crearNodo("Comodoro Rivadavia", 2088);
		Nodo salta = Nodo.crearNodo("Salta", 226);
		
		// Caminos
		ushuaia.addHijo(rioGallegos, 580);
		tucuman.addHijo(mendoza, 955);
		tucuman.addHijo(cordoba, 565);
		tucuman.addHijo(buenosAires, 1251);
		sanLuis.addHijo(neuquen, 770);
		sanLuis.addHijo(cordoba, 428);
		sanLuis.addHijo(buenosAires, 830);
		rioGallegos.addHijo(comodoroRivadavia, 778);
		rioGallegos.addHijo(ushuaia, 580);
		rosario.addHijo(cordoba, 405);
		rosario.addHijo(buenosAires, 298);
		neuquen.addHijo(sanLuis, 770);
		mendoza.addHijo(cordoba, 638);
		mendoza.addHijo(tucuman, 955);
		cordoba.addHijo(mendoza, 638);
		cordoba.addHijo(tucuman, 565);
		cordoba.addHijo(rosario, 405);
		cordoba.addHijo(buenosAires, 695);
		cordoba.addHijo(sanLuis, 428);
		buenosAires.addHijo(sanLuis, 830);
		buenosAires.addHijo(cordoba, 695);
		buenosAires.addHijo(tucuman, 1251);
		buenosAires.addHijo(rosario, 298);
		buenosAires.addHijo(salta, 1462);
		comodoroRivadavia.addHijo(rioGallegos, 778);
		comodoroRivadavia.addHijo(sanLuis, 1745);
		comodoroRivadavia.addHijo(buenosAires, 1761);
		salta.addHijo(tucuman, 306);
		salta.addHijo(buenosAires, 1462);
		
		this.nodoInicial = comodoroRivadavia;
		this.nodoObjetivo = tucuman;
	}
	
	public static void main(String[] args) {
		BusquedaA a = new BusquedaA();
		Nodo f = a.busqueda();
		
		if(f != null) {
			a.mostrarCamino(f);
		} else {
			System.out.println("No se ha encontrado un camino");
		}
	}
}
