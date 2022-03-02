import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class task4 {
    public static ArrayList<Integer> readFile(String filePath) throws FileNotFoundException, IOException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Integer> numbers = new ArrayList<>();
        while (sc.hasNextLine()) {
            numbers.add(Integer.parseInt(sc.nextLine()));
        }
        sc.close();
        return numbers;
    }


    public static int findAvg(ArrayList<Integer> array) {
        int sum = 0;
        for (Integer integer : array) {
            sum += integer;
        }
        return Math.abs(sum / array.size());
    }

    public static int findAnswer(ArrayList<Integer> array, int median) {
        int answer = 0;
        for (Integer integer : array) {
            answer += Math.abs(integer - median);
        }
        return answer;
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        String filePath = args[0];
        ArrayList<Integer> numbers = readFile(filePath);
        int median = findAvg(numbers);
        System.out.println(findAnswer(numbers, median));
    }
}