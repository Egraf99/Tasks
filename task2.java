import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class task2 {
    public static ArrayList<Float> readFile(String filePath) throws IOException {
        File file1 = new File(filePath);
        Scanner sc = new Scanner(file1);
        ArrayList<Float> numbers = new ArrayList<>();
        while (sc.hasNextLine()) {
            String[] numberStrings = sc.nextLine().split(" ");
            for (String number : numberStrings) {
                numbers.add(Float.parseFloat(number));
            }
        }
        sc.close();
        return numbers;
    }

    public static int pointInCircle(ArrayList<Float> circle, float pointX, float pointY) {
        float x0 = circle.get(0), y0 = circle.get(1), rad = circle.get(2);
        double rad2 = Math.pow(rad, 2);
        double vector = Math.pow((pointX - x0), 2) + Math.pow((pointY - y0), 2);
        if (rad2 < vector)
            return 1;
        else if (rad2 == vector)
            return 0;
        else
            return 2;
    }

    public static void main(String[] args) throws IOException {
        String file1 = args[0], file2 = args[1];
        ArrayList<Float> circleCoord = readFile(file1);
        ArrayList<Float> points = readFile(file2);
        for (int i = 0; i < points.size(); i++) {
            if (i % 2 != 0) {
                int answer = pointInCircle(circleCoord, points.get(i - 1), points.get(i));
                System.out.println(answer);
            }
        }
    }
}
