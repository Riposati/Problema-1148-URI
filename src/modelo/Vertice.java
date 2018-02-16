package modelo;

import java.util.ArrayList;
import java.util.List;

public class Vertice {

	private int rotulo;
	private double distancia;
	private List<Aresta> listaArestas;
	private Vertice precedente;

	public Vertice(int rotulo) {
		this.rotulo = rotulo;
		this.distancia = Double.POSITIVE_INFINITY;
		this.listaArestas = new ArrayList<Aresta>(1000);
		this.precedente = null;
	}

	public void setPrecedente(Vertice v) {
		this.precedente = v;
	}

	public Vertice getPrecedente() {
		return this.precedente;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public int getRotulo() {
		return rotulo;
	}

	public List<Aresta> getListaArestas() {
		return listaArestas;
	}
}
