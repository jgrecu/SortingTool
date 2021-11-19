package sorting;

import java.util.*;

public class Main {
    public static void main(final String[] args) {
        parseArgs(args);

    }

    private static void parseArgs(String[] args) {
        List<String> arguments = Arrays.asList(args);
        boolean byCount = false;

        arguments.stream().filter(s -> s.startsWith("-"))
                .filter(s -> !s.equals("-sortingType"))
                .filter(s -> !s.equals("-dataType"))
                .forEach(s -> System.out.println("\"" + s + "\"" + " is not a valid parameter. It will be skipped."));
        if (arguments.contains("-sortingType")) {
            int sortingTypeIndex = arguments.indexOf("-sortingType") + 1;
            if (sortingTypeIndex >= arguments.size() ||
                    !"natural".equals(arguments.get(sortingTypeIndex)) &&
                            !"byCount".equals(arguments.get(sortingTypeIndex))) {
                System.out.println("No sorting type defined!");
                return;
            } else if ("byCount".equals(arguments.get(sortingTypeIndex))) {
                byCount = true;
            }
        }
        if (arguments.contains("-dataType")) {
            int dataTypeIndex = arguments.indexOf("-dataType") + 1;
            if (dataTypeIndex >= arguments.size() || !"long".equals(arguments.get(dataTypeIndex)) &&
                    !"line".equals(arguments.get(dataTypeIndex)) && !"word".equals(arguments.get(dataTypeIndex))) {
                System.out.println("No data type defined!");
                return;
            } else if ("long".equals(arguments.get(dataTypeIndex))) {
                parseLong(byCount);
            } else if ("line".equals(arguments.get(dataTypeIndex))) {
                parseLine(byCount);
            } else if ("word".equals(arguments.get(dataTypeIndex))) {
                parseWords(byCount);
            }
        } else {
            parseWords(byCount);
        }
    }

    private static void parseLong(boolean byCount) {
        Scanner scanner = new Scanner(System.in);
        List<Long> list = new ArrayList<>();
        while (scanner.hasNext()) {
            String number = scanner.next();
            boolean isNumeric = number.matches("[+-]?\\d*(\\.\\d+)?");
            if (!isNumeric) {
                System.out.println("\"" + number + "\"" + " is not a long. It will be skipped.");
                continue;
            }
            list.add(Long.parseLong(number));
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
