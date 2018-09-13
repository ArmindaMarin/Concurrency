import java.util.Random;

public class Assignment1_2 {
    private int boundry = 8000000;
    private int arrayLenght = 10;
    private int[] mainList = new int[arrayLenght];
    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;
    private int leftSubArrayStart = 0;
    private int leftSubArraySize = mainList.length / 2;
    private int rightSubArrayStart = leftSubArraySize + 1;
    private int rightSubArraySize = mainList.length - leftSubArraySize;

    public static void main(String[] args) {

        new Assignment1_2().run();

//        Thread t1 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        Thread t2 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//
//        t1.start();
//        t2.start();
        
//        try {
//            t1.join();
//            t2.join();
//        } catch (InterruptedException e) {}
//        System.out.println(c.getValue());
    }

    public void run() {
        generateNumbers(mainList,boundry,genNumers);

        System.out.println("unsorted list:" + '\n');
        printOutList(mainList);

        int[] leftUnArray = splitArray(mainList, leftSubArrayStart, leftSubArraySize);
        int[] rightUnArray = splitArray(mainList, rightSubArrayStart, rightSubArraySize);

        int[] leftArray = selectionSort(leftUnArray);
        int[] rightArray = selectionSort(rightUnArray);

        mergeSort(leftArray, rightArray);

        System.out.println('\n' + "sorted list:" + '\n');
        printOutList(mainList);

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }

    // Generating a list of numbers
    public void generateNumbers(int[] array, int boundry, Random genNumers) {

        for (int i = 0; i < array.length; i++) {
            int newNumber = genNumers.nextInt(boundry) + 1;
            array[i] = newNumber;
        }
    }

    public int[] splitArray(int[] startingArray, int startOfList, int listSize) {
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
    public int[] selectionSort(int[] unSortedArray) {

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
    public void mergeSort(int[] leftList, int[] rightList) {

        int leftStart = 0, rightStart = 0, index = 0;

        while (leftStart < leftSubArraySize && rightStart < rightSubArraySize) {

            if (leftList[leftStart] <= rightList[rightStart]) {
                mainList[index] = leftList[leftStart];
                leftStart += 1;
            } else {
                mainList[index] = rightList[rightStart];
                rightStart += 1;
            }
            index += 1;
        }

        while (leftStart < leftSubArraySize) {
            mainList[index] = leftList[leftStart];
            leftStart += 1;
            index += 1;
        }

        while (rightStart < rightSubArraySize) {
            mainList[index] = rightList[rightStart];
            rightStart += 1;
            index += 1;
        }
    }

    // This is just to print
    public void printOutList(int[] array) {
        for (int element : array) {
            System.out.println(element);
        }
    }

}