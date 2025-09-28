package io.awijaya.lab;

/**
 * https://leetcode.com/problems/first-bad-version/description/
 * level: easy
 */
public class FirstBadVersion {
    // Mock API for testing purposes
    private int firstBadVersion;

    public FirstBadVersion(int firstBadVersion) {
        this.firstBadVersion = firstBadVersion;
    }

    /**
     * Mock API that simulates the isBadVersion function
     */
    public boolean isBadVersion(int version) {
        return version >= firstBadVersion;
    }

    /**
     * Approach 1: Binary Search (Optimal) Use binary search to find the first bad version.
     * <p>
     * Time Complexity: O(log n) Space Complexity: O(1)
     */
    public int firstBadVersionBinarySearch(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("n must be at least 1");
        }

        int left = 1;
        int right = n;

        while (left < right) {
            int mid = left + (right - left) / 2; // Avoid overflow
            if (isBadVersion(mid)) {
                right = mid; // First bad version is at mid or before
            } else {
                left = mid + 1; // First bad version is after mid
            }
        }

        return left;
    }
}
