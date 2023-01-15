package Java_WeightedProbability;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class wighted_probability {

    public static void main(String[] args) {
        // Create a list of letters
        List<Letters> letters = new ArrayList<>();

        // Add 10 letters to the list with their corresponding count and probability
        letters.add(new Letters("A", 0, 0.2));
        letters.add(new Letters("B", 0, 0.2));
        letters.add(new Letters("C", 0, 0.1));
        letters.add(new Letters("D", 0, 0.1));
        letters.add(new Letters("E", 0, 0.1));
        letters.add(new Letters("F", 0, 0.1));
        letters.add(new Letters("G", 0, 0.05));
        letters.add(new Letters("H", 0, 0.05));
        letters.add(new Letters("I", 0, 0.05));
        letters.add(new Letters("J", 0, 0.05));

        // Number of selection tests
        int testCount = 100000;

        //Tests with non-weighted probability
        System.out.println("-----------------------------------");
        System.out.println("Tests with non-weighted probability");
        System.out.println("-----------------------------------");
        nonWeightedProbability(letters, testCount);

        //Tests with weighted probability
        System.out.println("-----------------------------------");
        System.out.println("Tests with weighted probability");
        System.out.println("-----------------------------------");
        weightedProbability(letters, testCount);
    }

    /**
     * Method for performing selection tests with non-weighted probability.
     *
     * @param letters list of letters to perform tests on
     * @param testCount number of tests to perform
     */
    private static void nonWeightedProbability(List<Letters> letters, int testCount) {
        for (int i = 0; i < testCount; i++) {
            // Generate random number between 0 and size of letters list minus 1
            Random random = new Random();
            int min = 0;
            int max = letters.size() - 1;
            int randomNumber = random.nextInt(max - min + 1) + min;

            // Select letter at index of random number and increment its count by 1
            String selectedLetter = letters.get(randomNumber).getLetter();
            letters.stream()
                    .filter(a -> a.getLetter().equals(selectedLetter))
                    .findFirst()
                    .ifPresent(l -> l.setCount(l.getCount() + 1));
        }

        // Print count and percentage of each letter
        for (Letters letter : letters) {
            System.out.println(letter.getLetter()
                    + " -> " + letter.getCount()
                    + "(" + String.format("%.2f", (letter.getCount() * Math.pow(testCount, -1)) * 100) + "%)");
        }

        // Reset count of each letter to 0
        letters.forEach(a -> a.setCount(0));
    }

    /**
     *
     * Method for performing selection tests with weighted probability.
     *
     * @param letters list of letters to perform tests on
     * @param testCount number of tests to perform
     */
    private static void weightedProbability(List<Letters> letters, int testCount) {
        //Repeat the selection process 'testCount' times
        for (int i = 0; i < testCount; i++) {
            //List to hold the cumulative probabilities of each letter
            List<Double> cumulativeProbabilities = new ArrayList<>();
            double sum = 0;
            //Calculate cumulative probabilities for each letter
            for (Letters l : letters) {
                sum += l.getProbability();
                cumulativeProbabilities.add(sum);
            }
            //Generate a random number between 0 and 1
            double r = Math.random() * sum;
            //Select a letter based on the cumulative probabilities
            String selectedLetter = letters.stream()
                    //Find the first letter whose cumulative probability is greater than the random number
                    .filter(l -> r < cumulativeProbabilities.get(letters.indexOf(l)))
                    .findFirst().get().getLetter();

            //Increment the count for the selected letter
            letters.stream()
                    .filter(a -> a.getLetter().equals(selectedLetter))
                    .findFirst()
                    .ifPresent(l -> l.setCount(l.getCount() + 1));
        }
        //After 'testCount' loops, print out the number of times each letter was selected and the percentage it represents
        for (Letters letter : letters) {
            System.out.println(letter.getLetter()
                    + " -> " + letter.getCount()
                    + "(" + String.format("%.2f", (letter.getCount() * Math.pow(testCount, -1)) * 100) + "%)");
        }
        //Reset the count for each letter to 0
        letters.forEach(a -> a.setCount(0));
    }

    private static class Letters {

        private String letter;
        private int count;
        private double probability;

        public Letters(String letter, int count, double probability) {
            this.letter = letter;
            this.count = count;
            this.probability = probability;
        }

        public double getProbability() {
            return probability;
        }

        public void setProbability(double probability) {
            this.probability = probability;
        }

        public String getLetter() {
            return letter;
        }

        public void setLetter(String letter) {
            this.letter = letter;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
