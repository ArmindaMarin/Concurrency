import java.util.Random;

public class Assignment1_1 {
    private int boundry = 8000000;
    private int arrayLenght = 10;
    private int[] mainArray = new int[arrayLenght];
    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;

    public static void main(String[] args) {
        new Assignment1_1().run();
    }

    private void run() {
        generateNumbers(mainArray, boundry, genNumers);
        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);
        selectionSort(mainArray);
        System.out.println('\n' + "sorted list:" + '\n');
        printOutList(mainArray);

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }

    // Generating a list of numbers
    private void generateNumbers(int[] array, int boundry, Random genNumers) {

        for (int i = 0; i < array.length; i++) {
            int newNumber = genNumers.nextInt(boundry) + 1;
            array[i] = newNumber;
        }
    }

    // Sorting a list by using selection sort
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

        return unSortedArray;
    }

    // This is just to print
    private void printOutList(int[] array) {
        for (int element : array) {
            System.out.println(element);
        }
    }
}
