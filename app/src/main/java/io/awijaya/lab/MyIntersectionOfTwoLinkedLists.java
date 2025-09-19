package io.awijaya.lab;

/**
 * https://leetcode.com/problems/intersection-of-two-linked-lists/
 * level: easy
 */
public class MyIntersectionOfTwoLinkedLists {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode traverseA = headA;
        ListNode traverseB;

        ListNode maybeIntersect = null;

        while (traverseA != null) {
            traverseB = headB;
            while (traverseB != null) {
                if (traverseA == traverseB) {
                    maybeIntersect = traverseA;
                    return maybeIntersect;
                }

                traverseB = traverseB.next;
            }

            traverseA = traverseA.next;
        }

        return maybeIntersect;
    }

    /**
     * Approach 1: Two-Pointer Technique (Optimal)
     *
     * Key Insight: Use two pointers that traverse both lists. When a pointer reaches the end of its
     * list, redirect it to the head of the other list. Both pointers will traverse the same total
     * distance (m + n), so they will meet at the intersection point or both reach null.
     *
     * Time Complexity: O(m + n) Space Complexity: O(1)
     *
     * @param headA the head of the first linked list
     * @param headB the head of the second linked list
     * @return the intersection node, or null if no intersection
     */
    public ListNode cursorGetIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode pointerA = headA;
        ListNode pointerB = headB;

        // Traverse until both pointers meet or both reach null
        while (pointerA != pointerB) {
            // Move to next node, or to head of other list if at end
            pointerA = (pointerA == null) ? headB : pointerA.next;
            pointerB = (pointerB == null) ? headA : pointerB.next;
        }

        return pointerA; // This will be the intersection node or null
    }

    public static void main(String[] args) {
        // [4,1,8,4,5]
        // [5,6,1,8,4,5]

        ListNode list1node1 = new ListNode(4);
        ListNode list1node2 = new ListNode(1);
        ListNode nodeIntersect = new ListNode(8);
        ListNode list1node3 = new ListNode(4);
        ListNode list1node4 = new ListNode(5);
        list1node1.next = list1node2;
        list1node2.next = nodeIntersect;
        nodeIntersect.next = list1node3;
        list1node3.next = list1node4;

        ListNode list2node1 = new ListNode(5);
        ListNode list2node2 = new ListNode(6);
        ListNode list2node3 = new ListNode(1);
        list2node1.next = list2node2;
        list2node2.next = list2node3;
        list2node3.next = nodeIntersect;

        ListNode result = new MyIntersectionOfTwoLinkedLists().getIntersectionNode(list1node1, list2node1);
        System.out.println(result.val);
    }
}
