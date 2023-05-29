
/**
 * An implementation of a BST using arrays
 *
 * @author Jalal Kawash
 *
 *
 */

import java.lang.reflect.Array;

public class ArrayBST<T extends Comparable> implements BSTInterface<T>{
    private T[] tree;
    private int size;

    public static final int INORDER = 0;
    public static final int PREORDER = 1;
    public static final int POSTORDER = 2;

    private LinkedQueue<T> inOrderQueue, preOrderQueue, postOrderQueue;


    /**
     * Constructor for objects of class ArrayBST
     */
    public ArrayBST(Class<T> clazz, int maxSize){
        tree = (T[]) Array.newInstance(clazz, maxSize);
        size = 0;
    }

    /**
     * Precondition: None
     * Postcondition: returns true if tree is empty
     */
    public boolean isEmpty(){
        return (size == 0);
    }

    /**
     * Precondition: None
     * Postcondition: returns true if tree is full
     */
    public boolean isFull(){
        return false;
    }

    /**
     * Precondition: None
     * Postcondition: returns the number of elements inthe BST
     */
    public int size(){
        return size;
    }

    /**
     * Precondition: None
     * Postcondition: deletes all the elements in the BST and resests it to the initial condition
     */
    public void clear(){
        size = 0;
        for (int i = 0; i < tree.length; i++)
            tree[i] = null;
    }

    /**
     * Precondition: None
     * Postcondition: item is added to the BST
     */
    public void add(T item){
        int i = 0;
        while (i < tree.length && tree[i] != null) {
            if (item.compareTo(tree[i]) < 0) i = 2*i + 1;
            else i = 2*i + 2;
        }
        if (i >= tree.length){
            enlargeArray();
            add(item);
        }
        else {
            tree[i] = item;
            size++;
        }
    }

    private void enlargeArray() {
        T[] newTree = (T[]) Array.newInstance(tree.getClass().getComponentType(), tree.length*2);
        for (int i = 0; i < tree.length; i++)
            newTree[i] = tree[i];
        tree = newTree;
    }

    /**
     * Precondition: none
     * Postcondition: first occurence of item is removed from the BST
     */
    public void remove(T item){

    }


    /**
     * Precondition: None
     * Postcondition: item is added to the BST
     */
    public boolean contains(T item){
        int i = 0;
        while (tree[i] == null || i < tree.length) {
            if (tree[i] != null && item.compareTo(tree[i]) == 0) return true;
            if (tree[i] != null && item.compareTo(tree[i]) < 0) i = 2*i + 1;
            else i = 2*i + 2;
        }
        return false;
    }

    /**
     * Precondition: None
     * Postcondition: resets the current index to the begining of the BST
     */
    public void reset(int order) {
        if (order == INORDER)
        {
            inOrderQueue = new LinkedQueue<T>();
            inOrderTraversal(0);
        }

        if (order == PREORDER)
        {
            preOrderQueue = new LinkedQueue<T>();
            preOrderTraversal(0);
        }

        if (order == POSTORDER)
        {
            postOrderQueue = new LinkedQueue<T>();
            postOrderTraversal(0);
        }
    }

    private void inOrderTraversal(int index){
        if (index < tree.length && tree[index] != null)
        {
            inOrderTraversal(2*index+1);
            inOrderQueue.enqueue(tree[index]);
            inOrderTraversal(2*index+2);
        }
    }

    private void preOrderTraversal(int index){
        if (index < tree.length && tree[index] != null)
        {
            preOrderQueue.enqueue(tree[index]);
            preOrderTraversal(2*index+1);
            preOrderTraversal(2*index+2);
        }
    }

    private void postOrderTraversal(int index){
        if (index < tree.length && tree[index] != null)
        {
            postOrderTraversal(2*index+1);
            postOrderTraversal(2*index+2);
            postOrderQueue.enqueue(tree[index]);
        }
    }

    /**
     * Precondition: None
     * Postcondition: returns the next element in the list based on specified traversal order (inorder, preorder, postorder)
     */
    public T getNext(int order) {
        if (order == INORDER) return inOrderQueue.dequeue();
        if (order == PREORDER) return preOrderQueue.dequeue();
        if (order == POSTORDER) return postOrderQueue.dequeue();
        return null;
    }

    /**
     * Precondition: None
     * Postcondition: prints the contents of the list nased on the provided traversal order type
     */
    public void printBST(int order){
        int s = size;
        if (order == INORDER)
        {
            reset(INORDER);
            inOrderTraversal(0);
            System.out.println("Get items inorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(INORDER) + " ");
            System.out.println();
        }

        if (order == PREORDER)
        {
            reset(PREORDER);
            preOrderTraversal(0);
            System.out.println("Get items preorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(PREORDER) + " ");
            System.out.println();
        }

        if (order == POSTORDER)
        {
            reset(POSTORDER);
            postOrderTraversal(0);
            System.out.println("Get items postorder: ");
            for (int i = 0; i < s; i++) System.out.print(getNext(POSTORDER) + " ");
            System.out.println();
        }

    }
}
