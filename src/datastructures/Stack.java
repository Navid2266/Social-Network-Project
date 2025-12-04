package datastructures;

public class Stack {

    private Vector data;

    public Stack() {
        data = new Vector(100);
    }

    public void push(Object o) {
        data.addLast(o);
    }

    public Object pop() {
        if (data.isEmpty()) return null;
        Object temp =  data.getLast();
        data.removeLast();
        return temp;
    }

    public Object top() {
        return data.getLast();
    }

    public int size() {
        return data.size();
    }

    public boolean empty() {
        return data.isEmpty();
    }

    public String toString() {
        return data.toString();
    }
}

