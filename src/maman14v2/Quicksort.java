/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maman14v2;

/**
 * Quicksort class.
 *
 * @author Tal and Avi
 */
public class Quicksort {

    private final Integer[] numbers;
    private final int numSize;

    private int counter;

    /**
     * Quicksort constructor.
     *
     * @param array An array of numbers
     */
    public Quicksort(Integer[] array) {
        this.numSize = array.length;
        this.numbers = new Integer[this.numSize];
        System.arraycopy(array, 0, this.numbers, 0, this.numSize);
    }

    /**
     * Sort algorithm for kth elements. Created according to the algorithm in
     * page 111.
     *
     * @param k the number of elemets to sort
     * @return Integer array sorted
     */
    public Integer[] sort(int k) {
        this.counter = 0;
        this.randomizedSelect(0, this.numbers.length - 1, k);
        Integer[] result = new Integer[k];
        this.quicksort(0, k - 1);
        System.arraycopy(this.numbers, 0, result, 0, k);
        return result;
    }

    /**
     * Quicksort algorithm. Created according to the algorithm in page 122.
     *
     * @param start start index
     * @param end end index
     */
    private void quicksort(int start, int end) {
        int pivotIndex;
        if (start < end) {
            pivotIndex = this.partition(start, end);
            quicksort(start, pivotIndex - 1);
            quicksort(pivotIndex + 1, end);
        }

    }

    /**
     * Exchange algorithm.
     *
     * @param i index i to swap
     * @param j index j to swap
     */
    private void exchange(int i, int j) {
        int temp = this.numbers[i];
        this.numbers[i] = this.numbers[j];
        this.numbers[j] = temp;
    }

    /**
     * Partitioning algorithm Count the number of comparisons made. Created
     * according to the algorithm in page 122.
     *
     * @param start start index
     * @param end end index
     * @return index of the pivot after partitioning.
     */
    private int partition(int start, int end) {
        int pivot;
        int i;
        pivot = this.numbers[end];
        i = start - 1;
        for (int j = start; j < end; j++) {
            this.counter++;
            if (this.numbers[j] <= pivot) {
                i++;
                this.exchange(i, j);
            }
        }
        this.exchange(i + 1, end);
        return i + 1;

    }

    /**
     * Output the comparison counter.
     *
     * @return counter
     */
    public int counter() {
        return this.counter;
    }

    /**
     * Randomly partitioning the array. Created according to the algorithm in
     * page 129.
     *
     * @param start start index
     * @param end end index
     * @return index of the pivot after partitioning.
     */
    private int randomizedPartition(int start, int end) {
        int pivot = start + (int) (Math.random() * ((end - start) + 1));
        this.exchange(pivot, end);
        return this.partition(start, end);
    }

    /**
     * Select the kth element by randomly partitioning the array. Created
     * according to the algorithm in page 154 .
     *
     * @param start start index
     * @param end end index
     * @param k kth element
     * @return kth element index.
     */
    private Integer randomizedSelect(int start, int end, int k) {
        if (start == end) {
            return this.numbers[start];
        }
        int pivot = this.randomizedPartition(start, end);
        int i = pivot - start + 1;
        if (k == i) {
            return this.numbers[pivot];
        } else {
            if (k < i) {
                return this.randomizedSelect(start, pivot - 1, k);
            } else {
                return this.randomizedSelect(pivot + 1, end, k - i);
            }
        }
    }
}
