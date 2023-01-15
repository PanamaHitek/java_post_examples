package Java_TravellingSalesman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations {

    // Constant strings to specify the output order of permutations
    public static final String SORTED = "sorted";
    public static final String RANDOM = "random";
    private static String outputOrder = SORTED;

    // constructor
    public Permutations(String outputOrder) {
        this.outputOrder = outputOrder;
    }

    /**
     * A recursive method that generates all possible permutations of a given
     * string
     *
     * @param str the string to generate permutations for
     * @return a list of all possible permutations of the input string
     */
    public static List<String> getPermutations(String str) {

        List<String> permutations = new ArrayList<>();
        if (str.length() == 0) {
            permutations.add("");
            return permutations;
        }
        char first = str.charAt(0);
        String remainder = str.substring(1);
        List<String> words = getPermutations(remainder);
        for (String word : words) {
            for (int i = 0; i <= word.length(); i++) {
                permutations.add(insertCharAt(word, first, i));
            }
        }
        if (outputOrder.equals(SORTED)) {
            permutations = permutations.stream().sorted((a, b) -> a.compareTo(b)).collect(Collectors.toList());
        } else {
            Collections.shuffle(permutations);
        }

        return permutations;
    }

    public static String getOutputOrder() {
        return outputOrder;
    }

    public static void setOutputOrder(String outputOrder) {
        Permutations.outputOrder = outputOrder;
    }

    /**
     * Inserts a character into a string at a specified index
     *
     * @param str the string to insert the character into
     * @param c the character to insert
     * @param i the index to insert the character at
     * @return the modified string
     */
    public static String insertCharAt(String str, char c, int i) {
        String start = str.substring(0, i);
        String end = str.substring(i);
        return start + c + end;
    }

}
