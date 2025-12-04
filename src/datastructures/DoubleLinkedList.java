package datastructures;

public class DoubleLinkedList
{
    private class DoubleLinkedListElement
    {
        private Object data;
        private DoubleLinkedListElement nextElement;
        private DoubleLinkedListElement previousElement;
        public DoubleLinkedListElement(Object v, DoubleLinkedListElement next, DoubleLinkedListElement previous)
        {
            data = v;
            nextElement = next;
            previousElement = previous;
            if(nextElement != null) nextElement.previousElement = this;
            if(previousElement != null) previousElement.nextElement = this;
        }

        public DoubleLinkedListElement(Object v)
        {
            this(v,null,null);
        }
        public DoubleLinkedListElement previous()
        {
            return previousElement;
        }
        public Object value()
        {
            return data;
        }
        public DoubleLinkedListElement next()
        {
            return nextElement;
        }
        public void setNext(DoubleLinkedListElement value)
        {
            nextElement = value;
        }
        public void setPrevious(DoubleLinkedListElement value)
        {
            previousElement = value;
        }
    }


    private int count;
    private DoubleLinkedListElement head;
    private DoubleLinkedListElement tail;
    public DoubleLinkedList()
    {
        head = null;
        tail = null;
        count = 0;
    }
    public Object getFirst()
    {
        return head.value();
    }
    public Object getLast()
    {
        return tail.value();
    }
    public int size()
    {
        return count;
    }
    public void AddFirst(Object value)
    {
        head = new DoubleLinkedListElement(value,head,null);
        if(tail == null) tail = head;
        count++;
    }
    public void AddLast(Object value)
    {
        tail = new DoubleLinkedListElement(value,null,tail);
        if(head == null) head = tail;
        count++;
    }

    public String toString()
    {
        String s = "(";
        DoubleLinkedListElement d = head;
        while(d != null)
        {
            s += d.value().toString();
            s += " ";
            d = d.next();
        }
        s += ")";
        return s;
    }

    public void printReverse(){
        String s = "(";
        DoubleLinkedListElement d = tail;
        while(d != null){
            s += d.value().toString();
            s += " ";
            d = d.previous();
        }
        s += ")";
        System.out.println(s);
    }

    public void removeFirst()
    {
        head = head.next();
        if(head == null) tail=null;
        else head.setPrevious(null);
        count--;
    }

    public  void removeLast()
    {
        tail = tail.previous();
        if(tail == null) head=null;
        else tail.setNext(null);
        count--;
    }

    public void reverse() {
        DoubleLinkedListElement current = head;
        DoubleLinkedListElement temp = null;

        // Traverse the list and swap previous/next for each element
        while (current != null) {
            // Swap pointers
            temp = current.previous();
            current.setPrevious(current.next());
            current.setNext(temp);

            // Move to next element (which is old next, now in previous)
            current = current.previous();
        }

        // After loop, temp holds the last swapped element’s previous
        // So head and tail must be swapped
        if (temp != null) {
            head = temp.previous();  // new head
        }

        // Swap head and tail
        DoubleLinkedListElement tempHead = head;
        head = tail;
        tail = tempHead;
    }

}