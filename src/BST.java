import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implementation of a BST using linking
 *
 * Implementation is by contract and by reference
 *
 * @author Jalal Kawash, based on the implementation of Dale, Joyce, and Weems (Object-Oriented Data Structures in Java)
 *
 */


public class BST<T extends Comparable> implements BSTInterface<T> {
    protected Node<T> root;

    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;

    private LinkedQueue<T> inOrderQueue, preOrderQueue, postOrderQueue;

    public Node<T> getRoot() { // needed in TreePrinter
        return root;
    }

    /**
     * Constructor for objects of class BST
     */
    public BST() {
        root = null;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if BST is empty
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Precondition: None
     * Postcondition: returns false
     */
    public boolean isFull() {
        return false;
    }

    /**
     * Precondition: None
     * Postcondition: returns the number of elements inthe BST
     */
    public int size() {
        return recursiveSize(root);
    }

    int recursiveSize(Node<T> root) {
        if (root == null) return 0;
        else return recursiveSize(root.getLeft()) + recursiveSize(root.getRight()) + 1;
    }

    /**
     * Precondition: None
     * Postcondition: deletes all the elements in the BST and resests it to the initial condition
     */
    public void clear() {
        root = null;
    }

    /**
     * Precondition: None
     * Postcondition: resets the current index to the begining of the BST
     */
    public void reset(int order) {
        if (order == BST.INORDER) {
            inOrderQueue = new LinkedQueue<T>();
            inOrderTraversal(root);
        }

        if (order == BST.PREORDER) {
            preOrderQueue = new LinkedQueue<T>();
            preOrderTraversal(root);
        }

        if (order == BST.POSTORDER) {
            postOrderQueue = new LinkedQueue<T>();
            postOrderTraversal(root);
        }
    }

    void inOrderTraversal(Node<T> root) {
        if (root != null) {
            inOrderTraversal(root.getLeft());
            inOrderQueue.enqueue(root.getValue());
            inOrderTraversal(root.getRight());
        }
    }

    void preOrderTraversal(Node<T> root) {
        if (root != null) {
            preOrderQueue.enqueue(root.getValue());
            preOrderTraversal(root.getLeft());
            preOrderTraversal(root.getRight());
        }
    }

    void postOrderTraversal(Node<T> root) {
        if (root != null) {
            postOrderTraversal(root.getLeft());
            postOrderTraversal(root.getRight());
            postOrderQueue.enqueue(root.getValue());
        }
    }

    /**
     * Precondition: None
     * Postcondition: returns the next element in the list based on specified traversal order (inorder, preorder, postorder)
     */
    public T getNext(int order) {
        if (order == BST.INORDER) return inOrderQueue.dequeue();
        if (order == BST.PREORDER) return preOrderQueue.dequeue();
        if (order == BST.POSTORDER) return postOrderQueue.dequeue();
        return null;
    }

    /**
     * Precondition: None
     * Postcondition: Adds a new element to the list
     */
    public void add(T item) {
        root = recursiveAdd(item, root);
    }

    Node<T> recursiveAdd(T item, Node<T> root) {
        if (root == null) //insert here
        {
            root = new Node<T>();
            root.setValue(item);
        } else if (item.compareTo(root.getValue()) < 0) // got to left subtree
            root.setLeft(recursiveAdd(item, root.getLeft()));
        else root.setRight(recursiveAdd(item, root.getRight())); // go right

        return root;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if a given item is in the tree; otherwise returns false
     */
    public boolean contains(T item) {
        return recursiveContains(item, root);
    }

    boolean recursiveContains(T item, Node<T> root) {
        if (root == null) return false;
        else if (item.compareTo(root.getValue()) < 0) return recursiveContains(item, root.getLeft());
        else if (item.compareTo(root.getValue()) > 0) return recursiveContains(item, root.getRight());
        else return true;
    }

    /**
     * Precondition: Item to be removed is in the tree
     * Postcondition: removes an item from the BST
     */
    public void remove(T item) {
        root = recursiveRemove(item, root);
    }

    Node<T> recursiveRemove(T item, Node<T> root) {
        if (root == null) return null;
        else if (item.compareTo(root.getValue()) < 0) root.setLeft(recursiveRemove(item, root.getLeft()));
        else if (item.compareTo(root.getValue()) > 0) root.setRight(recursiveRemove(item, root.getRight()));
        else root = removeNode(root);
        return root;
    }

    Node<T> removeNode(Node<T> root) {
        T tmp;
        if (root.getLeft() == null) return root.getRight();
        else if (root.getRight() == null) return root.getLeft();
        else {
            tmp = predecessor(root.getLeft());
            root.setValue(tmp);
            root.setLeft(recursiveRemove(tmp, root.getLeft()));
            return root;
        }
    }

    T predecessor(Node<T> root)
    {
        while (root.getRight() != null) root = root.getRight();
        return root.getValue();
    }


    // NEW METHODS FROM HERE


    /**
     * Precondition: None
     * Postcondition: Returns the height of the tree
     */

    public int height() {
        return recursiveHeight(root);
    }

    private int recursiveHeight(Node<T> root) {
        if (root == null) return 0;
        else {
            int leftHeight = recursiveHeight(root.getLeft());
            int rightHeight = recursiveHeight(root.getRight());

            if (leftHeight > rightHeight)
                return leftHeight + 1;
            else
                return rightHeight + 1;
        }
    }

    /**
     * Precondition: None
     * Postcondition: Returns a reference to the parent of a node containing the given value
     */
    public Node<T> parent(T value) {
        return recParent(value, root);
    }

    private Node<T> recParent(T value, Node<T> root) {
        if (root == null) return null;
        if ((root.getLeft() != null && root.getLeft().getValue().equals(value)) || (root.getRight() != null && root.getRight().getValue().equals(value)))
            return root;
        Node<T> leftParent = recParent(value, root.getLeft());
        if (leftParent != null) return leftParent;
        Node<T> rightParent = recParent(value, root.getRight());
        if (rightParent != null) return rightParent;
        return null;
    }

    /**
     * Precondition: None
     * Postcondition: Returns the level of the node containing the given value. If the value does not exist, returns -1.
     */
    public int level(T value) {
        int initialLevel = -1;
        return recursiveLevel(value, root, 0, initialLevel);
    }


    private int recursiveLevel(T value, Node<T> root, int level, int maxLevel) {
        if (root == null)
            return maxLevel;
        
        if (root.getValue().equals(value))
            maxLevel = Math.max(level, maxLevel);

        int leftMaxLevel = recursiveLevel(value, root.getLeft(), level + 1, maxLevel);
        int rightMaxLevel = recursiveLevel(value, root.getRight(), level + 1, maxLevel);
        
        return Math.max(leftMaxLevel, rightMaxLevel);
    }


    private void resetVisited(Node<T> root) {
        if (root == null) {
            return;
        }
        root.setVisited(false);
        resetVisited(root.getLeft());
        resetVisited(root.getRight());
    }

    /**
     * Precondition: The binary tree is not null.
     * Postcondition: Returns true if the binary tree is complete, false otherwise.
     *                A binary tree is complete if the index of any node is less than the total number of nodes in the tree.
     *                It recursively checks the completeness of the left and right subtrees.
     *                The index of each node is determined using the formula: 2 * index + 1 (for the left child) and 2 * index + 2 (for the right child).
     */
    public boolean isComplete() {
        int totalNodes = size();
        return recIsComplete(root, 0, totalNodes);
    }

    /**
     * Precondition: The binary tree and its parameters are not null.
     * Postcondition: Returns true if the binary tree rooted at the given node is complete, false otherwise.
     *                It checks if the index of the current node is greater than or equal to the total number of nodes.
     *                It recursively checks the completeness of the left and right subtrees.
     *                The index of each node is determined using the formula: 2 * index + 1 (for the left child) and 2 * index + 2 (for the right child).
     */
    private boolean recIsComplete(Node<T> root, int index, int totalNodes) {
        if (root == null) {
            return true;
        }

        if (index >= totalNodes) {
            return false;
        }

        return recIsComplete(root.getLeft(), 2 * index + 1, totalNodes) &&
                recIsComplete(root.getRight(), 2 * index + 2, totalNodes);
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if the tree is perfect
     *                A perfect binary tree is a complete binary tree where all the leaf nodes are at the same level
     */
    public boolean isPerfect() {
        return recIsPerfect(root, 0, level(root.getValue()));
    }

    private boolean recIsPerfect(Node<T> root, int currentLevel, int maxLevel) {
        if (root == null) {
            return true;
        }

        if (root.getLeft() == null && root.getRight() == null) {
            // Leaf node found, check if it is at the same level as the maximum level
            return currentLevel == maxLevel;
        }

        if (root.getLeft() != null && root.getRight() != null) {
            // Both left and right child exist, recursively check their subtrees
            return recIsPerfect(root.getLeft(), currentLevel + 1, maxLevel) &&
                    recIsPerfect(root.getRight(), currentLevel + 1, maxLevel);
        }

        // If one child exists and the other doesn't, the tree is not perfect
        return false;
    }


    /**
     * Precondition: None
     * Postcondition: Returns true if the tree has duplicate values
     * Assumes that the values are drawn from a fixed set such as 0 to m-1 for some value m
     * 
     */
    public boolean hasDoubles() {
        HashSet<T> visited = new HashSet<>();
        return recursiveHasDoubles(root, visited);
    }

    private boolean recursiveHasDoubles(Node<T> root, HashSet<T> visited) {
        if (root == null) return false;
        if (visited.contains(root.getValue())) return true;
        visited.add(root.getValue());
        return recursiveHasDoubles(root.getLeft(), visited) || recursiveHasDoubles(root.getRight(), visited);
    }

    
   




    /**
     * Precondition: None
     * Postcondition: prints the contents of the list nased on the provided traversal order type
     */
    public void printBST(int order)
    {
        int s = size();
        if (order == INORDER)
        {
            reset(INORDER);
            inOrderTraversal(root);
            System.out.println("Get items inorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.INORDER) + " ");
            System.out.println();
        }

        if (order == PREORDER)
        {
            reset(PREORDER);
            preOrderTraversal(root);
            System.out.println("Get items preorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.PREORDER) + " ");
            System.out.println();
        }

        if (order == POSTORDER)
        {
            reset(POSTORDER);
            postOrderTraversal(root);
            System.out.println("Get items postorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(BST.POSTORDER) + " ");
            System.out.println();
        }

    }

}



