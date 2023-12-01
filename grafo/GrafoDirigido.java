package grafo;

import java.util.*;

public class GrafoDirigido<T> implements Grafo<T> {
	private Map<Integer, ArrayList<Arco<T>>> nodos;

	public GrafoDirigido() {
		this.nodos = new HashMap<>();
	}

	public void print() {
		for (Integer k : nodos.keySet()) {
			System.out.println("-> KEY: " + k + " - VALUE: " + nodos.get(k).toString());
		}
	}

	// El metodo agregarVertice() tendra una complejidad computacional de O(1) ya
	// que
	// no presenta bucles ni iteraciones
	// que puedan influir en la complejidad del mismo.
	@Override
	public void agregarVertice(int verticeId) {
		nodos.putIfAbsent(verticeId, new ArrayList<>());
	}

	// El método borrarVerice() tendra una complejidad computacional de O(|V|), ya
	// que
	// en el peor de los casos
	// recorrera todos los vertices y cada uno de sus arcos.
	@Override
	public void borrarVertice(int verticeId) {
		nodos.remove(verticeId);
		for (ArrayList<Arco<T>> adyacentes : nodos.values()) {
			adyacentes.removeIf(arco -> arco.getVerticeDestino() == verticeId);
		}
	}

	// El metodo agregarArco() tendra una complejidad computacional de O(1) ya que
	// no
	// presenta bucles ni iteraciones
	// que puedan influir en la complejidad del mismo.
	@Override
	public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
		if (nodos.containsKey(verticeId1) && nodos.containsKey(verticeId2)) {
			Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
			nodos.get(verticeId1).add(arco);
		} else {
			System.out.println("No se pudo agregar el arco");
		}
	}

	// El método borrarArco() presenta un complejidad O(|A|), ya que en el peor de
	// los
	// casos recorrerá el total de arcos del vertice origen.
	@Override
	public void borrarArco(int verticeId1, int verticeId2) {
		if (nodos.containsKey(verticeId1) && nodos.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = nodos.get(verticeId1);
			Iterator<Arco<T>> it = arcos.iterator();
			while (it.hasNext()) {
				Arco<T> arco = it.next();
				if (arco.getVerticeOrigen() == verticeId1 && arco.getVerticeDestino() == verticeId2) {
					it.remove();
				}
			}
		}
	}

	// El metodo contieneVertice() tendra una complejidad computacional de O(1) ya
	// que
	// no presenta bucles ni iteraciones
	// que puedan influir en la complejidad del mismo.
	@Override
	public boolean contieneVertice(int verticeId) {
		return nodos.containsKey(verticeId);
	}

	// El método existeArco() presenta un complejidad O(|A|), ya que en el peor de
	// los
	// casos recorrerá el total de arcos del vertice origen.
	@Override
	public boolean existeArco(int verticeId1, int verticeId2) {
		if (nodos.containsKey(verticeId1) && nodos.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = nodos.get(verticeId1);
			for (Arco<T> arco : arcos) {
				if (arco.getVerticeOrigen() == verticeId1 && arco.getVerticeDestino() == verticeId2) {
					return true;
				}
			}
		}
		return false;
	}

	// El método obtenerArco() presenta un complejidad O(|A|), ya que en el peor de
	// los casos recorrerá el total de arcos del vertice origen.
	@Override
	public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
		if (nodos.containsKey(verticeId1) && nodos.containsKey(verticeId2)) {
			ArrayList<Arco<T>> arcos = nodos.get(verticeId1);
			for (Arco<T> arco : arcos) {
				if (arco.getVerticeOrigen() == verticeId1 && arco.getVerticeDestino() == verticeId2) {
					Arco<T> arcoAux = new Arco<>(verticeId1, verticeId2, arco.getEtiqueta());
					return arcoAux;
				}
			}
		}
		return null;
	}

	// El metodo cantidadVertices() tendra una complejidad computacional de O(1) ya
	// que no presenta bucles ni iteraciones
	// que puedan influir en la complejidad del mismo.
	@Override
	public int cantidadVertices() {
		return nodos.size();
	}

	// La complejidad del metodo cantidadArcos() seria O(V + A), donde V es la
	// cantidad de vertices y A la cantidad de arcos.
	@Override
	public int cantidadArcos() {
		int cantidadArcos = 0;
		Set<Integer> keys = nodos.keySet();
		for (Integer key : keys) {
			ArrayList<Arco<T>> arcos = nodos.get(key);
			for (int i = 0; i < arcos.size(); i++) {
				cantidadArcos += 1;
			}
		}
		return cantidadArcos;
	}

	// El método obtenerVertices() tiene una complejidad de O(V), donde V es la
	// cantidad de vértices en el grafo.
	@Override
	public Iterator<Integer> obtenerVertices() {
		return nodos.keySet().iterator();
	}

	// El método obtenerAdyacentes() tiene una complejidad de O(A) en el peor caso,
	// donde A es la cantidad de arcos adyacentes al vértice especificado.
	@Override
	public Iterator<Integer> obtenerAdyacentes(int verticeId) {
		ArrayList<Arco<T>> adyacentes = nodos.get(verticeId);
		if (adyacentes == null) {
			System.out.println("El vertice no existe");
		}
		ArrayList<Integer> destinos = new ArrayList<>();
		for (Arco<T> arco : adyacentes) {
			destinos.add(arco.getVerticeDestino());
		}
		return destinos.iterator();
	}

	// El método obtenerArcos() tiene una complejidad de tiempo de O(|A|), donde A
	// es
	// la cantidad total de arcos en el grafo.
	@Override
	public Iterator<Arco<T>> obtenerArcos() {
		Set<Integer> keys = nodos.keySet();
		ArrayList<Arco<T>> arcos = new ArrayList<>();
		for (Integer key : keys) {
			ArrayList<Arco<T>> listaArcos = nodos.get(key);
			arcos.addAll(listaArcos);
		}
		return arcos.iterator();
	}

	// El método obtenerArcos() tiene una complejidad de tiempo de O(A), donde A es
	// la
	// cantidad total de arcos del vertice especificado por parametro.
	@Override
	public Iterator<Arco<T>> obtenerArcos(int verticeId) {
		ArrayList<Arco<T>> arcos = nodos.get(verticeId);
		return arcos.iterator();
	}

}
