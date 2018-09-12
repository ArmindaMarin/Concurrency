import java.util.Random;

public class Assignment1_1 {
    private int boundry = 8000000;
    private int arrayLenght = 10;
    private int [] listOfNumber = new int[arrayLenght];
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
        selectionSort(0,listOfNumber.length);
        System.out.println('\n' + "sorted list:" + '\n');
        printOutList();

        duration = (System.currentTimeMillis() - startTime);
        System.out.println(duration + " ms)");
    }
// Generating a list of numbers
    public void generateNumbers(){

        for (int i = 0; i < listOfNumber.length; i++) {
            int newNumber = genNumers.nextInt(boundry) +1;
            listOfNumber[i] = newNumber;
        }
    }
// Sorting a list by using selection sort
    public void selectionSort(int startOfList, int listSize){

        for (int i = startOfList; i < listSize ; i++) {
            int smallestNumber = i;

            for (int j = i +1; j < listSize; j++) {
                if(listOfNumber[j] < listOfNumber[smallestNumber]){
                    smallestNumber = j;
                }
            }
            int tempNumber = listOfNumber[smallestNumber];
            listOfNumber[smallestNumber] = listOfNumber[i];
            listOfNumber[i] = tempNumber;
        }
    }
// This is just to print
    public void printOutList(){
        for (int element: listOfNumber){
            System.out.println(element);
        }
    }
}
