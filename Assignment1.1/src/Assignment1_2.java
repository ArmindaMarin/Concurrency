import java.util.Random;

public class Assignment1_2 {
    private int boundry = 8000000;
    private int arrayLenght = 10;
    private int [] listOfNumber = new int[arrayLenght];
    private Random genNumers = new Random();

//    public static void main(String[] args) {
//        new Assignment1_1().runnable();
//    }

    public void runnable(){
        generateNumbers();
        System.out.println("unsorted list:" + '\n');
        printOutList();
        selectionSort(0,listOfNumber.length);
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
    // Sorting a list by using selection sort
    public int[] selectionSort(int startOfList, int listSize){
        int [] subArray = listOfNumber;

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

        return subArray;
    }
    // Sorting using merge sort
    public void mergeSort(){
//            /* Create temp arrays */
//            int L[] = new int [n1];
//            int R[] = new int [n2];
//
//            /*Copy data to temp arrays*/
//            for (int i=0; i<n1; ++i)
//                L[i] = arr[l + i];
//            for (int j=0; j<n2; ++j)
//                R[j] = arr[m + 1+ j];
//
//
//            /* Merge the temp arrays */
//
//            // Initial indexes of first and second subarrays
//            int i = 0, j = 0;
//
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
//        }
    }
    // This is just to print
    public void printOutList(){
        for (int element: listOfNumber){
            System.out.println(element);
        }
    }

    }
