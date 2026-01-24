public class Queue {

    private Vector data;

    public Queue() {
        data = new Vector(100);
    }

    public void push(Object o) {
        data.addLast(o);
    }

    public Object pop() {
        if (data.isEmpty()) return null;
        Object temp = data.getFirst();
        data.removeFirst();
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
}