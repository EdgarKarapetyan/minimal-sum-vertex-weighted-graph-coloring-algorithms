import java.util.HashSet;
import java.util.Set;

public class Vertex {
    public int index;
    public int weight;
    public int color = -1;
    public Set<Vertex> neighbors = new HashSet<>();

    public Vertex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public Vertex(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Set<Vertex> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(Set<Vertex> neighbors) {
        this.neighbors = neighbors;
    }
}