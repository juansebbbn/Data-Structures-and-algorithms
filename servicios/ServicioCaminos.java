package servicios;

import java.util.*;

import grafo.Arco;
import grafo.Grafo;

public class ServicioCaminos {

	private Grafo<?> grafo;
	private int origen;
	private int destino;
	private int lim;

	public ServicioCaminos(Grafo<?> grafo, int origen, int destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
	}

	public List<List<Integer>> caminos() {
		List<List<Integer>> caminos = new ArrayList<>();
		List<Integer> caminoActual = new ArrayList<>();
		Set<Integer> arcosVisitados = new HashSet<>();

		buscarCaminos(origen, destino, caminoActual, caminos, arcosVisitados, 0, lim);

		return caminos;
	}

	private void buscarCaminos(int verticeActual, int verticeDestino, List<Integer> caminoActual,
			List<List<Integer>> caminos, Set<Integer> arcosVisitados, int arcosRecorridos, int lim) {
		caminoActual.add(verticeActual);

		if (verticeActual == verticeDestino) {
			caminos.add(new ArrayList<>(caminoActual));
		} else if (arcosRecorridos < lim) {
			Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(verticeActual);

			while (adyacentes.hasNext()) {
				int adyacente = adyacentes.next();
				Arco<?> arco = grafo.obtenerArco(verticeActual, adyacente);

				if (!arcosVisitados.contains(arco.hashCode())) {
					arcosVisitados.add(arco.hashCode());
					buscarCaminos(adyacente, verticeDestino, caminoActual, caminos, arcosVisitados, arcosRecorridos + 1,
							lim);
					arcosVisitados.remove(arco.hashCode());
				}
			}
		}

		caminoActual.remove(caminoActual.size() - 1);
	}

}
