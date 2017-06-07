package estructuras_de_datos;

import java.util.ArrayList;


public class Nodo {
	
	private int valor;
	private ArrayList<Nodo> hijos;
	private Boolean esHoja;	
	
	public Nodo(int valor,ArrayList<Nodo> hijos, Boolean esHoja){
		this.valor = valor;
		this.hijos = hijos;
		this.esHoja = esHoja;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public ArrayList<Nodo> getHijos() {
		return hijos;
	}
	public void setHijos(ArrayList<Nodo> hijos) {
		this.hijos = hijos;
	}
	
	public Boolean esHoja(){
		return this.esHoja;
	}
	
	public Nodo hijoMayor(){
		Nodo mayor = this.hijos.get(0);
		for(Nodo nodo : this.hijos){
			if (nodo.getValor() > mayor.getValor()) {
				mayor = nodo;
			}
		}
		return mayor;
	}
	
	public Nodo hijoMenor(){
		Nodo menor = this.hijos.get(0);
		for(Nodo nodo : this.hijos){
			if (nodo.getValor() < menor.getValor()) {
				menor = nodo;
			}
		}
		return menor;
	}
}
