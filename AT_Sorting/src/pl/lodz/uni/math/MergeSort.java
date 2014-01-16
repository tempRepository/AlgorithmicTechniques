package pl.lodz.uni.math;

public class MergeSort {
    public static int[] numbers;

    private static int[] temp;

    public static int[] sort(int[] unsortedNumbers) {
        numbers = unsortedNumbers;
        temp = new int[unsortedNumbers.length];
        mergeSort(0, unsortedNumbers.length - 1);
        return numbers;
    }

    private static void mergeSort(int start, int stop) {
        if (start < stop) {
            int middle = (start + stop) / 2;
            mergeSort(start, middle);
            mergeSort(middle + 1, stop);
            merge(start, middle, stop);
        }
    }

    private static void merge(int start, int middle, int end) {

        for (int i = start; i <= end; i++) {
            temp[i] = numbers[i];
        }

        int leftIterator = start;
        int rightIterator = middle + 1;
        int tempIterator = start;
        while (leftIterator <= middle && rightIterator <= end) {
            if (temp[leftIterator] <= temp[rightIterator]) {
                numbers[tempIterator] = temp[leftIterator];
                leftIterator++;
            } else {
                numbers[tempIterator] = temp[rightIterator];
                rightIterator++;
            }
            tempIterator++;
        }

        while (leftIterator <= middle) {
            numbers[tempIterator] = temp[leftIterator];
            tempIterator++;
            leftIterator++;
        }

        while (rightIterator <= end) {
            numbers[tempIterator] = temp[rightIterator];
            tempIterator++;
            rightIterator++;
        }

    }

    public static void main(String[] args) {
        int[] numbersToSort = { 2, 7, 4489, 23, 7, 7, 8, 3, 45, 2, 10 };
        int[] result = MergeSort.sort(numbersToSort);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
