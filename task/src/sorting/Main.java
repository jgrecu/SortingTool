package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        parseArgs(args);
    }

    private static void parseArgs(String[] args) {
        List<String> arguments = Arrays.asList(args);
        boolean byCount = false;

        if (arguments.contains("-sortingType")) {
            if (arguments.contains("byCount")) {
                byCount = true;
            }
        }
        if (arguments.contains("-dataType")) {
            if (arguments.contains("long")) {
                parseLong(byCount);
            } else if (arguments.contains("line")) {
                parseLine(byCount);
            } else {
                parseWords(byCount);
            }
        } else {
            parseWords(byCount);
        }
    }

    private static void parseLong(boolean byCount) {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
        }
        System.out.printf("Total numbers: %d.%n", list.size());
        if (byCount) {
            int size = list.size();
            List<Long> listNoDuplicates = new ArrayList<>(new TreeSet<>(list));

            Collections.sort(listNoDuplicates, Comparator.comparing(i -> Collections.frequency(list, i)));

            listNoDuplicates.forEach(i -> System.out.println(i + ": " +
                    Collections.frequency(list, i) + " time(s), " +
                    (Collections.frequency(list, i) * 100) / size + "%"));

        } else {
            System.out.print("Sorted data: ");
            Collections.sort(list);
            list.forEach(i -> System.out.print(i + " "));
        }
        scanner.close();
    }

    private static void parseLine(boolean byCount) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
        }
        System.out.printf("Total lines: %d.%n", list.size());
        if (byCount) {
            int size = list.size();
            List<String> listNoDuplicates = new ArrayList<>(new TreeSet<>(list));
            Collections.sort(listNoDuplicates, Comparator.comparing(String::length).reversed());
            Collections.sort(listNoDuplicates, Comparator.comparing(i -> Collections.frequency(list, i)));
            listNoDuplicates.forEach(i -> System.out.println(i + ": " +
                    Collections.frequency(list, i) + " time(s), " +
                    (Collections.frequency(list, i) * 100) / size + "%"));
        } else {
            System.out.println("Sorted data:");
            Collections.sort(list, Comparator.comparing(String::length).reversed());
            list.forEach(System.out::println);
        }
        scanner.close();
    }

    private static void parseWords(boolean byCount) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\s+");
            list.addAll(Arrays.asList(line));
        }
        System.out.printf("Total words: %d.%n", list.size());
        if (byCount) {
            int size = list.size();
            List<String> listNoDuplicates = new ArrayList<>(new TreeSet<>(list));

            Collections.sort(listNoDuplicates,
                    Comparator.comparing(i -> Collections.frequency(list, i)).thenComparing(Object::toString));

            listNoDuplicates.forEach(i -> System.out.println(i + ": " +
                    Collections.frequency(list, i) + " time(s), " +
                    (Collections.frequency(list, i) * 100) / size + "%"));

        } else {
            System.out.print("Sorted data: ");
            Collections.sort(list);
            list.forEach(i -> System.out.print(i + " "));
        }
        scanner.close();
    }
}
