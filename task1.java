public class task1 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]), m = Integer.parseInt(args[1]);
        if (n < 1 || m < 1)
            return;
        int point = 1;        // start point
        StringBuilder arrayNumbers = new StringBuilder();
        do {
            arrayNumbers.append(point);
            point = (point + m - 1) % n;
            if (point == 0)
                point = n;

        } while (point != 1);
        System.out.println(arrayNumbers);
    }
}
