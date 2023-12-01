package servicios;

import java.util.*;

import grafo.Grafo;

public class ServicioDFS {

    private Grafo<?> grafo;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> dfsForest() {
        Set<Integer> visitados = new HashSet<>();
        List<Integer> finalizados = new ArrayList<>();
        List<Integer> auxFinalizados = new ArrayList<>();
        Iterator<Integer> it = this.grafo.obtenerVertices();
        while (it.hasNext()) {
            int vertice = it.next();
            if (!visitados.contains(vertice)) {
                auxFinalizados = dfs(vertice, visitados);
                finalizados.addAll(auxFinalizados);
            }
        }
        return finalizados;
    }

    private List<Integer> dfs(Integer u, Set<Integer> visitados) {
        List<Integer> finalizados = new ArrayList<>();
        Iterator<Integer> it = this.grafo.obtenerAdyacentes(u);
        visitados.add(u);
        while (it.hasNext()) {
            int vertice = it.next();
            if (!visitados.contains(vertice)) {
                List<Integer> resultadoTemporal = dfs(vertice, visitados);
                finalizados.addAll(resultadoTemporal);
            }
        }
        finalizados.add(u);
        return finalizados;
    }
}
