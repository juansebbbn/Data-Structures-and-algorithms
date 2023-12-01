package test;

import java.util.*;
import grafo.*;
import servicios.*;

public class Main {
    public static void main(String[] args) {

        GrafoDirigido<Integer> grafo = new GrafoDirigido<>();

        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);

        grafo.agregarArco(1, 2, 90);
        grafo.agregarArco(2, 3, 233);
        grafo.agregarArco(2, 4, 100);
        grafo.agregarArco(1, 5, 140);
        grafo.agregarArco(5, 4, 2131);
        grafo.agregarArco(3, 6, 3452);
        grafo.agregarArco(6, 4, 2345);
        grafo.agregarArco(3, 4, 2376);

        System.out.println("");
        System.out.println("TPE PROGRMACION 3: ");
        System.out.println("");

        // IMPRESION SIN ELIMINAR NINGUNA ARCO NI NODO
        System.out.println("IMPRESION SIN BORRAR NINGUN ARCO NI NODO: ");
        grafo.print(); 
        System.out.println("");
        System.out.println("CANTIDAD DE ARCOS: " + grafo.cantidadArcos());
        System.out.println("CANTIDAD DE VERTICES: " + grafo.cantidadVertices());
        System.out.println("CONTIENE VERTICE N (3)...?: " + grafo.contieneVertice(3));
        System.out.println("CONTIENE VERTICE N (123)...?: " + grafo.contieneVertice(123));
        System.out.println("EXISTE ARCO N (1 A 5)...?: " + grafo.existeArco(1, 5));
        System.out.println("EXISTE ARCO N (2 A 65)...?: " + grafo.existeArco(2, 65));
        System.out.println("ARCO N (1 A 5): " + grafo.obtenerArco(1, 5));
        System.out.println("ARCO N (23 A 43): " + grafo.obtenerArco(23, 43));
        System.out.println("");

        // ITERADORES
        System.out.println("VERTICES: ");
        Iterator<Integer> itV = grafo.obtenerVertices();
        while (itV.hasNext()) {
            int ady = itV.next();
            System.out.println(ady);
        }
        System.out.println("");

        //

        System.out.println("ADYACENTES A N (2): ");
        Iterator<Integer> itA = grafo.obtenerAdyacentes(2);
        while (itA.hasNext()) {
            int ady = itA.next();
            System.out.println(ady);
        }
        System.out.println("");

        //

        System.out.println("ARCOS:  ");
        Iterator<Arco<Integer>> itArc = grafo.obtenerArcos();
        while (itArc.hasNext()) {
            Arco<Integer> arco = itArc.next();
            System.out.println(arco);
        }
        System.out.println("");

        //

        System.out.println("ARCOS DE N (2): ");
        Iterator<Arco<Integer>> itAArc = grafo.obtenerArcos(2);
        while (itAArc.hasNext()) {
            Arco<Integer> arcoDeN = itAArc.next();
            System.out.println(arcoDeN);
        }
        System.out.println("");

        // SERVICIOS
        System.out.println("RECORRIDO BFS: ");
        ServicioBFS servicioBFS = new ServicioBFS(grafo);
        System.out.println(servicioBFS.bfsForest());

        System.out.println("RECORRIDO DFS: ");
        ServicioDFS servicioDFS = new ServicioDFS(grafo);
        System.out.println(servicioDFS.dfsForest());

        System.out.println("CAMINOS POSIBLES ENTRE N Y X (1 Y 4), CON LIMITE 3: ");
        ServicioCaminos servicioCaminos = new ServicioCaminos(grafo, 1, 4, 3);
        System.out.println(servicioCaminos.caminos());

        System.out.println("");

        // ELIMINANDO ARCO Y VERTICE, LUEGO VUELVO A IMPRIMIR.
        System.out.println("BORRANDO VERTICE N (2)...");
        grafo.borrarVertice(2);

        System.out.println();

        System.out.println("BORRANDO ARCO N (1 A 5)...");
        grafo.borrarArco(1, 5);
        System.out.println();

        System.out.println("IMPRESION CON ARCO N (1 A 5) Y VERTICE N (2) ELIMINADO: ");
        grafo.print();

        System.out.println(":)");
    }
}
