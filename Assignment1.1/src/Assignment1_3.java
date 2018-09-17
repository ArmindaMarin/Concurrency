import java.util.Random;

public class Assignment1_3 {
    public static void main(String[] args) {
        new Assignment1_3().run();
    }

    private void run() {
        Random randomNumbers = new Random();
        long startTime = System.currentTimeMillis();
        long duration;

        int boundary = 1000;
        int arrayLength = 100;
        int threshold = 500;

        int[] mainArray = new int[arrayLength];

        int leftArraySize = mainArray.length / 2;
        int rightArraySize = mainArray.length - leftArraySize;

        int leftArrayStart = 0;
        int rightArrayStart = mainArray.length / 2 + 1;

        int[] leftArray;
        int[] rightArray;


        generateNumbers(mainArray, boundary, randomNumbers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);

        leftArray = splitArray(mainArray, leftArrayStart, leftArraySize);
        rightArray = splitArray(mainArray, rightArrayStart, rightArraySize);

        leftArray = createNewThread(threshold, leftArray);
        rightArray = createNewThread(threshold, rightArray);

        mergeSort(leftArray, rightArray, mainArray, leftArray.length, rightArray.length);

        System.out.println('\n' + "sorted list:" + '\n');
        printOutList(mainArray);

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }

    // This inner class in where the arrays get sorted and divided in to more threads if needed.
    private class NewThread extends Thread {
        int[] sortedArray;
        int[] leftPartialArray;
        int[] rightPartialArray;

        int threshold;

        // The constructor gets the unsorted array and split it in two, that in case more threads are needed.
        private NewThread(int threshold, int[] sortedArray) {
            this.sortedArray = sortedArray;
            this.threshold = threshold;
            this.leftPartialArray = splitArray(sortedArray, 0, sortedArray.length / 2);
            this.rightPartialArray = splitArray(sortedArray, sortedArray.length / 2 + 1, sortedArray.length / 2);
        }

        public synchronized void run() {
            addMoreThreads(threshold, sortedArray);
            leftPartialArray = selectionSort(leftPartialArray);
            rightPartialArray = selectionSort(rightPartialArray);
            sortedArray = mergeSort(leftPartialArray, rightPartialArray, sortedArray, leftPartialArray.length, rightPartialArray.length);
        }

        // This method checks if the length of provided array is longer then threshold then it creates two more threads.
        private synchronized void addMoreThreads(int threshold, int[] array) {
            if (array.length > threshold) {
                this.leftPartialArray = createNewThread(threshold, leftPartialArray);
                this.rightPartialArray = createNewThread(threshold, rightPartialArray);
            }
        }

        private int[] getSortedArray() {
            return sortedArray;
        }
    }

    // This method is just for creating a new thread and returning a sorted array.
    private synchronized int[] createNewThread(int threshold, int[] unArray) {
        NewThread t1 = new NewThread(threshold, unArray);
        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
        }

        unArray = t1.getSortedArray();
        return unArray;
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