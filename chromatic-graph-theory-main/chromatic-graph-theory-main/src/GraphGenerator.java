import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class GraphGenerator {

    private static final int MAX_VERTICES_NUMBER = 15;
    private static final int MAX_WEIGHT = 100;
    private static final Random random = new Random();

    private static List<Vertex> vertices;
    private static int verticesNo;

    public static void main(String[] args) {
        generate();

//        List<Vertex> vertices = getVerticesFromFile("input/graph-2021-06-05T15:55:43.025.txt");
    }

    public static void generate() {
        verticesNo = random.nextInt(MAX_VERTICES_NUMBER) + 5;
        vertices = new ArrayList<>();
        for (int i = 0; i < verticesNo; i++) {
            vertices.add(new Vertex(i));
        }

        vertices = vertices.stream()
                .peek(GraphGenerator::randomConnect)
                .peek(GraphGenerator::randomWeight)
                .collect(Collectors.toList());

        writeToFile();
    }

    private static void randomWeight(Vertex vertex) {
        vertex.weight = random.nextInt(MAX_WEIGHT) + 1;
    }

    private static void randomConnect(Vertex vertex) {

        boolean isConnected;
        for (int i = 0; i < verticesNo; i++) {

            if (i == vertex.index) {
                continue;
            }

            isConnected = random.nextBoolean();
            if (isConnected) {
                Vertex neighbor = vertices.get(i);
                vertex.neighbors.add(neighbor);
                neighbor.neighbors.add(vertex);
            }
        }
    }


    private static void writeToFile() {
        LocalDateTime now = LocalDateTime.now();
        try(FileWriter myWriter = new FileWriter("chromatic-graph-theory-main/chromatic-graph-theory-main/input/graph-.txt" + ".txt")) {
            StringBuilder builder = new StringBuilder();
            builder.append(vertices.size()).append('\n');

            vertices.forEach(vertex -> {
                builder.append(vertex.index).append(" : ");
                builder.append(vertex.weight).append(" : ");
                for (Vertex v : vertex.neighbors) {
                    builder.append(v.index).append(", ");
                }
                builder.append('\n');
            });

            myWriter.write(builder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<Vertex> getVerticesFromFile(String filename) {
        File myObj = new File(filename);
        List<Vertex> vertices = new ArrayList<>();

        try(Scanner myReader = new Scanner(myObj)) {
            int size = Integer.parseInt(myReader.nextLine());

            for (int i = 0; i < size; i++) {
                vertices.add(new Vertex(i));
            }

            while (myReader.hasNextLine()) {

                String data = myReader.nextLine();

                String[] split = data.split(" : ");
                String[] neighborsStr = split[2].split(", ");

                int index = Integer.parseInt(split[0]);
                int weight = Integer.parseInt(split[1]);

                Set<Vertex> neighbors = new HashSet<>();
                for (String neighborStr :neighborsStr) {
                    int neighborIdx = Integer.parseInt(neighborStr);
                    neighbors.add(vertices.get(neighborIdx));
                }

                vertices.get(index).setWeight(weight);
                vertices.get(index).setNeighbors(neighbors);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return vertices;
    }


}
