import java.util.Random;

public class Assignment1_1 {
    private int boundry = 8000000;
    private int arrayLenght = 10;
    private int [] mainArray = new int[arrayLenght];
    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;

    public static void main(String[] args) {
        new Assignment1_1().run();
    }

    private void run(){
        generateNumbers();
        System.out.println("unsorted list:" + '\n');
        printOutList();
        selectionSort(mainArray);
        System.out.println('\n' + "sorted list:" + '\n');
        printOutList();

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }
// Generating a list of numbers
    public void generateNumbers(){

        for (int i = 0; i < mainArray.length; i++) {
            int newNumber = genNumers.nextInt(boundry) +1;
            mainArray[i] = newNumber;
        }
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
// This is just to print
    public void printOutList(){
        for (int element: mainArray){
            System.out.println(element);
        }
    }
}
