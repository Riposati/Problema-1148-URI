package modelo;

public class Aresta {

	private Vertice verticeAdj;
	private double pesoAresta;

	public double getPesoAresta() {
		return pesoAresta;
	}

	public void setPesoAresta(double pesoAresta) {
		this.pesoAresta = pesoAresta;
	}

	public Vertice getRotuloVerticeVai() {
		return verticeAdj;
	}

	public void setRotuloVerticeVai(Vertice rotuloVerticeVai) {
		this.verticeAdj = rotuloVerticeVai;
	}
}