import java.util.Random;

public class Assignment1_3 {
    private int boundry = 100;
    private int arrayLenght = 10000;
    private int[] mainArray = new int[arrayLenght];
    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;
    private int leftSubArrayStart = 0;
    private int leftSubArraySize = mainArray.length / 2;
    private int rightSubArrayStart = leftSubArraySize + 1;
    private int rightSubArraySize = mainArray.length - leftSubArraySize;
    private int[] leftArray = new int[leftSubArraySize];
    private int[] rightArray = new int [rightSubArraySize];
    private int threshold = 500;

    public static void main(String[] args) {
        new Assignment1_3().run();
    }

    private void run() {

        generateNumbers(mainArray, boundry, genNumers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);

//        Thread thread1 = addThread(threshold, leftUnArray);
//        Thread thread2 = addThread(threshold, rightUnArray);
//
//        thread1.start();
//        thread2.start();
//
//        try {
//            thread1.join();
//            thread2.join();
//
//            mergeSort(leftArray, rightArray);
//
//            System.out.println('\n' + "sorted list:" + '\n');
//            printOutList(mainArray);
//
//            duration = (System.currentTimeMillis() - startTime);
//            System.out.println(duration + " ms)");
//        } catch (InterruptedException e) {
//        }

        checkAndSplitThread(threshold, mainArray);

        mergeSort(leftArray, rightArray, this.mainArray, leftArray.length, rightArray.length);

        System.out.println('\n' + "sorted list:" + '\n');
        printOutList(mainArray);

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }

    private class NewThread extends Thread {
        int[] sortedArray;
        int treshold;

        public NewThread(int[] sortedArray, int treshhold) {
            this.sortedArray = sortedArray;
            this.treshold = treshhold;
        }

        public void run() {
            selectionSort(sortedArray);
        }

        public int[] getSortedArray() {
            return sortedArray;
        }
    }

    private void checkAndSplitThread(int treshold, int[] unArray) {
        int[] rightSortedArray;
        int[] leftSortedArray;
        int subBoundry;

        while (treshold > unArray.length) {
            subBoundry = unArray.length / 2;
            leftSortedArray = splitArray(unArray, 0, subBoundry);
            rightSortedArray = splitArray(unArray, subBoundry + 1, unArray.length);
            leftSortedArray = addThread(treshold, leftSortedArray);
            rightSortedArray = addThread(treshold, rightSortedArray);

            this.leftArray = leftSortedArray;
            this.rightArray = rightSortedArray;
        }

    }

    private int[] addThread(int treshold, int[] unArray) {

        NewThread t1 = new NewThread(unArray, treshold);

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
        }

        unArray = t1.getSortedArray();

        return unArray;
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
    private void mergeSort(int[] leftList, int[] rightList, int[] mainArray, int leftSubArraySize, int rightSubArraySize) {

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
    }

    // This is just to print
    private void printOutList(int[] array) {
        for (int element : array) {
            System.out.println(element);
        }
    }

}