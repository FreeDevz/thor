package io.awijaya.lab;

import java.util.*;

/**
 * https://leetcode.com/problems/alert-using-same-key-card-three-or-more-times-in-a-one-hour-period/description/
 * level: medium
 */
public class AlertUsingKeyCardsThreeOrMoreTimeInOneHourPeriod {
    /**
     * Approach 1: HashMap + Sorting with Sliding Window
     *
     * Algorithm: 1. Group all access times by worker name using a HashMap 2. Convert each time
     * string "HH:MM" to minutes since midnight 3. Sort each worker's access times 4. For each
     * worker, use a sliding window to check if any 3 consecutive accesses fall within 60 minutes 5.
     * Collect workers who trigger alerts and sort alphabetically
     *
     * Time Complexity: O(N * log(N)) where N is the number of total accesses - Grouping: O(N) -
     * Sorting each worker's times: O(N * log(N)) in worst case (all accesses by one worker) -
     * Checking alerts: O(N) - Final sorting: O(K * log(K)) where K is number of unique workers
     *
     * Space Complexity: O(N) for storing all access times in the HashMap
     *
     * @param keyName Array of worker names
     * @param keyTime Array of access times in "HH:MM" format
     * @return List of workers who received alerts, sorted alphabetically
     */
    public List<String> alertNames(String[] keyName, String[] keyTime) {
        // Group access times by worker name
        Map<String, List<Integer>> accessMap = new HashMap<>();

        for (int i = 0; i < keyName.length; i++) {
            String name = keyName[i];
            int minutes = convertToMinutes(keyTime[i]);

            accessMap.putIfAbsent(name, new ArrayList<>());
            accessMap.get(name).add(minutes);
        }

        List<String> alerts = new ArrayList<>();

        // Check each worker's access pattern
        for (Map.Entry<String, List<Integer>> entry : accessMap.entrySet()) {
            String name = entry.getKey();
            List<Integer> times = entry.getValue();

            // Sort times in ascending order
            Collections.sort(times);

            // Check for 3 or more accesses within 60 minutes
            if (hasAlert(times)) {
                alerts.add(name);
            }
        }

        // Sort alphabetically
        Collections.sort(alerts);
        return alerts;
    }

    /**
     * Helper method to convert "HH:MM" format to minutes since midnight
     *
     * @param time Time string in "HH:MM" format
     * @return Total minutes since midnight
     */
    private int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return hours * 60 + minutes;
    }

    /**
     * Helper method to check if a sorted list of access times has any window of 3 or more accesses
     * within 60 minutes
     *
     * @param sortedTimes Sorted list of access times in minutes
     * @return true if there's an alert window, false otherwise
     */
    private boolean hasAlert(List<Integer> sortedTimes) {
        if (sortedTimes.size() < 3) {
            return false;
        }

        // Check every consecutive triplet
        for (int i = 0; i <= sortedTimes.size() - 3; i++) {
            // If 3rd access is within 60 minutes of 1st access
            if (sortedTimes.get(i + 2) - sortedTimes.get(i) <= 60) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
