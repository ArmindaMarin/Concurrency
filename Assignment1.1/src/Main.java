import java.util.Random;

public class Main {
    private int boundry = 8000000;
    private int arraylenght = 10;
    private int [] listOfNumber = new int[arraylenght];
    private Random genNumers = new Random();

    public static void main(String[] args) {
        new Main().run();
    }

    public void run(){
        generateNumbers();
        System.out.println("unsorted list:" + '\n');
        printOutList();
        selectionSort();
        System.out.println('\n' + "sorted list:" + '\n');
        printOutList();
    }
// Generating a list of numbers
    public void generateNumbers(){

        for (int i = 0; i < listOfNumber.length; i++) {
            int newNumber = genNumers.nextInt(boundry) +1;
            listOfNumber[i] = newNumber;
        }
    }
// Sorting a list
    public void selectionSort(){

        int listSize = listOfNumber.length;

        for (int i = 0; i < listSize ; i++) {
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
