package io.awijaya.lab3;

/**
 * https://leetcode.com/problems/word-search/
 * level: medium
 */
public class WordSearch {
    // ========================================
    // APPROACH 1: BACKTRACKING WITH IN-PLACE MARKING (RECOMMENDED)
    // Time: O(M * N * 4^L), Space: O(L) where M,N = board dimensions, L = word length
    // ========================================

    /**
     * Approach 1: Backtracking with In-Place Marking
     *
     * This is the most space-efficient approach that modifies the board temporarily to mark visited
     * cells.
     *
     * Algorithm: 1. Iterate through each cell in the board 2. When we find a cell matching the
     * first character, start DFS 3. During DFS: - Check if current cell matches current character
     * in word - Mark cell as visited by replacing with a special character - Recursively explore
     * all 4 directions (up, down, left, right) - Backtrack by restoring the original character 4.
     * Return true if word is found, false otherwise
     *
     * Time Complexity: O(M * N * 4^L) where: - M * N: iterate through all cells - 4^L: worst case
     * DFS explores 4 directions for each character Space Complexity: O(L) for recursion stack depth
     *
     * Pros: Most space-efficient, no extra data structures needed Cons: Modifies input (though
     * temporarily), slightly harder to understand
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null || word.length() == 0) {
            return false;
        }

        int m = board.length;
        int n = board[0].length;

        // Early termination: check if board has enough characters
        if (word.length() > m * n) {
            return false;
        }

        // Try starting from each cell
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(board, word, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int i, int j, int index) {
        // Found the entire word
        if (index == word.length()) {
            return true;
        }

        // Boundary checks and character match check
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length
                || board[i][j] != word.charAt(index)) {
            return false;
        }

        // Mark current cell as visited
        char temp = board[i][j];
        board[i][j] = '#';

        // Explore all 4 directions
        boolean found = dfs(board, word, i + 1, j, index + 1) || // down
                dfs(board, word, i - 1, j, index + 1) || // up
                dfs(board, word, i, j + 1, index + 1) || // right
                dfs(board, word, i, j - 1, index + 1); // left

        // Backtrack: restore the cell
        board[i][j] = temp;

        return found;
    }

    public static void main(String[] args) {
        int [][] board = {{2, 2, 3}, {1, 2, 3}};
        int row = board.length;
        int col = board[0].length;
        System.out.println("row: " + row + " col: " + col);
    }
}
