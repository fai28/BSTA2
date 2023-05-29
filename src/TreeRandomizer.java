import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class TreeRandomizer {
    public static void main(String[] args) {
        List<Integer> treeSizes = Arrays.asList(500, 1000, 5000);
        
        for (Integer size : treeSizes) {
            double averageHeight;
            int totalHeight = 0;
            
            int doublesCounter = 0;
            int perfectCounter = 0;
            int completeCounter = 0;
            
            for (int i = 0; i < 100; i++) {
                BST<Integer> bst = null;
                try {
                    bst = generateRandomTree(size);
                } catch (Exception e) {
                    System.out.println("An error occurred during tree generation: " + e.getMessage());
                    continue;
                }
                totalHeight += bst.height();
                
                if (bst.isComplete()) completeCounter++;
                if (bst.isPerfect()) perfectCounter++;
                if (bst.hasDoubles()) doublesCounter++;
            }

            averageHeight = (double) totalHeight / 100;
            System.out.println("Tree Size: " + size + ":");
            System.out.println("Average height: " + averageHeight);
            System.out.println("Number of trees with doubles: " + doublesCounter);
            System.out.println("Number of complete trees: " + completeCounter);
            System.out.println("Number of perfect trees: " + perfectCounter);
        }
    }
    
    public static BST<Integer> generateRandomTree(int size) {
        BST<Integer> bst = new BST<>();
        Random rand = new Random();
        Set<Integer> values = new HashSet<>();

        while (values.size() < size) {
            int value = rand.nextInt(size * 2);
            if (values.add(value)) {
                bst.add(value);
            }
        }

        return bst;
    }
}