package io.awijaya.algo;

public class MyBinarySearchTree {
    int value;
    MyBinarySearchTree left;
    MyBinarySearchTree right;

    public MyBinarySearchTree(int value) {
        this.value = value;
    }

    public static MyBinarySearchTree createBST(int[] arr) {
        MyBinarySearchTree root;
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        } else {
            root = new MyBinarySearchTree(arr[0]);
        }

        if (arr.length == 1) {
            return root;
        }

        for (int i = 1; i < arr.length; i++) {
            int nodeValue = arr[i];
            MyBinarySearchTree parent = root;
            MyBinarySearchTree current = parent;

            while (current != null) {
                if (current.value > nodeValue) {
                    current = parent.left;
                    if (current == null) {
                        current = new MyBinarySearchTree(nodeValue);
                        parent.left = current;
                        break;
                    } else {
                        parent = current;
                    }
                } else {
                    current = parent.right;
                    if (current == null) {
                        current = new MyBinarySearchTree(nodeValue);
                        parent.right = current;
                        break;
                    } else {
                        parent = current;
                    }
                }
            }
        }
        return root;
    }

    public static MyBinarySearchTree insertBSTRecursive(MyBinarySearchTree root, int data) {
        if (root == null) {
            return new MyBinarySearchTree(data);
        }
        if (data < root.value) {
            root.left = insertBSTRecursive(root.left, data);
        } else {
            root.right = insertBSTRecursive(root.right, data);
        }
        return root;
    }


    public boolean searchIterative(int data) {
        boolean isFound = false;
        MyBinarySearchTree current = this;
        while (current != null) {
            if (data == current.value) {
                isFound = true;
                break;
            }
            if (data < current.value) {
                current = current.left;
            }
            if (data > current.value) {
                current = current.right;
            }
        }

        return isFound;
    }

    public void printInOrder() {
        if (this.left != null) {
            this.left.printInOrder();
        }
        System.out.println(this.value);
        if (this.right != null) {
            this.right.printInOrder();
        }
    }

    public static void main(String[] args) {
        int[] test1 = {3, 5, 6, 1, 8, 2, 4, 9, 7};
        MyBinarySearchTree myBinarySearchTree = MyBinarySearchTree.createBST(test1);
        myBinarySearchTree.printInOrder();

        MyBinarySearchTree myBinarySearchTree2 = null;
        for (int i = 0; i < test1.length; i++) {
            myBinarySearchTree2 = insertBSTRecursive(myBinarySearchTree2, test1[i]);
        }
        System.out.println(myBinarySearchTree2.searchIterative(1));
        System.out.println(myBinarySearchTree2.searchIterative(5));
        System.out.println(myBinarySearchTree2.searchIterative(8));
        System.out.println(myBinarySearchTree2.searchIterative(9));
        System.out.println(myBinarySearchTree2.searchIterative(10));
    }
}
