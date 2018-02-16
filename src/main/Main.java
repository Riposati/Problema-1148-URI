package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import modelo.Grafo;
import modelo.Vertice;

class FastReader {
	BufferedReader br;
	StringTokenizer st;

	public FastReader() {
		br = new BufferedReader(new InputStreamReader(System.in));
	}

	String next() {
		while (st == null || !st.hasMoreElements()) {
			try {
				st = new StringTokenizer(br.readLine());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return st.nextToken();
	}

	int nextInt() {
		return Integer.parseInt(next());
	}

	long nextLong() {
		return Long.parseLong(next());
	}

	double nextDouble() {
		return Double.parseDouble(next());
	}

	String nextLine() {
		String str = "";
		try {
			str = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
}


public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		FastReader s = new FastReader();

		Grafo g = new Grafo();
		int qtdVertices, qtdArestas, auxArestaVai, auxArestaRecebe, auxPesoAresta;
		List<Vertice> lista = new ArrayList<Vertice>(1000);

		qtdVertices = s.nextInt();
		qtdArestas = s.nextInt();

		while (qtdVertices != 0 || qtdArestas != 0) {

			for (int i = 0; i < qtdVertices; i++) { // O(500)
				Vertice v = new Vertice(i + 1);
				lista.add(v);
				g.addVertice(v);
			}

			Vertice verticeAux1 = null, verticeAux2 = null;

			for (int i = 0; i < qtdArestas; i++) { // O(250 000)

				auxArestaVai = s.nextInt();
				auxArestaRecebe = s.nextInt();
				auxPesoAresta = s.nextInt();

				for (int j = 0; j < lista.size(); j++) {

					if (auxArestaVai == lista.get(j).getRotulo()) {
						verticeAux1 = lista.get(j);
					}
					if (auxArestaRecebe == lista.get(j).getRotulo()) {
						verticeAux2 = lista.get(j);
					}
				}
				g.addAresta(verticeAux1, verticeAux2, auxPesoAresta);
			}

			int qtdPesquisas = s.nextInt();
			int a, b;

			for (int i = 0; i < qtdPesquisas; i++) { // O(100)

				a = s.nextInt();
				b = s.nextInt();

				g.dijkstra(a);
				g.mostraGrafo(a, b);

				for (int j = 0; j < g.getVertices().size(); j++) {
					g.getVertices().get(j).setDistancia(Double.POSITIVE_INFINITY);
				}
			}

			qtdVertices = s.nextInt();
			qtdArestas = s.nextInt();
			lista.clear();
			g = new Grafo();
			System.out.print("\n");
		}
	}
}