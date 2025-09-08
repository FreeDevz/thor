package io.awijaya.lab;

import java.util.*;

// Claude Prompt: what is the most efficient sorting algorithm in computer science?  Give a code example in Java.
public class EfficientSortingAlgorithms {

    /**
     * QUICKSORT - Most efficient for average cases
     * Time: O(n log n) average, O(n²) worst case
     * Space: O(log n) due to recursion
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static int partition(int[] arr, int low, int high) {
        // Choose rightmost element as pivot
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        // Place pivot in correct position
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static int partitionOptimized(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    /**
     * OPTIMIZED QUICKSORT with median-of-three pivot selection
     * Reduces worst-case probability and improves performance
     */
    public static void optimizedQuickSort(int[] arr, int low, int high) {
        if (low < high) {
            // Use insertion sort for small arrays (cutoff around 10-15 elements)
            if (high - low < 10) {
                insertionSort(arr, low, high);
                return;
            }

            // Choose median-of-three as pivot
            medianOfThree(arr, low, high);
            int pivotIndex = partitionOptimized(arr, low, high);

            optimizedQuickSort(arr, low, pivotIndex - 1);
            optimizedQuickSort(arr, pivotIndex + 1, high);
        }
    }

    private static void medianOfThree(int[] arr, int low, int high) {
        int mid = low + (high - low) / 2;

        // Sort low, mid, high
        if (arr[mid] < arr[low]) swap(arr, low, mid);
        if (arr[high] < arr[low]) swap(arr, low, high);
        if (arr[high] < arr[mid]) swap(arr, mid, high);

        // Place median at the end
        swap(arr, mid, high);
    }

    /**
     * MERGE SORT - Guaranteed O(n log n) performance
     * Time: O(n log n) in all cases
     * Space: O(n)
     */
    public static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;

            // Sort first and second halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        // Create temporary arrays for the two halves
        int[] leftArr = new int[mid - left + 1];
        int[] rightArr = new int[right - mid];

        // Copy data to temporary arrays
        System.arraycopy(arr, left, leftArr, 0, leftArr.length);
        System.arraycopy(arr, mid + 1, rightArr, 0, rightArr.length);

        // Merge the temporary arrays back
        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy remaining elements
        while (i < leftArr.length) arr[k++] = leftArr[i++];
        while (j < rightArr.length) arr[k++] = rightArr[j++];
    }

    /**
     * HEAP SORT - Consistent O(n log n) performance
     * Time: O(n log n) in all cases
     * Space: O(1)
     */
    public static void heapSort(int[] arr) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Extract elements from heap one by one
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end
            swap(arr, 0, i);

            // Call heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child is larger than root
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root
        if (largest != i) {
            swap(arr, i, largest);
            // Recursively heapify the affected sub-tree
            heapify(arr, n, largest);
        }
    }

    /**
     * INSERTION SORT - Efficient for small arrays
     * Used as optimization in hybrid algorithms
     */
    private static void insertionSort(int[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= low && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    /**
     * INTROSORT - Hybrid algorithm used by many standard libraries
     * Combines quicksort, heapsort, and insertion sort
     */
    public static void introSort(int[] arr) {
        int depthLimit = (int) (2 * Math.floor(Math.log(arr.length) / Math.log(2)));
        introSortRecursive(arr, 0, arr.length - 1, depthLimit);
    }

    private static void introSortRecursive(int[] arr, int low, int high, int depthLimit) {
        if (high - low < 16) {
            // Use insertion sort for small arrays
            insertionSort(arr, low, high);
        } else if (depthLimit == 0) {
            // Use heapsort when recursion depth limit is reached
            heapSortRange(arr, low, high);
        } else {
            // Use quicksort
            medianOfThree(arr, low, high);
            int pivot = partitionOptimized(arr, low, high);
            introSortRecursive(arr, low, pivot - 1, depthLimit - 1);
            introSortRecursive(arr, pivot + 1, high, depthLimit - 1);
        }
    }

    private static void heapSortRange(int[] arr, int low, int high) {
        // Implementation of heapsort for a specific range
        // This is a simplified version - full implementation would be more complex
        int[] temp = Arrays.copyOfRange(arr, low, high + 1);
        heapSort(temp);
        System.arraycopy(temp, 0, arr, low, temp.length);
    }

    // Utility method
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // Performance testing method
    public static void performanceTest() {
        int[] sizes = {1000, 10000, 100000, 1000000};

        System.out.println("Performance Comparison:");
        System.out.println("Size\t\tQuickSort\tMergeSort\tHeapSort\tIntroSort\tArrays.sort()");
        System.out.println("-".repeat(80));

        for (int size : sizes) {
            int[] original = generateRandomArray(size);

            // Test each algorithm
            long quickTime = timeSort("QuickSort", original.clone(),
                    arr -> optimizedQuickSort(arr, 0, arr.length - 1));

            long mergeTime = timeSort("MergeSort", original.clone(),
                    arr -> mergeSort(arr, 0, arr.length - 1));

            long heapTime = timeSort("HeapSort", original.clone(),
                    arr -> heapSort(arr));

            long introTime = timeSort("IntroSort", original.clone(),
                    arr -> introSort(arr));

            long arraysTime = timeSort("Arrays.sort", original.clone(),
                    arr -> Arrays.sort(arr));

            System.out.printf("%d\t\t%dms\t\t%dms\t\t%dms\t\t%dms\t\t%dms%n",
                    size, quickTime, mergeTime, heapTime, introTime, arraysTime);
        }
    }

    private static long timeSort(String name, int[] arr, SortingFunction sortFunc) {
        long start = System.currentTimeMillis();
        sortFunc.sort(arr);
        long end = System.currentTimeMillis();
        return end - start;
    }

    @FunctionalInterface
    interface SortingFunction {
        void sort(int[] arr);
    }

    private static int[] generateRandomArray(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt(size * 10);
        }
        return arr;
    }

    // Main method for testing
    public static void main(String[] args) {
        // Test with sample data
        int[] testArray = {64, 34, 25, 12, 22, 11, 90, 88, 76, 50, 42};

        System.out.println("Original array: " + Arrays.toString(testArray));

        // Test different sorting algorithms
        int[] quickSortArray = testArray.clone();
        optimizedQuickSort(quickSortArray, 0, quickSortArray.length - 1);
        System.out.println("QuickSort result: " + Arrays.toString(quickSortArray));

        int[] mergeSortArray = testArray.clone();
        mergeSort(mergeSortArray, 0, mergeSortArray.length - 1);
        System.out.println("MergeSort result: " + Arrays.toString(mergeSortArray));

        int[] heapSortArray = testArray.clone();
        heapSort(heapSortArray);
        System.out.println("HeapSort result: " + Arrays.toString(heapSortArray));

        int[] introSortArray = testArray.clone();
        introSort(introSortArray);
        System.out.println("IntroSort result: " + Arrays.toString(introSortArray));

        System.out.println("\n" + "=".repeat(50));

        // Run performance comparison
        performanceTest();
    }
}