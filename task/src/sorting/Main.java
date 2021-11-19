package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        List<String> arguments = Arrays.asList(args);
        boolean sorted = false;

        if (arguments.contains("-sortIntegers")) {
            sorted = true;
        }
        if (arguments.contains("long")) {
            parseLong(sorted);
        } else if (arguments.contains("line")) {
            parseLine(sorted);
        } else {
            parseWords(sorted);
        }
    }

    private static void parseLong(boolean sorted) {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
        }
        System.out.printf("Total numbers: %d.%n", list.size());
        if (sorted) {
            System.out.print("Sorted data: ");
            Collections.sort(list);
            list.forEach(i -> System.out.print(i + " "));
        } else {
            long maxLong = list.stream().mapToLong(v -> v).max().orElse(0);
            long countMax = list.stream().filter(v -> v == maxLong).count();
            long percent = (countMax * 100) / list.size();
            System.out.printf("The greatest number: %d (%d time(s), %d%%).%n", maxLong, countMax, percent);
        }
        scanner.close();
    }

    private static void parseLine(boolean sorted) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
        }
        System.out.printf("Total lines: %d.%n", list.size());
        if (sorted) {
            System.out.print("Sorted data: ");
            Collections.sort(list, Comparator.comparing(String::length));
            list.forEach(i -> System.out.print(i + " "));
        } else {
            String maxLine = Collections.max(list, Comparator.comparing(String::length));
            long countMax = Collections.frequency(list, maxLine);
            long percent = (countMax * 100) / list.size();
            System.out.printf("The longest line: %n%s%n(%d time(s), %d%%).%n", maxLine, countMax, percent);
        }
        scanner.close();
    }

    private static void parseWords(boolean sorted) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\s+");
            list.addAll(Arrays.asList(line));
        }
        System.out.printf("Total words: %d.%n", list.size());
        if (sorted) {
            System.out.print("Sorted data: ");
            list.stream().map(Integer::parseInt).sorted().forEach(i -> System.out.print(i + " "));
//            Collections.sort(list, Comparator.comparing(String::length));
//            list.forEach(i -> System.out.print(i + " "));
        } else {
            String maxWord = Collections.max(list, Comparator.comparing(String::length));
            long countMax = Collections.frequency(list, maxWord);
            long percent = (countMax * 100) / list.size();
            System.out.printf("The longest word: %s (%d time(s), %d%%).%n", maxWord, countMax, percent);
        }
        scanner.close();
    }
}
