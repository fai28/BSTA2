
/**
 * A tester class for the BSTTester ADT
 *
 * @author Jalal Kawash
 */

import java.util.Random;

public class BSTTester
{
    public static void main(String[] args)
    {
        System.out.println("Creating a new tree of integers");
        BST<Integer> t = new BST<Integer>();

        Random rnd = new Random();
        for (int i = 0; i < 10; i++)
        {
            t.add(rnd.nextInt(100));
        }
        t.printBST(BST.INORDER);
        t.printBST(BST.PREORDER);
        t.printBST(BST.POSTORDER);

        if (t.contains(12)) System.out.println("12 is in the tree");
        else System.out.println("12 is in not the tree");

        if (t.contains(8)) System.out.println("12 is in the tree");
        else System.out.println("8 is in not the tree");

        System.out.println("The tree has " + t.size() + " elements");
        t.add(200);
        System.out.println("The tree has " + t.size() + " elements");
        t.printBST(BST.INORDER);

        for (int i = 0; i < 30; i++)
        {
            int j = rnd.nextInt(100);
            if (t.contains(j))
            {
                System.out.println("Removing " + j + " from the tree");
                t.remove(j);
                t.printBST(BST.INORDER);
                System.out.println("The tree has " + t.size() + " elements");
            }
        }

        // Tests for the added methods
        boolean isComplete = t.isComplete();
        System.out.println("Is this a complete tree? " + isComplete);

        boolean isPerfect = t.isPerfect();
        System.out.println("Is this a perfect tree? " + isPerfect);

        boolean hasDoubles = t.hasDoubles();
        System.out.println("Does the tree have duplicate values? " + hasDoubles);


        int size = t.size();
        t.reset(BST.INORDER);
        System.out.println("Get items inorder: ");
        for (int i = 0; i < size; i++) System.out.print(t.getNext(BST.INORDER) + " ");
        System.out.println();
        t.reset(BST.PREORDER);
        System.out.println("Get items preorder: ");
        for (int i = 0; i < size; i++) System.out.print(t.getNext(BST.PREORDER) + " ");
        System.out.println();
        t.reset(BST.POSTORDER);
        System.out.println("Get items postorder: ");
        for (int i = 0; i < size; i++) System.out.print(t.getNext(BST.POSTORDER) + " ");
        System.out.println();
    }

}
