import java.util.Random;

public class Assignment1_3 {
    private int boundry = 100;
    private int arrayLenght = 10;
    private int[] mainArray = new int[arrayLenght];
    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;
    private int leftSubArrayStart = 0;
    private int leftSubArraySize = mainArray.length / 2;
    private int rightSubArrayStart = leftSubArraySize + 1;
    private int rightSubArraySize = mainArray.length - leftSubArraySize;
    private int[] leftArray;
    private int[] rightArray;
    private int threshold = 2;

    public static void main(String[] args) {
        new Assignment1_3().run();
    }

    private void run() {

        generateNumbers(mainArray, boundry, genNumers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);

        int[] leftUnArray = splitArray(mainArray, leftSubArrayStart, leftSubArraySize);
        int[] rightUnArray = splitArray(mainArray, rightSubArrayStart, rightSubArraySize);

        Thread t1 = addThread(threshold, leftUnArray);
        Thread t2 = addThread(threshold, rightUnArray);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();

            mergeSort(leftArray, rightArray);

            System.out.println('\n' + "sorted list:" + '\n');
            printOutList(mainArray);

            duration = (System.currentTimeMillis() - startTime);
            System.out.println(duration + " ms)");
        } catch (InterruptedException e) {
        }
    }

    private Thread addThread(int treshold, int[] unArray) {

        int[] sortedArray = unArray;
        int subBoundry = sortedArray.length/2;
        int[]leftSortedArray = splitArray(sortedArray,0,subBoundry);
        int[] rightSortedArray = splitArray(sortedArray,subBoundry + 1,sortedArray.length);

        if (unArray.length > treshold) {
            addThread(treshold, sortedArray);
        } else {
            Thread t1 = new Thread(new Runnable() {

                @Override
                public void run() {
                    selectionSort(sortedArray);
                }
            });

            t1.start();
            try {
                t1.join();

                mergeSort(leftSortedArray, rightSortedArray);
            } catch (InterruptedException e) { }
            return t1;
        }
        return null;
    }

    // Generating a list of numbers
    private void generateNumbers(int[] array, int boundry, Random genNumers) {

        for (int i = 0; i < array.length; i++) {
            int newNumber = genNumers.nextInt(boundry) + 1;
            array[i] = newNumber;
        }
    }

    private int[] splitArray(int[] startingArray, int startOfList, int listSize) {
        int[] subArray = new int[listSize];
        int subIndex = 0;
        int sizeCheck = listSize;
        int startingIndex = 0;
        if (startOfList != 0) {
            startingIndex = startOfList - 1;
        }


        for (int i = startOfList; i <= startingArray.length; i++) {

            if (sizeCheck > 0) {
                subArray[subIndex] = startingArray[startingIndex];
                sizeCheck -= 1;
                subIndex += 1;
                startingIndex += 1;
            } else {
                return subArray;
            }
        }

        return subArray;
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

    // Sorting using merge sort
    private void mergeSort(int[] leftList, int[] rightList) {

        int leftStart = 0, rightStart = 0, index = 0;

        while (leftStart < leftSubArraySize && rightStart < rightSubArraySize) {

            if (leftList[leftStart] <= rightList[rightStart]) {
                mainArray[index] = leftList[leftStart];
                leftStart += 1;
            } else {
                mainArray[index] = rightList[rightStart];
                rightStart += 1;
            }
            index += 1;
        }

        while (leftStart < leftSubArraySize) {
            mainArray[index] = leftList[leftStart];
            leftStart += 1;
            index += 1;
        }

        while (rightStart < rightSubArraySize) {
            mainArray[index] = rightList[rightStart];
            rightStart += 1;
            index += 1;
        }
    }

    // This is just to print
    private void printOutList(int[] array) {
        for (int element : array) {
            System.out.println(element);
        }
    }

}