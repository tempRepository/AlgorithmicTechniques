package pl.lodz.uni.math;

public class BubbleSort {

    public static void bubbleSort(Integer[] numbers) {
        int numbersSize = numbers.length - 1;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbersSize; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
            numbersSize--;
        }
    }

    public static void main(String[] args) {
        Integer[] numbers = { 7, 4, 8, 2, 5, 1 };
        bubbleSort(numbers);
        for (Integer integer : numbers) {
            System.out.println(integer);
        }

    }

}
