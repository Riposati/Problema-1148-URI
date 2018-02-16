package modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Grafo {

	class Comparador implements Comparator<Vertice> {

		@Override
		public int compare(Vertice o1, Vertice o2) {

			if (o1.getDistancia() < o2.getDistancia()) {
				return -1;
			}
			if (o1.getDistancia() > o2.getDistancia()) {
				return 1;
			}
			return 0;
		}
	}

	private List<Vertice> vertices;
	private PriorityQueue<Vertice> filaDeVertices;

	public Grafo() {
		this.vertices = new ArrayList<Vertice>(1000);
		this.filaDeVertices = new PriorityQueue<>(1000, new Comparador());
	}

	public List<Vertice> getVertices() {
		return vertices;
	}

	public void addVertice(Vertice v) {
		this.vertices.add(v);
	}

	public void mostraGrafo(int vInicial, int vFinal) {

		for (Vertice v : this.vertices) {

			if (v.getRotulo() == vFinal) {
				if (v.getDistancia() == Double.POSITIVE_INFINITY) {

					System.out.println("Nao e possivel entregar a carta");
				} else
					System.out.println((int) v.getDistancia());
			}
		}
	}

	public void addAresta(Vertice verticeParteAresta, Vertice verticeRecebeAresta, double pesoAresta) {

		Aresta a = new Aresta();
		a.setRotuloVerticeVai(verticeRecebeAresta);
		a.setPesoAresta(pesoAresta);
		this.vertices.get(verticeParteAresta.getRotulo() - 1).getListaArestas().add(a);

		for (int i = 0; i < this.vertices.get(verticeParteAresta.getRotulo() - 1).getListaArestas().size(); i++) { // O(500)

			if (this.vertices.get(verticeParteAresta.getRotulo() - 1).getListaArestas().get(i).getRotuloVerticeVai()
					.getRotulo() == verticeRecebeAresta.getRotulo()) {

				for (int j = 0; j < verticeRecebeAresta.getListaArestas().size(); j++) { // O(250 000)

					if (verticeRecebeAresta.getListaArestas().get(j).getRotuloVerticeVai().getRotulo() == this.vertices
							.get(verticeParteAresta.getRotulo() - 1).getRotulo()) {

						verticeRecebeAresta.getListaArestas().get(j).setPesoAresta(0);
						verticeParteAresta.getListaArestas().get(i).setPesoAresta(0);
						break;
					}
				}
			}
		}
	}

	private boolean acheiVertice(int rotulo) {

		for (int i = 0; i < this.getVertices().size(); i++) {

			if (this.getVertices().get(i).getRotulo() == rotulo) {
				this.getVertices().get(i).setDistancia(0); // O começo da busca
				filaDeVertices.add(this.getVertices().get(i));
				return true;
			}
		}

		return false;
	}

	private void BFS(Vertice v) {

		double sum = 0;

		for (int i = 0; i < v.getListaArestas().size(); i++) {

			Aresta a = v.getListaArestas().get(i);
			sum = v.getDistancia() + a.getPesoAresta();

			if (sum < a.getRotuloVerticeVai().getDistancia()) {

				a.getRotuloVerticeVai().setDistancia(sum);
				a.getRotuloVerticeVai().setPrecedente(v);
				this.filaDeVertices.add(a.getRotuloVerticeVai());
			}
		}
	}

	public void dijkstra(int rotuloVerticeInicial) {

		if (acheiVertice(rotuloVerticeInicial)) {

			while (filaNaoVazia()) {

				BFS(filaDeVertices.poll());
			}
		}
	}

	private boolean filaNaoVazia() {
		return !this.filaDeVertices.isEmpty();
	}
}
