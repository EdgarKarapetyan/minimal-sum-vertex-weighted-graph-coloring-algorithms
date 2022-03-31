import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Algorithm2 {
    public List<Vertex> vertices;

    public Algorithm2()
    {
        vertices = GraphGenerator.getVerticesFromFile("chromatic-graph-theory-main/chromatic-graph-theory-main/input/graph-.txt");
    }

    public int execute() {
        Vertex maxWeightVertex = vertices.get(0);
        for(Vertex v : vertices){
            if(v.weight > maxWeightVertex.weight){
                maxWeightVertex = v;
            }
        }

        List<Vertex> L = new ArrayList<>();
        List<Vertex> temp = new ArrayList<>();
        L.add(maxWeightVertex);
        while (!L.isEmpty()) {
            L.sort((v1, v2) -> v2.weight - v1.weight);
            for (Vertex v : L) {
                ColorWithLeastPossibleColor(v);
                for(Vertex n : v.neighbors){
                    if(n.color == -1 && !temp.contains(n) && !L.contains(n)){
                        temp.add(n);
                    }
                }
            }
            L.clear();
            L.addAll(temp);
            temp.clear();
        }
        for (Vertex vertex : vertices) {
            System.out.println("V" + vertex.index + ": " + vertex.color);
        }
        System.out.println("Vertex weight sum = " + Sum());
        return Sum();
    }

    private void ColorWithLeastPossibleColor(Vertex vertex){
        boolean colored = false;
        int color = 0;
        while(!colored){
            if(possibleToColor(vertex, color)){
                vertex.color = color;
                colored = true;
            }
            color++;
        }
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

    public static void main(String[] args) {
        Algorithm2 algorithm2 = new Algorithm2();
        algorithm2.execute();
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

    private void init2() {
        vertices = new ArrayList<>();

        Vertex v0 = new Vertex(0, 1);
        Vertex v1 = new Vertex(1, 2);
        Vertex v2 = new Vertex(2, 3);
        Vertex v3 = new Vertex(3, 4);
        Vertex v4 = new Vertex(4, 5);
        Vertex v5 = new Vertex(5, 6);
        Vertex v6 = new Vertex(6, 7);
        Vertex v7 = new Vertex(7, 8);

        v0.neighbors.addAll(Arrays.asList(v2,v4,v5));
        v1.neighbors.addAll(Arrays.asList(v4,v5,v6));
        v2.neighbors.addAll(Arrays.asList(v0,v3,v5));
        v3.neighbors.addAll(Arrays.asList(v2,v5,v7));
        v4.neighbors.addAll(Arrays.asList(v0,v1,v6,v7));
        v5.neighbors.addAll(Arrays.asList(v0,v1,v2,v3,v6));
        v6.neighbors.addAll(Arrays.asList(v1,v4,v5));
        v7.neighbors.addAll(Arrays.asList(v3,v4));

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
