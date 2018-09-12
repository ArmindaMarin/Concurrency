import java.util.Random;

public class Assignment1_2 {
    private int boundry = 8000000;
    private int arrayLenght = 10;
    private int [] listOfNumber = new int[arrayLenght];
    private Random genNumers = new Random();
    private long startTime = System.currentTimeMillis();
    private long duration;
    private int leftSubArrayStart = 0;
    private int leftSubArraySize = listOfNumber.length/2;
    private int rightSubArrayStart = listOfNumber.length/2 +1;
    private int rightSubArraySize = listOfNumber.length;

//    public static void main(String[] args) {
//        new Assignment1_1().runnable();
//    }

    public void runnable(){
        generateNumbers();

        System.out.println("unsorted list:" + '\n');
        printOutList();

        int[] leftUnArray = splitArray(listOfNumber,leftSubArrayStart,leftSubArraySize);
        int[] rightUnArray = splitArray(listOfNumber,rightSubArrayStart, rightSubArraySize);

        int[] leftArray = selectionSort(leftUnArray);
        int[] rightArray = selectionSort(rightUnArray);

        mergeSort(leftArray, rightArray);

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

    public int [] splitArray(int[] startingArray,int startOfList, int listSize){
        int[] subArray = new int[listSize];

        for (int i = startOfList; i < listSize; i++) {
            subArray[i] = startingArray[i];
        }

        return subArray;
    }
    // Sorting a list by using selection sort
    public int[] selectionSort(int[] unSortedArray){

        for (int i = 0; i < unSortedArray.length ; i++) {
            int smallestNumber = i;

            for (int j = i +1; j < unSortedArray.length; j++) {
                if(listOfNumber[j] < listOfNumber[smallestNumber]){
                    smallestNumber = j;
                }
            }
            int tempNumber = listOfNumber[smallestNumber];
            listOfNumber[smallestNumber] = listOfNumber[i];
            listOfNumber[i] = tempNumber;
        }

        return unSortedArray;
    }
    // Sorting using merge sort
    public void mergeSort(int[] leftList, int[] rightList){

        int leftStart = 0, rightStart = 0;

        

//            // Initial index of merged subarry array
//            int k = l;
//            while (i < n1 && j < n2)
//            {
//                if (L[i] <= R[j])
//                {
//                    arr[k] = L[i];
//                    i++;
//                }
//                else
//                {
//                    arr[k] = R[j];
//                    j++;
//                }
//                k++;
//            }
//
//            /* Copy remaining elements of L[] if any */
//            while (i < n1)
//            {
//                arr[k] = L[i];
//                i++;
//                k++;
//            }
//
//            /* Copy remaining elements of R[] if any */
//            while (j < n2)
//            {
//                arr[k] = R[j];
//                j++;
//                k++;
//            }
    }
    // This is just to print
    public void printOutList(){
        for (int element: listOfNumber){
            System.out.println(element);
        }
    }

    }
