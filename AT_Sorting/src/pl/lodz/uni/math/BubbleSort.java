package pl.lodz.uni.math;

public class BubbleSort {

    public static void bubbleSort(int[] numbersToSort) {
        int numbersSize = numbersToSort.length - 1;
        for (int i = 0; i < numbersToSort.length - 1; i++) {
            for (int j = 0; j < numbersSize; j++) {
                if (numbersToSort[j] > numbersToSort[j + 1]) {
                    int temp = numbersToSort[j];
                    numbersToSort[j] = numbersToSort[j + 1];
                    numbersToSort[j + 1] = temp;
                }
            }
            numbersSize--;
        }
    }

    public static void main(String[] args) {
        
        int[] result = null;
        long runningTimeSum=0;
        for (int i = 0; i < 100000; i++) {
            int[] numbersToSort = ExamplesGenerator.generateExamples(100);
            TimeCounter.start();
            bubbleSort(numbersToSort);
            runningTimeSum+=TimeCounter.stopTime();
        }
        System.out.println("Average time: "+runningTimeSum/100000);
    }

}
