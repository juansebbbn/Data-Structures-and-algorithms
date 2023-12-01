package servicios;

import java.util.*;

import grafo.Grafo;

public class ServicioBFS {
	private Grafo<?> grafo;

	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
	}

	public List<Integer> bfsForest() {
		List<Integer> forest = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		Iterator<Integer> it = this.grafo.obtenerVertices();
		while (it.hasNext()) {
			int vertice = it.next();
			if (!visited.contains(vertice)) {
				List<Integer> tree = bfsTree(vertice, visited);
				forest.addAll(tree);
			}
		}
		return forest;
	}

	private List<Integer> bfsTree(Integer node, Set<Integer> visited) {
		List<Integer> tree = new ArrayList<>();
		Queue<Integer> queue = new LinkedList<>();

		visited.add(node);
		queue.offer(node);

		while (!queue.isEmpty()) {
			Integer currNode = queue.poll();
			tree.add(currNode);
			Iterator<Integer> it = this.grafo.obtenerAdyacentes(currNode);

			while (it.hasNext()) {
				int adyacente = it.next();
				if (!visited.contains(adyacente)) {
					visited.add(adyacente);
					queue.offer(adyacente);
				}
			}
		}

		return tree;
	}

}