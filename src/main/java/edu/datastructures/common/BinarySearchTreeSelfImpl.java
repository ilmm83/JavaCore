package edu.datastructures.common;

public class BinarySearchTreeSelfImpl<T extends Comparable<T>> {

    // Tracks the number of nodes in this BST
    private int nodeCount = 0;

    // This BST is a rooted tree, so we maintain a handle on the root node
    private Node root = null;

    // Internal node containing node references
    // and the actual node data
    private class Node {
        T data;
        Node left, right;

        public Node(T data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return nodeCount;
    }

    // Add an element to this binary tree. Returns true
    // if we successfully perform an insertion
    public boolean add(T elem) {
        if (contains(elem)) return false;
        else {
            root = add(root, elem);
            nodeCount++;
            return true;
        }
    }

    // Remove a value from this binary tree, if it exists
    public boolean remove(T elem) {
        // Make sure the node we want to remove
        // actually exists before we remove it
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
        }
        return false;
    }

    private Node remove(Node node, T elem) {
        if (node == null) return null;
        int less = elem.compareTo(node.data);
        if (less < 0) node.left = remove(node.left, elem);
        else if (less > 0) node.right = remove(node.right, elem);
        else {
            // This is the case with only a right subtree or
            // no subtree at all. In this situation just
            // swap the node we wish to remove with its right child.
            if (node.right == null) {
                Node child = node.left;
                node.data = null;
                node = null;
                return child;
                // This is the case with only a left subtree or
                // no subtree at all. In this situation just
                // swap the node we wish to remove with its left child.
            } else if (node.left == null) {
                Node child = node.right;
                node.data = null;
                node = null;
                return child;
            }
            // When removing a node from a binary tree with two links the
            // successor of the node being removed can either be the largest
            // value in the left subtree or the smallest value in the right subtree. In this impl I have decided to find the
            // smallest value in the right subtree which can be found by
            // traveling as far left as possible in the right subtree.
            else {
                // Find the leftmost node in the right subtree
                Node tmp = digLeft(node.right);

                // Swap the data
                node.data = tmp.data;

                // Go into the right subtree and remove the leftmost node we
                // found and swapped data with. This prevents us from having
                // two nodes in our tree with the same value.
                node.right = remove(node.right, tmp.data);

                // If instead we wanted to find the largest node in the left
                // subtree as opposed to the smallest node in the right subtree
                // here is what we would do:
                // Node tmp = digRight(node.left);
                // node.data = tmp.data;
                // node.left = remove(node.left, tmp.data);
            }
        }
        return node;
    }

    // Helper method to find the leftmost node
    private Node digLeft(Node node) {
        Node cur = node;
        while (cur.left != null) cur = cur.left;
        return cur;
    }

    // Inversion
    private Node digRight(Node node) {
        Node cur = node;
        while (cur.right != null) cur = cur.right;
        return cur;
    }

    // returns true is the element exists in the tree
    private boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        // Base case: reached bottom, value not found
        if (node == null) return false;
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) return contains(node.left, elem);
        else if (cmp > 0) return contains(node.right, elem);
        else return true;
    }

    private Node add(Node node, T elem) {
        // Base case: Find a leaf node
        if (node == null) {
            node = new Node(elem, null, null);
        } else {
            // Place lower elements values in left subtree
            if (less(elem, node.data)) node.left = add(node.left, elem);
            else node.right = add(node.right, elem);
        }
        return node;
    }

    // Computes the height of the tree, O(n)
    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private boolean less(T elem, T root) {
        return elem.compareTo(root) < 0;
    }


    // This method returns an iterator for a given TreeTraversalOrder.
    // The ways in which you can traverse the tree are in four different ways:
    // preorder, inorder, postorder and levelorder.
    public java.util.Iterator<T> traverse(TreeTraversalOrder order) {
        return switch (order) {
            case PRE_ORDER -> preOrderTraversal();
            case IN_ORDER -> inOrderTraversal();
            case POST_ORDER -> postOrderTraversal();
            case LEVEL_ORDER -> levelOrderTraversal();
            default -> null;
        };
    }

    // Returns as iterator to traverse the tree in preorder
    private java.util.Iterator<T> preOrderTraversal() {

        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns as iterator to traverse the tree in order
    private java.util.Iterator<T> inOrderTraversal() {

        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack = new java.util.Stack<>();
        stack.push(root);

        return new java.util.Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {

                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();

                // Dig left
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                // Try moving down right once
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns as iterator to traverse the tree in post order
    private java.util.Iterator<T> postOrderTraversal() {
        final int expectedNodeCount = nodeCount;
        final java.util.Stack<Node> stack1 = new java.util.Stack<>();
        final java.util.Stack<Node> stack2 = new java.util.Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
        }
        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return stack2.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    // Returns as iterator to traverse the tree in level order
    private java.util.Iterator<T> levelOrderTraversal() {

        final int expectedNodeCount = nodeCount;
        final java.util.Queue<Node> queue = new java.util.LinkedList<>();
        queue.offer(root);

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) throw new java.util.ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}

enum TreeTraversalOrder {
    PRE_ORDER,
    IN_ORDER,
    POST_ORDER,
    LEVEL_ORDER
}
