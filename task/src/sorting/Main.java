package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();

        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
        }

        System.out.printf("Total numbers: %d.%n", list.size());
        long maxLong = list.stream().mapToLong(v -> v).max().orElse(0);
        long countMax = list.stream().filter(v -> v == maxLong).count();
        System.out.printf("The greatest number: %d (%d time(s)).%n", maxLong, countMax);
    }
}
