package io.awijaya.lab;

// https://leetcode.com/problems/merge-two-sorted-lists/
public class MyMergedTwoLists {
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * Approach 1: Iterative Merge with Dummy Node
     * <p>
     * This is the most efficient and commonly used approach for merging sorted linked lists.
     * <p>
     * Algorithm: 1. Create a dummy node to simplify edge cases 2. Use a pointer to build the
     * merged list 3. Compare nodes from both lists and link the smaller one 4. Move the pointer
     * forward 5. Attach remaining nodes from the non-empty list
     * <p>
     * Time Complexity: O(n + m) where n and m are the lengths of the two lists Space Complexity:
     * O(1) as we reuse existing nodes
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                ListNode tmp = new ListNode(list1.val);
                current.next = tmp;
                list1 = list1.next;
            } else {
                ListNode tmp = new ListNode(list2.val);
                current.next = tmp;
                list2 = list2.next;
            }
            current = current.next;
        }

        if (list1 != null) {
            current.next = list1;
        }
        if (list2 != null) {
            current.next = list2;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        int[] list1 = {5};
        int[] list2 = {1, 2, 4};

        int[] list3 = {1, 2, 4, 5};
        int[] list4 = {1, 3, 4};
        int[] output34 = {1, 1, 2, 3, 4, 4, 5};
    }
}
