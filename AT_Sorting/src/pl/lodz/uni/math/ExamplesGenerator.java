package pl.lodz.uni.math;

import java.util.Random;

public class ExamplesGenerator {
    public static int[] generateExamples(int howMuch) {
        Random rand = new Random();
        int[] examples = new int[howMuch];
        for (int i = 0; i <= examples.length - 1; i++) {
            examples[i] = rand.nextInt();
        }
        return examples;
    }

}
