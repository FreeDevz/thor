package io.awijaya.lab;

/**
 * https://leetcode.com/problems/reverse-linked-list/description/
 * level: easy
 */
public class ReverseLinkedList {
    public static class ListNode {
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

    // two pointers technique
    public ListNode reverseListIterative(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode next = curr.next; // Store the next node
            curr.next = prev; // Reverse the current node's pointer
            prev = curr; // Move prev to current node
            curr = next; // Move to the next node
        }

        return prev; // prev becomes the new head of reversed list
    }

    // recursive implementation
    public ListNode reverseListRecursive(ListNode head) {
        // Base case: empty list or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Recursively reverse the rest of the list
        ListNode newHead = reverseListRecursive(head.next);

        // Adjust pointers: make the next node point back to current node
        head.next.next = head;
        head.next = null; // Break the original link

        return newHead; // Return the new head (originally the tail)
    }

    public static void main(String[] args) {
        ReverseLinkedList reverseLinkedList = new ReverseLinkedList();

        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        one.next = two;
        two.next = three;

        ListNode result = reverseLinkedList.reverseListRecursive(one);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }

    }
}
