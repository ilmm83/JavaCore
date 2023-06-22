package edu.datastructures.common;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Stack;

public class AVLTreeRecursive<T extends Comparable<T>> implements Iterable<T> {

    class Node {
        // Balance Factor and height of this node in the tree
        int bf, height;
        // Node data
        T data;
        // Left and right children of this node
        Node left, right;

        public Node(T data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

    // Node root
    private Node root;

    // Tracks the number of nodes inside the tree.
    private int nodeCount = 0;

    // The height of a rooted tree is the number of edges between the tree's
    // root and its furthest leaf. This means that a tree containing a single
    // node has a height of 0.
    public int height() {
        if (root == null) return 0;
        return root.height;
    }

    // Returns the number of nodes in the tree.
    public int size() {
        return nodeCount;
    }

    // Returns whether the tree is empty.
    public boolean isEmpty() {
        return size() == 0;
    }

    // Prints a visual representation of the tree to the console.
//    public void display() {
//        TreePrinter.print(root);
//    }

    // Return true/false depending on whether a value exists in the tree.
    public boolean contains(T data) {
        return contains(root, data);
    }

    // Helper method for search a node
    private boolean contains(Node node, T data) {
        if (node == null) return false;
        int cmp = data.compareTo(node.data);
        if (cmp < 0) return contains(node.left, data);
        else if (cmp > 0) return contains(node.right, data);
        return true;
    }

    // Insert/add a value to the AVL tree. The value must not be null, O(log(n))
    public boolean insert(T value) {
        if (value == null) return false;
        if (!contains(root, value)) {
            root = insert(root, value);
            nodeCount++;
            return true;
        }
        return false;
    }

    // Insert a value inside the AVL tree
    private Node insert(Node node, T value) {
        if (node == null) return new Node(value);
        int cmp = value.compareTo(node.data);
        if (cmp < 0) node.left = insert(node.left, value);
        else node.right = insert(node.right, value);
        // Update balance factor and height values.
        update(node);
        // Re-balance tree
        return balance(node);
    }

    // Re-balance a node if its balance factor is +2 or -2.
    private Node balance(Node node) {
        // Left heavy subtree
        if (node.bf == -2) {
            // LL case
            if (node.left.bf <= 0) return LLCase(node);
                // LR case
            else return LRCase(node);
            // Right heavy subtree.
        } else if (node.bf == +2) {
            // RR case
            if (node.right.bf >= 0) return RRCase(node);
            // RL case
        } else return RLCase(node);
        // Node either has a balance factor of 0, +1, -1 which is fine
        return node;
    }


    // Case methods
    private Node RLCase(Node node) {
        node.right = rightRotation(node.right);
        return RRCase(node);
    }

    private Node RRCase(Node node) {
        return leftRotation(node);
    }

    private Node LRCase(Node node) {
        node.left = leftRotation(node.left);
        return LLCase(node);
    }

    private Node LLCase(Node node) {
        return rightRotation(node);
    }


    // Rotation methods
    private Node leftRotation(Node node) {
        Node newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        update(node);
        update(newParent);
        return newParent;
    }

    private Node rightRotation(Node node) {
        Node newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        update(node);
        update(newParent);
        return newParent;
    }


    // Update a node's height and balance factor.
    private void update(Node node) {
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;
        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.bf = rightNodeHeight - leftNodeHeight;
    }

    // Remove a value from this binary tree if it exists, O(log(n))
    public boolean remove(T elem) {
        if (elem == null) return false;
        if (contains(root, elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    // Removes a value form AVL tree
    private Node remove(Node node, T elem) {
        if (node == null) return null;
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) node.left = remove(node.left, elem);
        else if (cmp > 0) node.right = remove(node.right, elem);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                // Choose to remove from left subtree
                if (node.left.height > node.right.height) {
                    // Swap the value of the successor into the node.
                    T successorValue = findMax(node.left);
                    node.data = successorValue;
                    // Find the largest node in the left subtree.
                    node.left = remove(node.left, successorValue);
                } else {
                    T successorValue = findMin(node.right);
                    node.data = successorValue;
                    node.right = remove(node.right, successorValue);
                }
            }
        }
        update(node);
        return balance(node);
    }

    // Helper method to find the rightmost node (which has the largest value)
    private T findMax(Node node) {
        while (node.right != null) node = node.right;
        return node.data;
    }

    // Helper method to find the leftmost node (which has the lowest value)
    private T findMin(Node node) {
        while (node.left != null) node = node.left;
        return node.data;
    }

    @Override
    public Iterator<T> iterator() {
        final int expectedCountOfNodes = nodeCount;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);
        return new Iterator<T>() {
            Node t = root;
            @Override
            public boolean hasNext() {
                if (expectedCountOfNodes != nodeCount) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedCountOfNodes != nodeCount) throw new ConcurrentModificationException();
                while (t != null && t.left != null) {
                    stack.push(t.left);
                    t = t.left;
                }
                Node node = stack.pop();
                while (t != null && t.right != null) {
                    stack.push(t.right);
                    t = t.right;
                }
                return node.data;
            }
        };
    }

    // Make sure all left child nodes are smaller in value than their parent and
    // make sure all right child nodes are greater in value than their parent.
    // (Used only for testing)
    public boolean validateBSTInvarient(Node node) {
        if (node == null) return true;
        T val = node.data;
        boolean isValid = true;
        if (node.left != null) isValid = isValid && node.left.data.compareTo(val) < 0;
        if (node.right != null) isValid = isValid && node.right.data.compareTo(val) > 0;
        return isValid && validateBSTInvarient(node.left) && validateBSTInvarient(node.right);
    }
}
