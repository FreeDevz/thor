package io.awijaya.lab;

/**
 * https://leetcode.com/problems/remove-duplicates-from-sorted-list/description/
 * level: easy
 */
public class RemoveDuplicatesFromSortedList {

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

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode prev = head;
        while (head != null) {
            int current = head.val;
            if (current == prev.val) {
                prev.next = head.next;
            } else {
                prev = head;
            }

            head = head.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
