import java.util.Random;

public class Assignment1_2 {
    // These var need to be global so that the threads can access them.
    private int[] leftArray;
    private int[] rightArray;

    public static void main(String[] args) {

        new Assignment1_2().run();

    }

    private void run() {
        Random randomNumbers = new Random();
        long startTime = System.currentTimeMillis();
        long duration;

        int boundary = 100;
        int arrayLength = 10;

        int[] mainArray = new int[arrayLength];

        int leftArrayStart = 0;
        int leftArraySize = mainArray.length / 2;
        int rightArrayStart = leftArraySize + 1;
        int rightArraySize = mainArray.length - leftArraySize;

        generateNumbers(mainArray, boundary, randomNumbers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                int[] leftUnArray = splitArray(mainArray, leftArrayStart, leftArraySize);
                leftArray = selectionSort(leftUnArray);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                int[] rightUnArray = splitArray(mainArray, rightArrayStart, rightArraySize);
                rightArray = selectionSort(rightUnArray);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            mergeSort(leftArray, rightArray, mainArray, leftArraySize, rightArraySize);

            System.out.println('\n' + "sorted list:" + '\n');
            printOutList(mainArray);

            duration = (System.currentTimeMillis() - startTime);
            System.out.println(duration + " ms)");
        } catch (InterruptedException e) {
        }
    }

    // Generating a random numbers and puts them in a given array.
    private void generateNumbers(int[] array, int boundary, Random randomNumbers) {
        for (int i = 0; i < array.length; i++) {
            int newNumber = randomNumbers.nextInt(boundary) + 1;
            array[i] = newNumber;
        }
    }

    // This method will copy a part of an array and return that copied part.
    private int[] splitArray(int[] array, int startOfArray, int sizeOfArray) {
        int[] PartialArray = new int[sizeOfArray];
        int index = 0;
        int sizeCheck = sizeOfArray;
        int startingIndex = 0;
        if (startOfArray != 0) {
            startingIndex = startOfArray - 1;
        }


        for (int i = startOfArray; i <= array.length; i++) {

            if (sizeCheck > 0) {
                PartialArray[index] = array[startingIndex];
                sizeCheck -= 1;
                index += 1;
                startingIndex += 1;
            } else {
                return PartialArray;
            }
        }

        return PartialArray;
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

    // Sorting two lists together.
    private int[] mergeSort(int[] leftList, int[] rightList, int[] mainArray, int leftSubArraySize, int rightSubArraySize) {

        int leftStart = 0, rightStart = 0, index = 0;

        while (leftStart < leftSubArraySize && rightStart < rightSubArraySize) {

            if (leftList[leftStart] <= rightList[rightStart]) {
                mainArray[index] = leftList[leftStart];
                leftStart++;
            } else {
                mainArray[index] = rightList[rightStart];
                rightStart++;
            }
            index++;
        }

        while (leftStart < leftSubArraySize) {
            mainArray[index] = leftList[leftStart];
            leftStart++;
            index++;
        }

        while (rightStart < rightSubArraySize) {
            mainArray[index] = rightList[rightStart];
            rightStart++;
            index++;
        }
        return mainArray;
    }

    // This is just to print and see is all the sorting was done correctly.
    private void printOutList(int[] array) {
        for (int element : array) {
            System.out.println(element);
        }
    }

}