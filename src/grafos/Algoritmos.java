package grafos;

import java.util.*;

/**
 * Nesta classe devem ser implementados todos os métodos de grafos de forma estática
 * @author vilson.junior
 */
public class Algoritmos {
    
    public static ArrayList<Vertice> percorreLargura(Grafo g, Vertice inicio) {
        ArrayList<Vertice> resultado = new ArrayList<>();

        return resultado;
    }


    public static List<Vertice> Profundidade(Grafo g) {
        ArrayList<Vertice> vertices = g.obterVertices();
        Stack<Vertice> pilha = new Stack<>();

        List<Vertice> ordemDeVisita = new ArrayList<>();

        pilha.add(vertices.get(0));
        pilha.lastElement().visitar();
        boolean repetir = true;

        while(repetir){
            Queue<Vertice> aux = new LinkedList<>();

            Vertice vAtual = pilha.lastElement();
            ordemDeVisita.add(vAtual);

            int n = vAtual.obterArcos().size();
            int c = -1;

            ;

            for (int i = 0; i < n; i++) {

                Vertice v = vAtual.obterArcos().get(i).getDestino();

                if (v.obterVisitado() == 0){
                    v.visitar();
                    aux.add(v);
                    c++;
                }

            }

            pilha.pop();

            if (!aux.isEmpty()){
                int m = aux.size();
                for (int i = 0; i < m; i++) {
                    pilha.add(aux.poll());
                }
            }

            if (pilha.isEmpty())
                repetir = false;
        }


        return ordemDeVisita;
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


