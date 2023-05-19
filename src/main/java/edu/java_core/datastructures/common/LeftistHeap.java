package edu.java_core.datastructures.common;

public class LeftistHeap<T extends Comparable<T>> {

    private Node<T> root;

    private static class Node<T> {
        T data;
        int rank;
        Node<T> left, right;

        Node(T data) {
            this.data = data;
            rank = 1;
        }
    }

    public void insert(T data) {
        var node = new Node<T>(data);
        root = merge(root, node);
    }

    public T deleteMin() {
        if (root == null) throw new RuntimeException("Root is undefined");

        T data = root.data;
        root = merge(root.left, root.right);
        return data;
    }

    public T findMin() {
        if (root == null) throw new RuntimeException("Root is undefined");

        return root.data;
    }

    public Node<T> merge(Node<T> root, Node<T> node) {
        if (root == null) return node;
        if (node == null) return root;

        if (root.data.compareTo(node.data) > 0) {
            var temp = root;
            root = node;
            node = temp;
        }
        root.right = merge(root.right, node);

        if (root.left == null || root.right == null || root.left.rank < root.right.rank) {
            var temp = root.left;
            root.left = root.right;
            root.right = temp;
        }

        if (root.right == null) root.rank = 1;
        else root.rank = root.right.rank + 1;

        return root;
    }

}