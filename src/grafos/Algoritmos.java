package grafos;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Nesta classe devem ser implementados todos os métodos de grafos de forma estática
 * @author vilson.junior
 */
public class Algoritmos {
    
    public static ArrayList<Vertice> percorreLargura(Grafo g, Vertice inicio) {
        ArrayList<Vertice> resultado = new ArrayList<>();

        return resultado;
    }

    // Código simples
    public static List<Arco> kruskal(Grafo g) {
        List<Arco> arcosOrdenados = new ArrayList<>(g.obterTodosOsArcos());
        arcosOrdenados.sort(Comparator.comparingDouble(Arco::getPeso));

        ArrayList<Vertice> vertices = g.obterVertices();

        int count = 1;
        for (Vertice v : vertices) {
            v.setnArvore(count++);
        }

        List<Arco> mst = new ArrayList<>();

        for (Arco a : arcosOrdenados) {
            if (a.getOrigem() != null && a.getDestino() != null) {
                int treeOrigem = a.getOrigem().getnArvore();
                int treeDestino = a.getDestino().getnArvore();

                if (treeOrigem != treeDestino) {
                    mst.add(a);

                    for (Vertice v : g.obterVertices()) {
                        if (v.getnArvore() == treeDestino) {
                            v.setnArvore(treeOrigem);
                        }
                    }
                }
            }
        }

        return mst;
    }
}

// Código otimizado
//    public static List<Arco> kruskal(Grafo g) {
//        List<Arco> arcosOrdenados = new ArrayList<>(g.obterTodosOsArcos());
//        arcosOrdenados.sort(Comparator.comparingDouble(Arco::getPeso));
//
//        Map<Vertice, Vertice> parent = new HashMap<>();
//        for (Vertice v : g.obterVertices()) {
//            parent.put(v, v);
//        }
//
//        List<Arco> mst = new ArrayList<>();
//
//        for (Arco arco : arcosOrdenados) {
//            Vertice u = arco.getOrigem();
//            Vertice v = arco.getDestino();
//
//            if (u != null && v != null) {
//                Vertice rootU = find(parent, u);
//                Vertice rootV = find(parent, v);
//
//                if (!rootU.equals(rootV)) {
//                    mst.add(arco);
//                    union(parent, rootU, rootV);
//                }
//            }
//        }
//
//        return mst;
//    }
//
//    private static Vertice find(Map<Vertice, Vertice> parent, Vertice v) {
//        if (!parent.get(v).equals(v)) {
//            parent.put(v, find(parent, parent.get(v)));
//        }
//        return parent.get(v);
//    }
//
//    private static void union(Map<Vertice, Vertice> parent, Vertice rootU, Vertice rootV) {
//        parent.put(rootV, rootU);
//    }


