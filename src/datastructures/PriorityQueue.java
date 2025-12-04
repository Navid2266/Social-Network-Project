package datastructures;
import java.util.Comparator;

public class PriorityQueue
{
    private class PriorityPair implements Comparable {
        private Object element;
        private Object priority;

        public PriorityPair(Object element, Object priority) {
            this.element = element;
            this.priority = priority;
        }

        public int compareTo(Object o) {
            PriorityPair p2 = (PriorityPair) o;
            return ((Comparable) this.priority).compareTo(p2.priority);
        }
    }

    private SortedLinkedList data;
    public PriorityQueue()
    {
        data = new SortedLinkedList();
    }

    public void push(Object o, int priority)
    {
        PriorityPair pair = new PriorityPair(o, priority);
        data.addSorted(pair);
    }

    public Object pop()
    {
        PriorityPair pair = (PriorityPair) data.removeFirst();
        if ( pair == null) return null;
        return pair.element;
    }

    public Object top()
    {
        PriorityPair pair = (PriorityPair) data.getFirst();
        if ( pair == null) return null;
        return pair.priority;
    }
}
