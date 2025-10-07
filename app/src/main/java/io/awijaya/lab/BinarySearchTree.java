package io.awijaya.lab;

public class BinarySearchTree {
    int value;
    BinarySearchTree left;
    BinarySearchTree right;

    public BinarySearchTree(int value) {
        this.value = value;
    }

    public static BinarySearchTree createBST(int[] arr) {
        BinarySearchTree root;
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        } else {
            root = new BinarySearchTree(arr[0]);
        }

        if (arr.length == 1) {
            return root;
        }

        for (int i = 1; i < arr.length; i++) {
            int nodeValue = arr[i];
            BinarySearchTree parent = root;
            BinarySearchTree current = parent;

            while (current != null) {
                if (current.value > nodeValue) {
                    current = parent.left;
                    if (current == null) {
                        current = new BinarySearchTree(nodeValue);
                        parent.left = current;
                        break;
                    } else {
                        parent = current;
                    }
                } else {
                    current = parent.right;
                    if (current == null) {
                        current = new BinarySearchTree(nodeValue);
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

    public static BinarySearchTree insertBSTRecursive(BinarySearchTree root, int data) {
        if (root == null) {
            return new BinarySearchTree(data);
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
        BinarySearchTree current = this;
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
        BinarySearchTree binarySearchTree = BinarySearchTree.createBST(test1);
        binarySearchTree.printInOrder();

        BinarySearchTree binarySearchTree2 = null;
        for (int i = 0; i < test1.length; i++) {
            binarySearchTree2 = insertBSTRecursive(binarySearchTree2, test1[i]);
        }
        System.out.println(binarySearchTree2.searchIterative(1));
        System.out.println(binarySearchTree2.searchIterative(5));
        System.out.println(binarySearchTree2.searchIterative(8));
        System.out.println(binarySearchTree2.searchIterative(9));
        System.out.println(binarySearchTree2.searchIterative(10));
    }
}
