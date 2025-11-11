package io.awijaya.lab3;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.ca/2016-11-26-362-Design-Hit-Counter/
 * level: medium
 * type: code design
 *
 */
public class DesignHitCounter {
    /** Number of seconds included in the trailing window (5 minutes). */
    public static final int WINDOW_DURATION_SECONDS = 300;

    private DesignHitCounter() {
        // Utility class
    }

    /**
     * Common contract for hit counter implementations.
     */
    public interface HitCounter {
        /**
         * Records a new hit occurring at {@code timestamp}.
         *
         * @param timestamp the current timestamp in seconds granularity (must be positive and
         *        non-decreasing across operations)
         */
        void hit(int timestamp);

        /**
         * Returns the number of hits received during the most recent
         * {@value #WINDOW_DURATION_SECONDS} seconds, inclusive of the current timestamp.
         *
         * @param timestamp the current timestamp in seconds granularity (must be positive and
         *        non-decreasing across operations)
         * @return number of hits within the trailing window
         */
        int getHits(int timestamp);
    }

    /**
     * Validates that the provided timestamp complies with LeetCode constraints.
     *
     * @param timestamp timestamp to validate
     * @throws IllegalArgumentException if {@code timestamp} is non-positive
     */
    public static void validateTimestamp(int timestamp) {
        if (timestamp <= 0) {
            throw new IllegalArgumentException("Timestamp must be positive: " + timestamp);
        }
    }

    /**
     * Ensures that calls arrive in chronological order, matching the problem statement's guarantee.
     *
     * @param previousTimestamp the timestamp used by the previous operation
     * @param currentTimestamp the timestamp for the current operation
     * @throws IllegalArgumentException if operations arrive out of order
     */
    static void validateChronologicalOrder(int previousTimestamp, int currentTimestamp) {
        if (currentTimestamp < previousTimestamp) {
            throw new IllegalArgumentException("Timestamps must be non-decreasing. Previous: "
                    + previousTimestamp + ", current: " + currentTimestamp);
        }
    }

    /**
     * Approach 1: Queue-based hit counter.
     *
     * <p>
     * Maintains a deque of timestamps and evicts entries older than 300 seconds as new operations
     * arrive. This mirrors the canonical interview solution and keeps all historical hits within
     * the active window.
     *
     * <p>
     * Time Complexity: {@code O(1)} amortised per operation.<br>
     * Space Complexity: {@code O(W)} where {@code W = 300}.
     */
    public static final class QueueHitCounter implements HitCounter {

        private final Deque<Integer> timestamps = new ArrayDeque<>();
        private int lastTimestamp;

        @Override
        public void hit(int timestamp) {
            validateTimestamp(timestamp);
            purge(timestamp);
            timestamps.addLast(timestamp);
            lastTimestamp = timestamp;
        }

        @Override
        public int getHits(int timestamp) {
            validateTimestamp(timestamp);
            purge(timestamp);
            lastTimestamp = timestamp;
            return timestamps.size();
        }

        private void purge(int timestamp) {
            validateChronologicalOrder(lastTimestamp, timestamp);
            int threshold = timestamp - WINDOW_DURATION_SECONDS;
            while (!timestamps.isEmpty() && timestamps.peekFirst() <= threshold) {
                timestamps.removeFirst();
            }
        }
    }

    public static void main(String[] args) {
        HitCounter hitCounter = new QueueHitCounter();
        hitCounter.hit(1);       // hit at timestamp 1.
        hitCounter.hit(2);       // hit at timestamp 2.
        hitCounter.hit(3);       // hit at timestamp 3.
        System.out.println(hitCounter.getHits(4));   // get hits at timestamp 4, return 3.
        hitCounter.hit(300);     // hit at timestamp 300.
        System.out.println(hitCounter.getHits(300)); // get hits at timestamp 300, return 4.
        System.out.println(hitCounter.getHits(301)); // get hits at timestamp 301, return 3.
    }
}
