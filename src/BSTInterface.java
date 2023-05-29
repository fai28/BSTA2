
/**
 * Bibary Search Tree ADT operations
 *
 * @author Jalal Kawash
 */

public interface BSTInterface <T extends Comparable>
{
    public boolean isEmpty();
    public boolean isFull();
    public int size();
    public void clear();
    public void reset(int order);
    public T getNext(int order);
    public void add (T element);
    public boolean contains(T element);
    public void remove(T element);
}
