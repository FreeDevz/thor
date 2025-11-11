package io.awijaya.lab3;

import java.util.Map;
import java.util.TreeMap;

/**
 * https://leetcode.com/problems/my-calendar-i/submissions/1824958135/
 * level: medium
 * type: code design
 */
public class MyCalendarI {
    public static void validateInterval(int start, int end) {
        if (start < 0) {
            throw new IllegalArgumentException("Start time must be non-negative");
        }
        if (end <= start) {
            throw new IllegalArgumentException(
                    "End time must be greater than start time: [" + start + ", " + end + ")");
        }
    }

    public MyCalendarI() {

    }

    private final TreeMap<Integer, Integer> calendar = new TreeMap<>();

    /**
     * Attempts to add the interval {@code [start, end)} to the calendar.
     *
     * @return {@code true} if the booking was successful, {@code false} otherwise
     */
    public boolean book(int start, int end) {
        validateInterval(start, end);

        Map.Entry<Integer, Integer> previous = calendar.floorEntry(start);
        if (previous != null && previous.getValue() > start) {
            return false;
        }

        Map.Entry<Integer, Integer> next = calendar.ceilingEntry(start);
        if (next != null && next.getKey() < end) {
            return false;
        }

        calendar.put(start, end);
        return true;
    }
}
