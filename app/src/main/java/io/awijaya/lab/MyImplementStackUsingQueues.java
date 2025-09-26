package io.awijaya.lab;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/implement-stack-using-queues/description/
 * level: easy
 */
public class MyImplementStackUsingQueues {
    List<Integer> queue = new LinkedList<>();

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        int last = queue.getLast();
        queue.remove(queue.size() - 1);

        return last;
    }

    public int top() {

        return queue.getLast();
    }

    public boolean empty() {

        return queue.isEmpty();
    }

    public static void main(String[] args) {
        MyImplementStackUsingQueues myStack = new MyImplementStackUsingQueues();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.top()); // return 2
        System.out.println(myStack.pop()); // return 2
        System.out.println(myStack.empty()); // return False
    }
}
