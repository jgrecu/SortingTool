package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {

        if (args[0].startsWith("-data")) {
            String command = args[1];
            switch (command) {
                case "long":
                    parseLong();
                    break;
                case "line":
                    parseLine();
                    break;
                case "word":
                    parseWords();
                    break;
                default:
                    break;
            }
        } else {
            parseWords();
        }
    }

    private static void parseLong() {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNextLong()) {
            long number = scanner.nextLong();
            list.add(number);
        }
        System.out.printf("Total numbers: %d.%n", list.size());
        long maxLong = list.stream().mapToLong(v -> v).max().orElse(0);
        long countMax = list.stream().filter(v -> v == maxLong).count();
        long percent = (countMax * 100) / list.size();
        System.out.printf("The greatest number: %d (%d time(s), %d%%).%n", maxLong, countMax, percent);
        scanner.close();
    }

    private static void parseLine() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            list.add(line);
        }
        System.out.printf("Total lines: %d.%n", list.size());
//        String maxLine = Collections.max(list, Comparator.comparing(String::length));
        String maxLine = Collections.max(list, Comparator.comparing(s -> s));
        long countMax = list.stream().filter(maxLine::equals).count();
        long percent = (countMax * 100) / list.size();
        System.out.printf("The longest line: %n%s%n(%d time(s), %d%%).%n", maxLine, countMax, percent);
        scanner.close();
    }

    private static void parseWords() {
        Scanner scanner = new Scanner(System.in);
        List<String> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String[] line = scanner.nextLine().split("\\s+");
            list.addAll(Arrays.asList(line));
        }
        System.out.printf("Total words: %d.%n", list.size());
        String maxWord = Collections.max(list, Comparator.comparing(String::length));
        long countMax = list.stream().filter(maxWord::equals).count();
        long percent = (countMax * 100) / list.size();
        System.out.printf("The longest word: %s (%d time(s), %d%%).%n", maxWord, countMax, percent);
        scanner.close();
    }
}
