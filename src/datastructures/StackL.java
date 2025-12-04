package datastructures;

public class StackL {

    private LinkedList data;

    public StackL() {
        data = new LinkedList();
    }

    public void push (Object o) {
        data.addFirst(o);
    }

    public Object pop() {
        return data.removeFirst();
    }

    public Object top(){
        return data.getFirst();
    }

    public int size(){
        return data.size();
    }

    public boolean isEmpty(){
        return data.size() == 0;
    }

    public String  toString(){
        return data.toString();
    }
}
