import java.util.*;
import java.util.stream.Collectors;

public class Algorithm3 {
    public List<Vertex> vertices;

    public Algorithm3() {
        vertices = GraphGenerator.getVerticesFromFile("chromatic-graph-theory-main/chromatic-graph-theory-main/input/graph-.txt");
    }

    public static void main(String[] args) {
        Algorithm3 algorithm3 = new Algorithm3();
        algorithm3.execute();
    }

    public int execute() {
        List<Vertex> sortedVertices = vertices.stream().sorted((new Comparator<Vertex>() {
            @Override
            public int compare(Vertex v1, Vertex v2) {
                return (v2.weight - v1.weight);
            }
        })).collect(Collectors.toList());

        int leastColor = 1;
        for (Vertex v : sortedVertices) {
            if (possibleToColor(v, leastColor)) {
                v.color = leastColor;
            } else {
                int minPossibleColor = leastColor + 1;
                while (!possibleToColor(v, minPossibleColor)) {
                    minPossibleColor++;
                }
                v.color = minPossibleColor;
            }
        }

        for (Vertex vertex : vertices) {
            System.out.println("V" + vertex.index + ": " + vertex.color);
        }
        System.out.println("Vertex weight sum = " + Sum());
        return Sum();
    }

    private boolean possibleToColor(Vertex vertex, int color) {
        for (Vertex v : vertex.neighbors) {
            if (v.color == color) {
                return false;
            }
        }
        return true;
    }

    public void PrintColors() {
        for (Vertex v : vertices) {
            System.out.println(v.toString());
        }
    }

    public int Sum(){
        int maxColor = 0;
        for (Vertex v: vertices) {
            if(v.color > maxColor)
                maxColor = v.color;
        }

        int sum = 0;
        for(int c = 0 ; c <= maxColor; c++){
            sum += MaxWeightOfColor(c);
        }
        return sum;
    }

    public int MaxWeightOfColor(int color){
        int maxWeight = 0;
        for (Vertex v: vertices) {
            if(v.color == color){
                if(v.weight > maxWeight)
                    maxWeight = v.weight;
            }
        }
        return maxWeight;
    }

    private void init() {
        vertices = new ArrayList<>();

        Vertex v0 = new Vertex(0, 1);
        Vertex v1 = new Vertex(1, 4);
        Vertex v2 = new Vertex(2, 5);
        Vertex v3 = new Vertex(3, 2);
        Vertex v4 = new Vertex(4, 4);
        Vertex v5 = new Vertex(5, 8);
        Vertex v6 = new Vertex(6, 1);
        Vertex v7 = new Vertex(7, 6);

        v0.neighbors.addAll(Arrays.asList(v1));
        v1.neighbors.addAll(Arrays.asList(v0, v2, v3, v6));
        v2.neighbors.addAll(Arrays.asList(v1, v4));
        v3.neighbors.addAll(Arrays.asList(v1, v4, v5));
        v4.neighbors.addAll(Arrays.asList(v2, v3, v6, v7));
        v5.neighbors.addAll(Arrays.asList(v3, v7));
        v6.neighbors.addAll(Arrays.asList(v1, v4, v7));
        v7.neighbors.addAll(Arrays.asList(v4, v5, v6));

        vertices.add(v0);
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
        vertices.add(v4);
        vertices.add(v5);
        vertices.add(v6);
        vertices.add(v7);
    }
}
