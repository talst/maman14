package maman14v2;

import java.io.IOException;
import java.util.Scanner;

/**
 * Main class.
 *
 * @author Tal and Avi
 */
public class Maman14v2 {

    private static final int A = 100;
    private static final int B = 200;
    private static final int C = 500;
    private static final int D = 1000;

    /**
     * Start the program.
     *
     * @throws IOException
     */
    private static void start() throws IOException {
        String op;
        welcome();
        op = choose();
        if (op.compareTo("a") == 0) {
            runProgram();
        }
        if (op.compareTo("b") == 0) {
            preConfiguredTest();
        }
    }

    /**
     * Welcome text.
     */
    private static void welcome() {
        System.out.println("Data Structure, Winter 2014 \n \n"
                + "This is a \"mini-research\" project. \n"
                + "We will compare two solutions to the \n"
                + "\"sorting k smallest elements out of n\" problem. \n \n");
    }

    /**
     * Choose between running the program or the test procedure.
     *
     * @return a for the program, b for the test.
     */
    private static String choose() {
        System.out.println("Please choose: \n"
                + "a - Run the program. \n"
                + "b - Run the test procedure (A, B, C and D arrays).");
        Scanner in = new Scanner(System.in);
        return in.next();
    }

    /**
     * The program run.
     */
    private static void runProgram() {
        Integer[] array;
        int n;
        int k;
        String op;
        Scanner in = new Scanner(System.in);

        System.out.println("Please enter n and k (space between):");
        //get user input for n, k.
        n = in.nextInt();
        k = in.nextInt();

        System.out.println("Please choose: \n"
                + "a - for random numbers between 0 and 999 \n"
                + "b - to give your own numbers:  \n");
        //get user input a or b.
        op = in.next();
        array = createArray(n, op);
        runSorts(array, k);
    }

    /**
     * Create an array
     *
     * @param n  length of the array.
     * @param op a for random, b for manual.
     * @return The created array.
     */
    private static Integer[] createArray(int n, String op) {
        Integer[] array;
        array = new Integer[n];

        if (op.compareTo("b") == 0) {
            array = createManualArray(array.length);
        }

        if (op.compareTo("a") == 0) {
            array = createRandomArray(array.length);
        }

        return array;
    }

    /**
     * Create a manual array.
     *
     * @param length length of the array.
     * @return the created array.
     */
    private static Integer[] createManualArray(int length) {
        Integer[] array;
        array = new Integer[length];
        Scanner num = new Scanner(System.in);
        System.out.println("Please enter " + length + " numbers, delimited by spaces: \n");
        for (int m = 0; m < array.length; m++) {
            array[m] = num.nextInt();
        }
        return array;
    }

    /**
     * Create a random array.
     *
     * @param length length of the array.
     * @return The created array.
     */
    private static Integer[] createRandomArray(int length) {
        Integer[] array;
        array = new Integer[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }

    /**
     * Run heapsort and randomized-select + quicksort.
     *
     * @param array the array to sort.
     * @param k     the number of the elements to sort.
     */
    private static void runSorts(Integer[] array, int k) {
        int i;
        MinHeap heap = new MinHeap(array);   // create legit MinHeap class named
        Quicksort sorter = new Quicksort(array); // create legit Quicksort class
        Integer[] quickResult;
        heap.heapSort(k);
        quickResult = sorter.sort(k);
        System.out.println("\nThe " + k + " smallest numbers, in sorted order are: ");
        for (i = 0; i < k - 1; i++) {
            System.out.print(quickResult[i].intValue() + ", ");
        }
        System.out.println(quickResult[k - 1].intValue() + ".");
        System.out.println("Number of comparisons for algorithm 1 (heap): " + heap.counter());
        System.out.println("Number of comparisons for algorithm 2 (select): " + sorter.counter());
    }

    /**
     * Run the pre-configured test for arrays a[100], b[200], c[500] and
     * d[1000].
     *
     * @throws IOException
     */
    private static void preConfiguredTest() throws IOException {
        runTest(A);
        runTest(B);
        runTest(C);
        runTest(D);

    }

    /**
     * Start the test for a specific array. Run for k= 3, 50 and 100.
     *
     * @param n length of the array.
     * @throws IOException
     */
    private static void runTest(int n) throws IOException {
        Integer[] array;
        System.out.println("Running test for Size: " + n);
        array = createRandomArray(n);
        runSorts(array, 3);
        runSorts(array, 50);
        runSorts(array, 100);
        waitForEnter(1);
    }

    /**
     * Wait for enter.
     *
     * @param n 0 for end of the program, 1 for a continue.
     * @throws IOException
     */
    public static void waitForEnter(int n) throws IOException {
        if (n == 1) {
            System.out.println("Press enter to continue.");
        }
        if (n == 0) {
            System.out.println("Press enter to exit.");
        }
        System.in.read();
    }

    /**
     * main.
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        start();
        waitForEnter(0);

    }

}
