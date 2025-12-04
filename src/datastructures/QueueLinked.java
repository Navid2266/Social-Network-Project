package datastructures;

public class QueueLinked {

    private LinkedList data;

    public QueueLinked() {
        data = new LinkedList();
    }

    public void push(Object o) {
        data.addLast(o);
    }

    public Object pop() {
        return data.removeFirst();
    }

    public Object top() {
        return data.getFirst();
    }

    public int size() {
        return data.size();
    }

    public boolean empty() {
        return data.size() == 0;
    }

    public String toString() {
        return data.toString();
    }
}
