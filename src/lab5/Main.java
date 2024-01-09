package lab5;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static double getAverage(List<Integer> numbers) {
        return numbers.stream()
                .mapToDouble(Integer::doubleValue)
                .average()
                .orElse(0.0);
    }

    public static List<String> convertToUpperCaseWithPrefix(List<String> strings) {
        return strings.stream()
                .map(s -> "_new_" + s.toUpperCase())
                .collect(Collectors.toList());
    }

    public static List<Integer> getUniqueElementsSquared(List<Integer> numbers) {
        return numbers.stream()
                .filter(i -> Collections.frequency(numbers, i) == 1)
                .map(i -> i * i)
                .collect(Collectors.toList());
    }

    public static List<String> filterAndSortByFirstLetter(List<String> strings, char letter) {
        return strings.stream()
                .filter(s -> s.startsWith(String.valueOf(letter)))
                .sorted()
                .collect(Collectors.toList());
    }

    public static <T> T getLastElement(Collection<T> collection) {
        return collection.stream()
                .reduce((first, second) -> second)
                .orElseThrow(NoSuchElementException::new);
    }

    public static int sumOfEvenNumbers(int[] numbers) {
        return Arrays.stream(numbers)
                .filter(n -> n % 2 == 0)
                .sum();
    }

    public static Map<Character, String> getMapByFirstCharacter(List<String> strings) {
        return strings.stream()
                .collect(Collectors.toMap(
                        s -> s.charAt(0),
                        s -> s.substring(1),
                        (existing, replacement) -> existing));
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println("Average: " + getAverage(numbers));

        List<String> strings = Arrays.asList("aboubakar", "davies", "haaland", "barcoly", "bellingham", "bombastic");
        System.out.println("Converted strings: " + convertToUpperCaseWithPrefix(strings));

        System.out.println("Unique elements squared: " + getUniqueElementsSquared(numbers));

        char letter = 'b';
        System.out.println("Filtered and sorted strings by letter '" + letter + "': " + filterAndSortByFirstLetter(strings, letter));

        try {
            List<String> emptyList = Collections.emptyList();
            System.out.println("Last element: " + getLastElement(emptyList));
        } catch (NoSuchElementException e) {
            System.out.println("The collection is empty.");
        }

        int[] intArray = {1, 2000, 20, 3};
        System.out.println("Sum of even numbers: " + sumOfEvenNumbers(intArray));

        List<String> stringList = Arrays.asList("sometimes", "home", "emergency", "callback", "cool");
        System.out.println("Mapped by first character: " + getMapByFirstCharacter(stringList));
    }
}
