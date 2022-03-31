import java.util.*;

public class Tester {
    public Algorithm1 algorithm1;
    public Algorithm2 algorithm2;
    public Algorithm3 algorithm3;

    public Tester(){
    }

    public static void main(String[] args) {
        ArrayList<Integer> result1 = new ArrayList<>();
        ArrayList<Integer> result2 = new ArrayList<>();
        ArrayList<Integer> result3 = new ArrayList<>();
        for(int i = 1 ; i <= 100; i++ ){
            System.out.println("-- Test " + i + " --");
            GraphGenerator.generate();
            Algorithm1 algorithm1 = new Algorithm1();
            Algorithm2 algorithm2 = new Algorithm2();
            Algorithm3 algorithm3 = new Algorithm3();

            System.out.println("Algorithm 1 : ");
            result1.add(algorithm1.execute());
            System.out.println();
            System.out.println("Algorithm 2 : ");
            result2.add(algorithm2.execute());
            System.out.println();
            System.out.println("Algorithm 3 : ");
            result3.add(algorithm3.execute());
            System.out.println();
        }

        for(int i = 0; i< result1.size(); i++){
            System.out.println(result1.get(i).toString() + '\t' + result2.get(i).toString() + '\t' + result3.get(i).toString() + '\t');
        }
    }

}
