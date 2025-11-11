package io.awijaya.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * https://leetcode.com/problems/design-browser-history/description/
 * level: medium
 */
public class DesignBrowserHistory {
    private static final int MAX_URL_LENGTH = 2048;

    private DesignBrowserHistory() {
        // Utility class
    }

    /**
     * Validates that the provided URL adheres to LeetCode constraints.
     *
     * @param url the URL to validate
     * @throws NullPointerException if {@code url} is {@code null}
     * @throws IllegalArgumentException if {@code url} is blank or exceeds the maximum length
     */
    public static void validateUrl(String url) {
        Objects.requireNonNull(url, "URL must not be null");
        if (url.isBlank()) {
            throw new IllegalArgumentException("URL must contain non-whitespace characters");
        }
        if (url.length() > MAX_URL_LENGTH) {
            throw new IllegalArgumentException(
                    "URL length must be <= " + MAX_URL_LENGTH + " characters");
        }
        if (url.indexOf(' ') >= 0) {
            throw new IllegalArgumentException("URL must not contain whitespace characters");
        }
    }

    /**
     * Validates that the step count is not negative.
     *
     * @param steps number of steps to move backward or forward
     * @throws IllegalArgumentException if {@code steps} is negative
     */
    public static void validateSteps(int steps) {
        if (steps < 0) {
            throw new IllegalArgumentException("Step count must be non-negative");
        }
    }

    /**
     * Approach 1: Dynamic Array + Pointer.
     *
     * <p>
     * Maintains the browser history in a resizable array while tracking the current index. When a
     * new URL is visited, all forward history is discarded in {@code O(n)} time in the worst case
     * by clearing the tail of the list. Moving backward or forward runs in {@code O(1)} time.
     *
     * <p>
     * Time Complexity: {@code O(n)} for {@link #visit(String)} due to trimming, {@code O(1)} for
     * {@link #back(int)} and {@link #forward(int)}<br>
     * Space Complexity: {@code O(n)} where {@code n} is the number of stored URLs
     */
    public static final class BrowserHistoryArray {
        private final List<String> history;
        private int currentIndex;

        /**
         * Creates a new browser history with the supplied homepage.
         *
         * @param homepage initial page to load
         */
        public BrowserHistoryArray(String homepage) {
            validateUrl(homepage);
            this.history = new ArrayList<>();
            this.history.add(homepage);
            this.currentIndex = 0;
        }

        /**
         * Visits a new URL and discards any forward history.
         *
         * @param url new URL to visit
         */
        public void visit(String url) {
            validateUrl(url);
            if (currentIndex < history.size() - 1) {
                history.subList(currentIndex + 1, history.size()).clear();
            }
            history.add(url);
            currentIndex = history.size() - 1;
        }

        /**
         * Moves backward in history by up to {@code steps} entries.
         *
         * @param steps number of steps to move backward
         * @return the URL at the new current position
         */
        public String back(int steps) {
            validateSteps(steps);
            currentIndex = Math.max(0, currentIndex - steps);
            return history.get(currentIndex);
        }

        /**
         * Moves forward in history by up to {@code steps} entries.
         *
         * @param steps number of steps to move forward
         * @return the URL at the new current position
         */
        public String forward(int steps) {
            validateSteps(steps);
            currentIndex = Math.min(history.size() - 1, currentIndex + steps);
            return history.get(currentIndex);
        }

        /** Returns the URL currently displayed. */
        public String current() {
            return history.get(currentIndex);
        }

        /**
         * Returns a snapshot of the history list for testing and debugging.
         *
         * <p>
         * The returned list is immutable to guard against accidental modifications.
         */
        public List<String> snapshot() {
            return Collections.unmodifiableList(new ArrayList<>(history));
        }

        /** Returns the index of the current page in the history list. */
        public int cursorIndex() {
            return currentIndex;
        }

        /** Returns the number of stored pages. */
        public int size() {
            return history.size();
        }
    }
}
