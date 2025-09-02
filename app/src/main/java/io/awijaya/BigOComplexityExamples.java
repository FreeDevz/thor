package io.awijaya;

import java.util.*;

/**
 * Big O Complexity Examples in Java
 *
 * This class demonstrates various time complexities with practical code examples. Each method is
 * annotated with its time complexity and explanation.
 */
public class BigOComplexityExamples {

    // ========================================
    // O(1) - CONSTANT TIME
    // ========================================

    /**
     * O(1) - Constant Time Operations that take the same amount of time regardless of input size
     */
    public static class ConstantTime {

        // Array access by index
        public static int getFirstElement(int[] array) {
            if (array.length == 0)
                return -1;
            return array[0]; // Always takes same time regardless of array size
        }

        // HashMap get/put operations
        public static String getValueFromMap(HashMap<String, String> map, String key) {
            return map.get(key); // Hash table lookup is O(1) average case
        }

        // Mathematical operations
        public static int addNumbers(int a, int b) {
            return a + b; // Simple arithmetic is always O(1)
        }

        // Stack push/pop operations
        public static void stackOperations(Stack<Integer> stack) {
            stack.push(42); // O(1)
            stack.pop(); // O(1)
            stack.peek(); // O(1)
        }
    }

    // ========================================
    // O(log n) - LOGARITHMIC TIME
    // ========================================

    /**
     * O(log n) - Logarithmic Time Operations that reduce the problem size by half each step
     */
    public static class LogarithmicTime {

        // Binary Search - Classic O(log n) algorithm
        public static int binarySearch(int[] sortedArray, int target) {
            int left = 0;
            int right = sortedArray.length - 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (sortedArray[mid] == target) {
                    return mid;
                } else if (sortedArray[mid] < target) {
                    left = mid + 1; // Eliminate left half
                } else {
                    right = mid - 1; // Eliminate right half
                }
            }
            return -1; // Not found
        }

        // Binary Search Tree lookup (balanced tree)
        public static TreeNode searchBST(TreeNode root, int target) {
            if (root == null || root.val == target) {
                return root;
            }

            if (target < root.val) {
                return searchBST(root.left, target); // Go left
            } else {
                return searchBST(root.right, target); // Go right
            }
        }

