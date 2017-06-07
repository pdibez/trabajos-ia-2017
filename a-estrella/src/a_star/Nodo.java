package a_star;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Nodo implements Comparable<Nodo> {
    private Map<Nodo, Integer> nodosHijos; // Nodo hijo -> Coste desde este nodo hasta nodo hijo
    public final String nombre;
    public final int h; // Heurística
    public int coste = 0; // Coste del camino más corto desde el nodo inicial hasta este nodo
    public Nodo padre = null;
    
    private Nodo(String nombre, int h) {
    	this.nombre = nombre;
    	this.h = h;
    	this.nodosHijos = new HashMap<Nodo, Integer>();
    }
    
    public static Nodo crearNodo(String nombre, int h) {
    	return new Nodo(nombre, h);
    }
    
    public void addHijo(Nodo nodo, int coste) {
    	nodosHijos.put(nodo, coste);
    }
    
    public Set<Nodo> getHijos() {
    	return nodosHijos.keySet();
    }
    
    public int getCosteHijo(Nodo hijo) {
    	return nodosHijos.get(hijo);
    }
    
    public int getF() {
    	return this.coste + this.h;
    }
    
    @Override
    public int compareTo(Nodo otro) {
		return this.getF() - otro.getF();
    }
    
    @Override
    public boolean equals(Object otro) {
    	if(otro == null || !(otro instanceof Nodo)) {
    		return false;
    	}
    	
    	return ((Nodo) otro).nombre == this.nombre; 
    }
}
