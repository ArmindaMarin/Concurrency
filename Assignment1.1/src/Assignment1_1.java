import java.util.Random;

public class Assignment1_1 {
    public static void main(String[] args) {
        new Assignment1_1().run();
    }

    private void run() {
        Random randomNumbers = new Random();
        long startTime = System.currentTimeMillis();
        long duration;

        int boundary = 8000000;
        int arrayLength = 10;

        int[] mainArray = new int[arrayLength];

        generateNumbers(mainArray, boundary, randomNumbers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);

        mainArray = selectionSort(mainArray);

        System.out.println('\n' + "sorted list:" + '\n');
        printOutList(mainArray);

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }

    // Generating a random numbers and puts them in a given array.
    private void generateNumbers(int[] array, int boundary, Random randomNumbers) {
        for (int i = 0; i < array.length; i++) {
            int newNumber = randomNumbers.nextInt(boundary) + 1;
            array[i] = newNumber;
        }
    }

    // Sorting a list by using selection sort.
    private int[] selectionSort(int[] unSortedArray) {

        for (int i = 0; i < unSortedArray.length; i++) {
            int smallestNumber = i;

            for (int j = i + 1; j < unSortedArray.length; j++) {
                if (unSortedArray[j] < unSortedArray[smallestNumber]) {
                    smallestNumber = j;
                }
            }
            int tempNumber = unSortedArray[smallestNumber];
            unSortedArray[smallestNumber] = unSortedArray[i];
            unSortedArray[i] = tempNumber;
        }
        // The array that is returned is sorted.
        return unSortedArray;
    }

    // This is just to print and see is all the sorting was done correctly.
    private void printOutList(int[] array) {
        for (int element : array) {
            System.out.println(element);
        }
    }

}
