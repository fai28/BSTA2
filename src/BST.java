
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


    /**
     * Precondition: None
     * Postcondition: Returns the height of the tree
     */
    public int height() {
        // TODO: Add implementation here
        // Steps:
        // 1. Call a recursive helper method to calculate the height of the tree
        // 2. The height of a tree is the maximum height between the left and right subtrees of each node
        // 3. The height of an empty tree is 0
        // 4. Return the calculated height
        return recursiveHeight(root);
    }

    private int recursiveHeight(Node<T> root) {
        // TODO: Implement the recursive helper method to calculate the height of the tree
        // ...
        return 0;
    }

    /**
     * Precondition: None
     * Postcondition: Returns a reference to the parent of a node containing the given value
     */
    public Node<T> parent(T value) {
        // TODO: Add implementation here
        // Steps:
        // 1. Call a recursive helper method to find the parent of the node containing the given value
        // 2. If the node is found, return its parent node
        // 3. If the node is not found or it is the root node (which has no parent), return null
        return recursiveParent(value, root);
    }

    private Node<T> recursiveParent(T value, Node<T> root) {
        // TODO: Implement the recursive helper method to find the parent of the node containing the given value
        // ...
        return root;
    }

    /**
     * Precondition: None
     * Postcondition: Returns the level of the node containing the given value
     * If there are multiple nodes with the same value, it returns the highest level
     */
    public int level(T value) {
        // TODO: Add implementation here
        // Steps:
        // 1. Call a recursive helper method to find the level of the node containing the given value
        // 2. Keep track of the current level while traversing the tree
        // 3. If the node is found, update the level if it is higher than the previous highest level
        // 4. Return the highest level found
        return recursiveLevel(value, root, 0);
    }

    private int recursiveLevel(T value, Node<T> root, int level) {
        // TODO: Implement the recursive helper method to find the level of the node containing the given value
        // ...
        return level;
    }

    private boolean recIsComplete(Node<T> root, int index) {
        // TODO: Implement the recursive helper method to check if the tree is complete
        // ...
        return false;
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if the tree is complete
     * Must call a recursive method recIsComplete(root, index)
     */
    public boolean isComplete() {
        // TODO: Add implementation here
        // Steps:
        // 1. Call a recursive helper method to check if the tree is complete
        // 2. The helper method should take the current node and its index
        // 3. The index represents the position of the current node in a complete binary tree
        // 4. In a complete binary tree, the index of any node cannot be greater than or equal to the number of nodes n
        // 5. If the tree is complete, return true; otherwise, return false
        return recIsComplete(root, 0);
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if the tree is perfect
     * A perfect binary tree is a complete binary tree where all the leaf nodes are at the same level
     */
    public boolean isPerfect() {
        // TODO: Add implementation here
        // Steps:
        // 1. Calculate the height of the tree using the height() method
        // 2. Traverse the tree to check if all the leaf nodes are at the height level
        // 3. If all leaf nodes are at the height level, return true; otherwise, return false
        return recursiveIsPerfect(root, 0, height());
    }

    private boolean recursiveIsPerfect(Node<T> root, int level, int height) {
        // TODO: Implement the recursive helper method to check if the tree is perfect
        // ...
        return false;
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if the tree has duplicate values
     * Assumes that the values are drawn from a fixed set such as 0 to m-1 for some value m
     * Capitalizes on the "visited" field of the Node class
     */
    public boolean hasDoubles() {
        // TODO: Add implementation here
        // Steps:
        // 1. Traverse the tree and mark the visited nodes by setting the "visited" field to true
        // 2. If a node is visited again (i.e., its "visited" field is already true), return true
        // 3. If no duplicates are found, return false
        return recursiveHasDoubles(root);
    }

    private boolean recursiveHasDoubles(Node<T> root) {
        // TODO: Implement the recursive helper method to check if the tree has duplicate values
        // ...
        return false;
    }

    /**
     * Precondition: None
     * Postcondition: Returns true if the tree is complete
     *                Must call a recursive method recIsComplete(root, index)
     */

    T predecessor(Node<T> root)
    {
        while (root.getRight() != null) root = root.getRight();
        return root.getValue();
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

