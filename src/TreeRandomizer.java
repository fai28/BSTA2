/**
 * Monmoy Maahdie (30149094)
 * Fairooz Shafin (30149774)
 * Assignment 3 CPSC 331 Spring 2023
 */
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import java.util.List;
import java.util.Random;


public class TreeRandomizer {
    public static void main(String[] args) {
        List<Integer> treeSizes = Arrays.asList(500, 1000, 5000);
        String filename = "results.txt";
        
        try {
            FileWriter fileWriter = new FileWriter(filename, true);
            fileWriter.write("EXPERIMENT 10\n");
        for (Integer size : treeSizes) {
            double averageHeight;
            int totalHeight = 0;
            
            int doublesCounter = 0;
            int perfectCounter = 0;
            int completeCounter = 0;
            BST<Integer> bst = null;
            
            for (int i = 0; i < 100; i++) {
                bst = null;
                try {
                    bst = generateRandomTree(size);
                } catch (Exception e) {
                    System.out.println("An error occurred during tree generation: " + e.getMessage());
                    fileWriter.write("An error occurred during tree generation: " + e.getMessage() + "\n");
                    continue;
                }
                
                int height = bst.height();
                totalHeight += height;
                //System.out.println("Tree Number: " + (i+1) + ", Height: " + height);
                
                if (bst.isComplete()) {
                	completeCounter++;
                }
                if (bst.isPerfect()) {
                	perfectCounter++;
                }
                if (bst.hasDoubles()) {
                	doublesCounter++;
                }
            }

            averageHeight = (double) totalHeight / 100;
            
            System.out.println("Tree Size: " + size + " Nodes");
            System.out.println("Average height: " + averageHeight);
            System.out.println("Number of complete trees: " + completeCounter);
            System.out.println("Number of perfect trees: " + perfectCounter);
            System.out.println("Number of trees with duplicates: " + doublesCounter + "\n");
            
            
            fileWriter.write("Tree Size: " + size + " Nodes\n");
            fileWriter.write("Average height: " + averageHeight + "\n");
            fileWriter.write("Number of complete trees: " + completeCounter + "\n");
            fileWriter.write("Number of perfect trees: " + perfectCounter + "\n");
            fileWriter.write("Number of trees with duplicates: " + doublesCounter + "\n\n");

            
            bst.printBST(BST.INORDER);
        }
        fileWriter.close(); 
    } catch (IOException e) {
        System.out.println("An error occurred while writing to the file.");
        e.printStackTrace();
        }
    }
    
    public static BST<Integer> generateRandomTree(int size) {
        BST<Integer> bst = new BST<>();
        Random rand = new Random();

        for (int i = 0; i < size; i++) {
            int value = rand.nextInt(size*2000);
            bst.add(value);
        }

        return bst;
    }
}