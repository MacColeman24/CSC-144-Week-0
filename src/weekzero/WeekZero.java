package weekzero;

/* 
 * Week 0 Programming Project
 * CSC144 Object-Oriented Programming
 * Mac Coleman
 * November 19, 2021
 * I mostly worked on this by myself, but I tried to talk with Brodie when I could.
 * I added more algorithms so that I could get more things done.
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Collections;

/**
 *
 * @author Mac Coleman
 */
public class WeekZero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> test = generateTestList(1000);
        System.out.println("Randomized List of 1000 elements:");
        prettyPrint(test, 10, 3);
        
        System.out.println("Minimum: " + minimum(test));
        System.out.println("Index of Minimum: " + minimumIndex(test));
        System.out.println("Index of Minimum after 500: " + minimumIndexAfter(test, 500));
        
        System.out.println("Element at index 250: " + test.get(250));
        System.out.println("Element at index 750: " + test.get(750));
        System.out.println("Swapping elements...");
        swap(test, 250, 750);
        System.out.println("Element at index 250: " + test.get(250));
        System.out.println("Element at index 750: " + test.get(750));
        
        System.out.println("Sorting by selection sort with custom functions:");
        jankySelectionSort(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Shuffled:");
        shuffleList(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Partially sorting the first two digits");
        partialSort(test, 1);
        prettyPrint(test, 10, 3);
        
        System.out.println("Sorting by selection sort:");
        selectionSort(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Shuffled:");
        shuffleList(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Sorting by insertion sort:");
        selectionSort(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Shuffled:");
        shuffleList(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Sorted with merge sort:");
        test = mergeSort(test);
        prettyPrint(test, 10, 3);
        
        System.out.println("Index of 197 with sequential search: " + sequentialSearch(test, 197));
        System.out.println("\t(-1 is returned if the element is not found)");
        
        System.out.println("Index of 197 with binary search: " + binarySearch(test, 197));
        System.out.println("\t(-1 is returned if the element is not found)");
        
        List<Integer> A = generatePrimes(25);
        List<Integer> B = generatePrimes(64);
        System.out.println("Sorted list A: " + A);
        System.out.println("Sorted list B: " + B);
        System.out.println("Merged combination of A and B: " + merge(A, B));
       
        
    }

    //Problem 1
    public static int minimum(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        for (int n : list) {
            if (n < min) {
                min = n;
            }
        }
        return min;
    }

    //Problem 2
    public static int minimumIndex(List<Integer> list) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
                minIndex = i;
            }
        }

        return minIndex;

    }

    //Problem 3
    public static int minimumIndexAfter(List<Integer> list, int after) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = after; i < list.size(); i++) {
            if (list.get(i) < min) {
                min = list.get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }

    //Problem 4
    public static void swap(List<Integer> list, int indexA, int indexB) {
        int temp = list.get(indexB);
        list.set(indexB, list.get(indexA));
        list.set(indexA, temp);
    }

    //Problem 5
    public static void jankySelectionSort(List<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            int minUnsorted = minimumIndexAfter(list, i);
            swap(list, minUnsorted, i);
        }
    }

    //Problem 6
    public static void partialSort(List<Integer> list, int sorted) {
        int sortNext = sorted + 1;
        while (sortNext > 0 && list.get(sortNext) < list.get(sortNext - 1)) {
            swap(list, sortNext, sortNext - 1);
            sortNext--;
        }
    }
    
    //Problem 7
    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> sorted = new ArrayList<>();
        int l = 0;
        int r = 0;
        while (l < left.size() && r < right.size()) {
            if(left.get(l) < right.get(r)) {
                sorted.add(left.get(l));
                l++;
            } else {
                sorted.add(right.get(r));
                r++;
            }
        }
        
        while (l < left.size()) {
            sorted.add(left.get(l));
            l++;
        }
        while (r < right.size()) {
            sorted.add(right.get(r));
            r++;
        }
        
        return sorted;
    }

    public static int sequentialSearch(List<Integer> list, int value) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == value) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(List<Integer> list, int value) {
        int low = 0;
        int high = list.size();
        int middle = low + (high - low) / 2;

        while (middle != low && middle != high) {
            if (list.get(middle) == value) {
                return middle;
            } else if (list.get(middle) > value) {
                high = middle;
            } else if (list.get(middle) < value) {
                low = middle;
            }
            middle = low + (high - low) / 2;
        }

        return -1;
    }

    public static void selectionSort(List<Integer> list) {
        //Iterate over array and place minimum elements of unsorted part at the beginning of the array.
        //Should probably be O(n^2)?
        for (int i = 0; i < list.size(); i++) {
            int minIndex = -1;
            int min = Integer.MAX_VALUE;

            for (int j = i; j < list.size(); j++) {
                if (list.get(j) < min) {
                    minIndex = j;
                    min = list.get(j);
                }
            }
            Collections.swap(list, i, minIndex);
        }
    }

    public static void insertionSort(List<Integer> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            int offset = i + 1;
            while (offset > 0 && list.get(offset) < list.get(offset - 1)) {
                Collections.swap(list, offset, offset - 1);
                offset--;
            }
        }
    }

    public static List<Integer> mergeSort(List<Integer> list) {
        if (list.size() < 2) {
            return list;
        } else {
            List<Integer> left = list.subList(0, list.size() / 2);
            List<Integer> right = list.subList(list.size() / 2, list.size());
            List<Integer> Left = mergeSort(left);
            List<Integer> Right = mergeSort(right);
            return merge(Left, Right);
        }
    }

    public static void shuffleList(List<Integer> list) {
        //This is probably not the best way to do this. It seems like each
        Random r = new Random();
        for (int i = 0; i < list.size(); i++) {
            Collections.swap(list, i, i + r.nextInt(list.size() - i));
        }
    }

    public static List<Integer> generatePrimes(int n) {
        //Sieve of Eratosthenes
        List<Integer> primes = new ArrayList<>();
        boolean[] possiblePrimes = new boolean[n - 1];
        Arrays.fill(possiblePrimes, true);

        for (int i = 0; i < possiblePrimes.length; i++) {
            if (possiblePrimes[i]) {
                //This number is prime
                int prime = i + 2;
                int multiplier = 2;
                int multiple = prime * multiplier;
                primes.add(prime);
                while (multiple - 2 < possiblePrimes.length) {
                    possiblePrimes[multiple - 2] = false;
                    multiplier++;
                    multiple = prime * multiplier;
                }
            }

        }
        return primes;
    }

    public static List<Integer> generateTestList(int n) {
        /* 
         * In all of your programs, I see that you usually pass an instance of
         * the Random class in through the function arguments. Is it bad to
         * instantiate one inside of a function like this? I imagine making all
         * these new objects that only live for the duration of the function
         * might be annoying for the garbage collector.
         */
        Random rng = new Random();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            list.add(rng.nextInt(1000));
        }

        return list;
    }

    public static void prettyPrint(List<Integer> list, int columns, int pad) {
        System.out.println("[");
        System.out.print("\t");
        for (int i = 0; i < list.size(); i++) {
            String element = list.get(i).toString();

            int spaceToAdd = pad - element.length();
            String result = "";

            for (int j = 0; j < spaceToAdd; j++) {
                result = result.concat(" ");
            }
            result = result.concat(element);

            if (i != list.size() - 1) {
                System.out.print(result + ", ");
            } else {
                System.out.print(result);
            }

            if ((i + 1) % columns == 0 && i + 1 != list.size()) {
                System.out.println();
                System.out.print("\t");
            }
        }
        System.out.println("\n]");

    }
}
