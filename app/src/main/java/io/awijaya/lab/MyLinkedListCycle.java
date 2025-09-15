package io.awijaya.lab;

import java.util.HashSet;
import java.util.Set;

public class MyLinkedListCycle {

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    private Set<ListNode> seen = new HashSet<>();

    public boolean hasCycle(ListNode head) {
        while (head != null) {
            if (!seen.contains(head)) {
                seen.add(head);
            } else {
                return true;
            }
            head = head.next;
        }

        return false;
    }

    /**
     * Approach 1: Floyd's Cycle Detection Algorithm (Tortoise and Hare) - OPTIMAL
     * <p>
     * This is the most efficient solution using two pointers moving at different speeds. The slow
     * pointer moves one step at a time, while the fast pointer moves two steps. If there's a cycle,
     * the fast pointer will eventually catch up to the slow pointer.
     *
     * @param head the head of the linked list
     * @return true if there is a cycle, false otherwise
     */
    public boolean hasCycleFloyd(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head;

        // Move slow one step and fast two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            // If they meet, there's a cycle
            if (slow == fast) {
                return true;
            }
        }

        return false;
    }
}
