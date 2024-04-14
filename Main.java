import java.util.*;

// Clase para representar las aristas del grafo
class Edge {
    char destination;
    double weight;

    Edge(char dest, double w) {
        destination = dest;
        weight = w;
    }
}

// Clase para representar el grafo
class Graph {
    private Map<Character, List<Edge>> adjList;

    Graph() {
        adjList = new HashMap<>();
    }

    // Método para agregar una arista al grafo
    void addEdge(char src, char dest, double weight) {
        adjList.computeIfAbsent(src, k -> new ArrayList<>()).add(new Edge(dest, weight));
    }

    // Método para el recorrido en anchura del grafo
    void BFS(char start) {
        Map<Character, Boolean> visited = new HashMap<>();
        Queue<Character> queue = new LinkedList<>();

        visited.put(start, true);
        queue.add(start);

        while (!queue.isEmpty()) {
            char current = queue.poll();
            System.out.print(current + " ");

            for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.containsKey(edge.destination)) {
                    visited.put(edge.destination, true);
                    queue.add(edge.destination);
                }
            }
        }
        System.out.println();
    }

    // Método para el recorrido en profundidad del grafo
    void DFS(char start) {
        Map<Character, Boolean> visited = new HashMap<>();
        Stack<Character> stack = new Stack<>();

        visited.put(start, true);
        stack.push(start);

        while (!stack.isEmpty()) {
            char current = stack.pop();
            System.out.print(current + " ");

            for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
                if (!visited.containsKey(edge.destination)) {
                    visited.put(edge.destination, true);
                    stack.push(edge.destination);
                }
            }
        }
        System.out.println();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Graph graph = new Graph();
        char option;

        do {
            System.out.println("Menu:");
            System.out.println("1. Crear grafo");
            System.out.println("2. Recorrido en anchura");
            System.out.println("3. Recorrido en profundidad");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            option = scanner.next().charAt(0);

            switch (option) {
                case '1': {
                    System.out.print("Ingrese el número de aristas: ");
                    int numEdges = scanner.nextInt();
                    System.out.println("Ingrese las aristas en el formato: Origen Destino Peso");
                    for (int i = 0; i < numEdges; ++i) {
                        char src = scanner.next().charAt(0);
                        char dest = scanner.next().charAt(0);
                        double weight = scanner.nextDouble();
                        graph.addEdge(src, dest, weight);
                    }
                    break;
                }
                case '2': {
                    System.out.print("Ingrese el nodo origen para el recorrido en anchura: ");
                    char start = scanner.next().charAt(0);
                    System.out.print("Recorrido en anchura: ");
                    graph.BFS(start);
                    break;
                }
                case '3': {
                    System.out.print("Ingrese el nodo origen para el recorrido en profundidad: ");
                    char start = scanner.next().charAt(0);
                    System.out.print("Recorrido en profundidad: ");
                    graph.DFS(start);
                    break;
                }
                case '4': {
                    System.out.println("Saliendo del programa.");
                    break;
                }
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (option != '4');

        scanner.close();
    }
}
