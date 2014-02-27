package maman14v2;

/**
 * Heap Class.
 *
 * @author Tal and Avi
 */
public class MinHeap {

    private final Integer[] heap;
    private int heapSize;
    private int counter;

    /**
     * MinHeap constructor. Build a legit heap out of an array of Integer.
     * Created according to the algorithm in page 111.
     *
     * @param array contains the values for which the heap will be built from.
     */
    public MinHeap(Integer[] array) {
        this.heapSize = array.length;
        this.heap = new Integer[this.heapSize];
        this.counter = 0;
        System.arraycopy(array, 0, this.heap, 0, this.heapSize);
        for (int i = (int) (Math.floor(array.length / 2) - 1); i >= 0; i--) {
            this.minHeapify(i);
        }
    }

    /**
     * Find the left child of a node.
     *
     * @param node the node index.
     * @return the child index.
     */
    private int left(int node) {
        node = (2 * node) + 1;
        return node;
    }

    /**
     * Find the right child of a node.
     *
     * @param node the node index.
     * @return the child index.
     */
    private int right(int node) {
        node = (2 * node) + 2;
        return node;
    }

    /**
     * Exchange algorithm.
     *
     * @param i index i to swap
     * @param j index j to swap
     */
    private void exchange(int i, int j) {
        int temp = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = temp;
    }

    /**
     * Heapify the sub-heap under node. Created according to the algorithm in
     * page 109.
     *
     * @param node the root of the sub-tree.
     */
    private void minHeapify(int node) {
        int leftChild;
        int rightChild;
        int smallest = node;
        leftChild = this.left(node);
        rightChild = this.right(node);

        if (leftChild < this.heapSize) {
            this.counter++;
            if (this.heap[leftChild] < this.heap[node]) {
                smallest = leftChild;
            }
        }
        if (rightChild < this.heapSize) {
            this.counter++;
            if (this.heap[rightChild] < this.heap[smallest]) {
                smallest = rightChild;
            }
        }
        if (smallest != node) {
            exchange(node, smallest);
            this.minHeapify(smallest);

        }

    }

    /**
     * Extract the root of the heap - the minimum. Created according to the
     * algorithm in page 116.
     *
     * @return the root of the heap.
     */
    private Integer extractMin() {
        Integer result;
        if (this.heapSize > 0) {
            result = this.heap[0];
            this.exchange(0, this.heapSize - 1);
            this.heapSize = this.heapSize - 1;
            this.minHeapify(0);
        } else {
            result = null;
        }

        return result;

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
     * Output a sorted array up to kth index. Use heapsort by extracting min k
     * times.
     *
     * @param k The index to sort up to
     * @return Array of Integer sorted.
     */
    public Integer[] heapSort(int k) {
        Integer[] result = new Integer[k];
        for (int i = 0; i < k; i++) {
            result[i] = this.extractMin();
        }
        return result;
    }

}
