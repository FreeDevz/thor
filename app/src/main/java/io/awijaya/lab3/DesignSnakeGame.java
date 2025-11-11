package io.awijaya.lab3;

import java.util.*;

/**
 * https://leetcode.com/problems/design-snake-game/description/
 * https://leetcode.ca/2016-11-17-353-Design-Snake-Game/
 * level: medium
 * type: code design
 */
public final class DesignSnakeGame {

    private DesignSnakeGame() {
        // Utility class
    }

    private static final int MAX_DIMENSION = 10_000;
    private static final int MAX_FOOD_ITEMS = 50;

    /**
     * Validates the board dimensions.
     *
     * @throws IllegalArgumentException if {@code width} or {@code height} is outside the allowed
     *         range
     */
    public static void validateDimensions(int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Width and height must be positive");
        }
        if (width > MAX_DIMENSION || height > MAX_DIMENSION) {
            throw new IllegalArgumentException(
                    "Width and height must not exceed " + MAX_DIMENSION + " cells");
        }
    }

    /**
     * Validates the food positions to ensure they fall within the board boundaries and satisfy
     * LeetCode constraints.
     *
     * @throws IllegalArgumentException if any food position is outside the board or if the number
     *         of food items exceeds the allowed maximum
     */
    public static void validateFood(int width, int height, int[][] food) {
        Objects.requireNonNull(food, "Food array must not be null");
        if (food.length == 0) {
            throw new IllegalArgumentException("Food array must contain at least one item");
        }
        if (food.length > MAX_FOOD_ITEMS) {
            throw new IllegalArgumentException(
                    "Food array must not contain more than " + MAX_FOOD_ITEMS + " items");
        }
        for (int[] position : food) {
            if (position == null || position.length != 2) {
                throw new IllegalArgumentException(
                        "Each food position must contain exactly two coordinates");
            }
            int row = position[0];
            int col = position[1];
            if (row < 0 || row >= height || col < 0 || col >= width) {
                throw new IllegalArgumentException(
                        "Food position out of bounds: [" + row + ", " + col + "]");
            }
        }
    }

    /** Enumeration of the four possible move directions. */
    public enum Direction {
        UP(-1, 0), DOWN(1, 0), LEFT(0, -1), RIGHT(0, 1);

        private final int dRow;
        private final int dCol;

        Direction(int dRow, int dCol) {
            this.dRow = dRow;
            this.dCol = dCol;
        }

        public int rowDelta() {
            return dRow;
        }

        public int colDelta() {
            return dCol;
        }

        /**
         * Parses a single-character direction string (case-insensitive).
         *
         * @throws IllegalArgumentException if the input does not map to a direction
         */
        public static Direction fromString(String direction) {
            Objects.requireNonNull(direction, "Direction must not be null");
            if (direction.length() != 1) {
                throw new IllegalArgumentException("Direction must be a single character");
            }
            return fromChar(direction.charAt(0));
        }

        /**
         * Parses a direction character (case-insensitive).
         *
         * @throws IllegalArgumentException if the character does not represent a valid direction
         */
        public static Direction fromChar(char direction) {
            return switch (Character.toUpperCase(direction)) {
                case 'U' -> UP;
                case 'D' -> DOWN;
                case 'L' -> LEFT;
                case 'R' -> RIGHT;
                default -> throw new IllegalArgumentException(
                        "Unsupported direction: " + direction);
            };
        }

        /** Returns the direction as an uppercase single-letter string. */
        @Override
        public String toString() {
            return name().substring(0, 1);
        }
    }

    private static int encode(int row, int col, int width) {
        return row * width + col;
    }

    private static int row(int encoded, int width) {
        return encoded / width;
    }

    private static int col(int encoded, int width) {
        return encoded % width;
    }

    /**
     * Approach 1: Deque + HashSet (Optimal).
     *
     * <p>
     * Maintains the snake body as a deque of encoded positions and tracks occupied cells with a
     * hash set. Removing the tail happens prior to collision detection when the snake does not eat
     * food, allowing re-entry into the tail's previous position. All operations run in {@code O(1)}
     * time.
     *
     * <p>
     * Time Complexity: {@code O(1)} per call to {@link #move(Direction)}<br>
     * Space Complexity: {@code O(k)} where {@code k} is the snake length
     */
    public static final class SnakeGameDeque {
        private final int width;
        private final int height;
        private final int[][] food;
        private final Deque<Integer> body = new ArrayDeque<>();
        private final Set<Integer> occupied = new HashSet<>();

        private int foodIndex;
        private int score;
        private boolean gameOver;

        public SnakeGameDeque(int width, int height, int[][] food) {
            validateDimensions(width, height);
            validateFood(width, height, food);
            this.width = width;
            this.height = height;
            this.food = deepCopy(food);
            int start = encode(0, 0, width);
            body.addFirst(start);
            occupied.add(start);
        }

        /**
         * Moves the snake in the requested direction.
         *
         * @param direction movement direction
         * @return current score, or {@code -1} if the game is over
         */
        public int move(Direction direction) {
            Objects.requireNonNull(direction, "Direction must not be null");
            if (gameOver) {
                return -1;
            }

            int head = body.peekFirst();
            int currentRow = row(head, width);
            int currentCol = col(head, width);

            int nextRow = currentRow + direction.rowDelta();
            int nextCol = currentCol + direction.colDelta();

            if (isOutOfBounds(nextRow, nextCol)) {
                gameOver = true;
                return -1;
            }

            boolean eatsFood = foodIndex < food.length && nextRow == food[foodIndex][0]
                    && nextCol == food[foodIndex][1];

            if (!eatsFood) {
                int removedTail = body.removeLast();
                occupied.remove(removedTail);
            }

            int nextEncoded = encode(nextRow, nextCol, width);
            if (occupied.contains(nextEncoded)) {
                gameOver = true;
                return -1;
            }

            body.addFirst(nextEncoded);
            occupied.add(nextEncoded);

            if (eatsFood) {
                score++;
                foodIndex++;
            }
            return score;
        }

        /** Returns the current score (number of food items consumed). */
        public int score() {
            return score;
        }

        /** Returns {@code true} if the game has ended. */
        public boolean isGameOver() {
            return gameOver;
        }

        /** Returns an immutable snapshot of the snake body from head to tail. */
        public List<int[]> snapshot() {
            List<int[]> copy = new ArrayList<>(body.size());
            for (int position : body) {
                copy.add(new int[] {row(position, width), col(position, width)});
            }
            return Collections.unmodifiableList(copy);
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || row >= height || col < 0 || col >= width;
        }
    }

    /**
     * Approach 2: Custom Doubly Linked List.
     *
     * <p>
     * Represents the snake body as a doubly linked list to emphasize constant-time insertion and
     * removal at both ends. A hash set tracks occupied cells for collision detection; the list
     * nodes are only used for traversal and snapshot creation.
     *
     * <p>
     * Time Complexity: {@code O(1)} per {@link #move(Direction)}<br>
     * Space Complexity: {@code O(k)} where {@code k} is the snake length
     */
    public static final class SnakeGameLinkedList {
        private final int width;
        private final int height;
        private final int[][] food;

        private Node head;
        private Node tail;
        private final Set<Integer> occupied = new HashSet<>();
        private int foodIndex;
        private int score;
        private boolean gameOver;

        public SnakeGameLinkedList(int width, int height, int[][] food) {
            validateDimensions(width, height);
            validateFood(width, height, food);
            this.width = width;
            this.height = height;
            this.food = deepCopy(food);
            head = tail = new Node(0, 0);
            occupied.add(encode(0, 0, width));
        }

        public int move(Direction direction) {
            Objects.requireNonNull(direction, "Direction must not be null");
            if (gameOver) {
                return -1;
            }

            int nextRow = head.row + direction.rowDelta();
            int nextCol = head.col + direction.colDelta();

            if (isOutOfBounds(nextRow, nextCol)) {
                gameOver = true;
                return -1;
            }

            boolean eatsFood = foodIndex < food.length && nextRow == food[foodIndex][0]
                    && nextCol == food[foodIndex][1];

            if (!eatsFood) {
                removeTail();
            }

            int encoded = encode(nextRow, nextCol, width);
            if (occupied.contains(encoded)) {
                gameOver = true;
                return -1;
            }

            prepend(nextRow, nextCol);
            occupied.add(encoded);

            if (eatsFood) {
                score++;
                foodIndex++;
            }
            return score;
        }

        public int score() {
            return score;
        }

        public boolean isGameOver() {
            return gameOver;
        }

        public List<int[]> snapshot() {
            List<int[]> result = new ArrayList<>();
            for (Node node = head; node != null; node = node.next) {
                result.add(new int[] {node.row, node.col});
            }
            return Collections.unmodifiableList(result);
        }

        private void prepend(int row, int col) {
            Node newHead = new Node(row, col);
            newHead.next = head;
            head.prev = newHead;
            head = newHead;
        }

        private void removeTail() {
            int encoded = encode(tail.row, tail.col, width);
            occupied.remove(encoded);
            if (head == tail) {
                return;
            }
            tail = tail.prev;
            tail.next.prev = null;
            tail.next = null;
        }

        private boolean isOutOfBounds(int row, int col) {
            return row < 0 || row >= height || col < 0 || col >= width;
        }

        private static final class Node {
            private final int row;
            private final int col;
            private Node prev;
            private Node next;

            private Node(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
    }

    private static int[][] deepCopy(int[][] food) {
        int[][] copy = new int[food.length][];
        for (int i = 0; i < food.length; i++) {
            copy[i] = food[i].clone();
        }
        return copy;
    }
}



