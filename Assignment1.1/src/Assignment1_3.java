import java.util.Random;

public class Assignment1_3 {
    private int boundry = 100;
    private int arrayLenght = 10000;
    private int threshold = 500;

    private int[] mainArray = new int[arrayLenght];

    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;

    private int leftSubArraySize = mainArray.length / 2;
    private int rightSubArraySize = mainArray.length - leftSubArraySize;

    private int leftSubArrayStart = 0;
    private int rightSubArrayStart = mainArray.length / 2 + 1;

    private int[] leftArray = splitArray(mainArray,leftSubArrayStart,leftSubArraySize);
    private int[] rightArray = splitArray(mainArray,rightSubArrayStart,rightSubArraySize);

    public static void main(String[] args) {
        new Assignment1_3().run();
    }

    private void run() {

        generateNumbers(mainArray, boundry, genNumers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainArray);

        doSomeThing(threshold,leftArray, leftSubArrayStart,leftSubArraySize);

        mergeSort(leftArray, rightArray, this.mainArray, leftArray.length, rightArray.length);

        System.out.println('\n' + "sorted list:" + '\n');
        printOutList(mainArray);

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }

    private class NewThread extends Thread {
        int[] sortedArray;
        int treshold;
        int startOfList;
        int listSize;

        public NewThread(int[] sortedArray, int treshhold, int startOfList, int listSize) {
            this.sortedArray = sortedArray;
            this.treshold = treshhold;
        }

        public void run() {
            sortedArray = selectionSort(splitArray(sortedArray,startOfList, listSize));
        }

        public int[] getSortedArray() {
            return sortedArray;
        }
    }

    private void doSomeThing(int threshold, int[] array,int arrayStart,int arraySize){

        int[] newArray = array;

        while(threshold < newArray.length){
            addThread(threshold,array, arrayStart,arraySize);
        }
    }

    private int[] addThread(int treshold, int[] unArray, int startOfList, int listSize) {

        NewThread t1 = new NewThread(unArray, treshold, startOfList, listSize);

        t1.start();

        try {
            t1.join();
        } catch (InterruptedException e) { }

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