        // Finding height of binary tree
        public static int getHeight(TreeNode root) {
            if (root == null)
                return 0;
            return 1 + Math.max(getHeight(root.left), getHeight(root.right));
        }
    }

    // ========================================
    // O(n) - LINEAR TIME
    // ========================================

    /**
     * O(n) - Linear Time Operations that visit each element exactly once
     */
    public static class LinearTime {

        // Simple array traversal
        public static int findMax(int[] array) {
            if (array.length == 0)
                return Integer.MIN_VALUE;

            int max = array[0];
            for (int i = 1; i < array.length; i++) { // Visit each element once
                if (array[i] > max) {
                    max = array[i];
                }
            }
            return max;
        }

        // Linear search
        public static int linearSearch(int[] array, int target) {
            for (int i = 0; i < array.length; i++) { // May need to check all elements
                if (array[i] == target) {
                    return i;
                }
            }
            return -1;
        }

        // Counting occurrences
        public static int countOccurrences(String text, char target) {
            int count = 0;
            for (char c : text.toCharArray()) { // Visit each character once
                if (c == target) {
                    count++;
                }
            }
            return count;
        }

        // Sum of array elements
        public static long sumArray(int[] array) {
            long sum = 0;
            for (int num : array) { // Visit each element exactly once
                sum += num;
            }
            return sum;
        }
    }

    // ========================================
    // O(n log n) - LINEARITHMIC TIME
    // ========================================

    /**
     * O(n log n) - Linearithmic Time Efficient sorting algorithms and divide-and-conquer approaches
     */
    public static class LinearithmicTime {

        // Merge Sort - Classic O(n log n) sorting algorithm
        public static void mergeSort(int[] array) {
            if (array.length <= 1)
                return;

            int mid = array.length / 2;
            int[] left = Arrays.copyOfRange(array, 0, mid);
            int[] right = Arrays.copyOfRange(array, mid, array.length);

            mergeSort(left); // Divide: log n levels
            mergeSort(right); // Divide: log n levels
            merge(array, left, right); // Conquer: O(n) at each level
        }

        private static void merge(int[] result, int[] left, int[] right) {
            int i = 0, j = 0, k = 0;

            while (i < left.length && j < right.length) {
                if (left[i] <= right[j]) {
                    result[k++] = left[i++];
                } else {
                    result[k++] = right[j++];
                }
            }

            while (i < left.length)
                result[k++] = left[i++];
            while (j < right.length)
                result[k++] = right[j++];
        }

        // Quick Sort (average case)
        public static void quickSort(int[] array, int low, int high) {
            if (low < high) {
                int pivotIndex = partition(array, low, high);
                quickSort(array, low, pivotIndex - 1); // Divide
                quickSort(array, pivotIndex + 1, high); // Divide
            }
        }

        private static int partition(int[] array, int low, int high) {
            int pivot = array[high];
            int i = low - 1;

            for (int j = low; j < high; j++) {
                if (array[j] <= pivot) {
                    i++;
                    swap(array, i, j);
                }
            }
            swap(array, i + 1, high);
            return i + 1;
        }

        private static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        // Heap Sort
        public static void heapSort(int[] array) {
            int n = array.length;

            // Build max heap: O(n)
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i);
            }

            // Extract elements: O(n log n)
            for (int i = n - 1; i > 0; i--) {
                swap(array, 0, i);
                heapify(array, i, 0); // O(log n) for each of n elements
            }
        }

        private static void heapify(int[] array, int n, int i) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && array[left] > array[largest])
                largest = left;
            if (right < n && array[right] > array[largest])
                largest = right;

            if (largest != i) {
                swap(array, i, largest);
                heapify(array, n, largest);
            }
        }
    }

    // ========================================
    // O(n²) - QUADRATIC TIME
    // ========================================

    /**
     * O(n²) - Quadratic Time Nested loops over the same dataset
     */
    public static class QuadraticTime {

        // Bubble Sort - Classic O(n²) algorithm
        public static void bubbleSort(int[] array) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) { // Outer loop: n iterations
                for (int j = 0; j < n - i - 1; j++) { // Inner loop: up to n iterations
                    if (array[j] > array[j + 1]) {
                        // Swap elements
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
            }
        }

        // Selection Sort
        public static void selectionSort(int[] array) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) { // Outer loop: n iterations
                int minIndex = i;
                for (int j = i + 1; j < n; j++) { // Inner loop: n-i iterations
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                // Swap minimum element with first element
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
            }
        }

        // Finding all pairs with given sum
        public static List<int[]> findPairsWithSum(int[] array, int targetSum) {
            List<int[]> pairs = new ArrayList<>();

            for (int i = 0; i < array.length; i++) { // Outer loop: n iterations
                for (int j = i + 1; j < array.length; j++) { // Inner loop: up to n iterations
                    if (array[i] + array[j] == targetSum) {
                        pairs.add(new int[] {array[i], array[j]});
                    }
                }
            }
            return pairs;
        }

        // Matrix multiplication (naive approach)
        public static int[][] multiplyMatrices(int[][] A, int[][] B) {
            int n = A.length;
            int[][] result = new int[n][n];

            for (int i = 0; i < n; i++) { // Outer loop: n iterations
                for (int j = 0; j < n; j++) { // Middle loop: n iterations
                    for (int k = 0; k < n; k++) { // Inner loop: n iterations
                        result[i][j] += A[i][k] * B[k][j]; // Total: O(n³)
                    }
                }
            }
            return result;
        }
    }

    // ========================================
    // O(n³) - CUBIC TIME
    // ========================================

    /**
     * O(n³) - Cubic Time Triple nested loops
     */
    public static class CubicTime {

        // Three Sum problem (brute force)
        public static List<List<Integer>> threeSum(int[] nums, int target) {
            List<List<Integer>> result = new ArrayList<>();
            int n = nums.length;

            for (int i = 0; i < n - 2; i++) { // Outer loop: n iterations
                for (int j = i + 1; j < n - 1; j++) { // Middle loop: n iterations
                    for (int k = j + 1; k < n; k++) { // Inner loop: n iterations
                        if (nums[i] + nums[j] + nums[k] == target) {
                            result.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        }
                    }
                }
            }
            return result;
        }

        // Floyd-Warshall Algorithm for shortest paths
        public static void floydWarshall(int[][] graph) {
            int n = graph.length;

            for (int k = 0; k < n; k++) { // Outer loop: n iterations
                for (int i = 0; i < n; i++) { // Middle loop: n iterations
                    for (int j = 0; j < n; j++) { // Inner loop: n iterations
                        if (graph[i][k] != Integer.MAX_VALUE && graph[k][j] != Integer.MAX_VALUE
                                && graph[i][k] + graph[k][j] < graph[i][j]) {
                            graph[i][j] = graph[i][k] + graph[k][j];
                        }
                    }
                }
            }
        }
    }

    // ========================================
    // O(2^n) - EXPONENTIAL TIME
    // ========================================

    /**
     * O(2^n) - Exponential Time Each step doubles the number of operations
     */
    public static class ExponentialTime {

        // Fibonacci sequence (naive recursive approach)
        public static long fibonacci(int n) {
            if (n <= 1)
                return n;

            // Each call branches into 2 recursive calls
            return fibonacci(n - 1) + fibonacci(n - 2);
        }

        // Power Set generation (all subsets)
        public static List<List<Integer>> generateSubsets(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            generateSubsetsHelper(nums, 0, new ArrayList<>(), result);
            return result;
        }

        private static void generateSubsetsHelper(int[] nums, int index, List<Integer> current,
                                                  List<List<Integer>> result) {
            if (index == nums.length) {
                result.add(new ArrayList<>(current));
                return;
            }

            // Don't include current element
            generateSubsetsHelper(nums, index + 1, current, result);

            // Include current element
            current.add(nums[index]);
            generateSubsetsHelper(nums, index + 1, current, result);
            current.remove(current.size() - 1);
        }

        // Tower of Hanoi
        public static void towerOfHanoi(int n, char source, char destination, char auxiliary) {
            if (n == 1) {
                System.out.println("Move disk 1 from " + source + " to " + destination);
                return;
            }

            // Move n-1 disks from source to auxiliary
            towerOfHanoi(n - 1, source, auxiliary, destination);

            // Move the bottom disk from source to destination
            System.out.println("Move disk " + n + " from " + source + " to " + destination);

            // Move n-1 disks from auxiliary to destination
            towerOfHanoi(n - 1, auxiliary, destination, source);
        }
    }

    // ========================================
    // O(n!) - FACTORIAL TIME
    // ========================================

    /**
     * O(n!) - Factorial Time Generating all permutations
     */
    public static class FactorialTime {

        // Generate all permutations of an array
        public static List<List<Integer>> generatePermutations(int[] nums) {
            List<List<Integer>> result = new ArrayList<>();
            generatePermutationsHelper(nums, 0, result);
            return result;
        }

        private static void generatePermutationsHelper(int[] nums, int start,
                                                       List<List<Integer>> result) {
            if (start == nums.length) {
                List<Integer> permutation = new ArrayList<>();
                for (int num : nums) {
                    permutation.add(num);
                }
                result.add(permutation);
                return;
            }

            for (int i = start; i < nums.length; i++) {
                // Swap
                swap(nums, start, i);

                // Recurse
                generatePermutationsHelper(nums, start + 1, result);

                // Backtrack
                swap(nums, start, i);
            }
        }

        private static void swap(int[] array, int i, int j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

        // Traveling Salesman Problem (brute force)
        public static int solveTSP(int[][] graph) {
            int n = graph.length;
            int[] cities = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                cities[i] = i + 1; // Cities 1 to n-1 (starting from city 0)
            }

            int minCost = Integer.MAX_VALUE;
            List<List<Integer>> permutations = generatePermutations(cities);

            for (List<Integer> perm : permutations) {
                int cost = 0;
                int current = 0; // Start from city 0

                for (int city : perm) {
                    cost += graph[current][city];
                    current = city;
                }
                cost += graph[current][0]; // Return to starting city

                minCost = Math.min(minCost, cost);
            }

            return minCost;
        }
    }

    // Helper class for tree operations
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ========================================
    // DEMONSTRATION AND TESTING
    // ========================================

    public static void main(String[] args) {
        System.out.println("=== Big O Complexity Examples ===\n");

        // Test data
        int[] testArray = {64, 34, 25, 12, 22, 11, 90};
        int[] sortedArray = {1, 3, 5, 7, 9, 11, 13, 15};

        // O(1) examples
        System.out.println("O(1) - Constant Time:");
        System.out.println("First element: " + ConstantTime.getFirstElement(testArray));
        System.out.println();

        // O(log n) examples
        System.out.println("O(log n) - Logarithmic Time:");
        System.out.println("Binary search for 7: " + LogarithmicTime.binarySearch(sortedArray, 7));
        System.out.println();

        // O(n) examples
        System.out.println("O(n) - Linear Time:");
        System.out.println("Maximum element: " + LinearTime.findMax(testArray));
        System.out.println();

        // O(n log n) examples
        System.out.println("O(n log n) - Linearithmic Time:");
        int[] arrayToSort = testArray.clone();
        LinearithmicTime.mergeSort(arrayToSort);
        System.out.println("Merge sorted: " + Arrays.toString(arrayToSort));
        System.out.println();

        // O(n²) examples
        System.out.println("O(n²) - Quadratic Time:");
        int[] bubbleArray = testArray.clone();
        QuadraticTime.bubbleSort(bubbleArray);
        System.out.println("Bubble sorted: " + Arrays.toString(bubbleArray));
        System.out.println();

        // O(2^n) examples (small input only!)
        System.out.println("O(2^n) - Exponential Time:");
        System.out.println("Fibonacci(10): " + ExponentialTime.fibonacci(10));
        System.out.println();

        // O(n!) examples (very small input only!)
        System.out.println("O(n!) - Factorial Time:");
        int[] smallArray = {1, 2, 3};
        List<List<Integer>> perms = FactorialTime.generatePermutations(smallArray);
        System.out.println("Permutations of [1,2,3]: " + perms);

        System.out.println("\n=== Complexity Summary ===");
        System.out.println("O(1)      < O(log n) < O(n)      < O(n log n)");
        System.out.println("O(n log n) < O(n²)    < O(n³)     < O(2^n)     < O(n!)");
        System.out.println("\nBest to Worst Performance for large inputs!");
    }
}

