package datastructures;

public class LinkedList {

    private class ListElement {

        private Object el1;
        private ListElement el2;

        public ListElement(Object el, ListElement nextElement) {
            el1 = el;
            el2 = nextElement;
        }

        public ListElement(Object el) {
            this(el, null);
        }

        public Object first() {
            return el1;
        }

        public ListElement rest() {
            return el2;
        }

        public void setFirst(Object value) {
            el1 = value;
        }

        public void setRest(ListElement value) {
            el2 = value;
        }
    }

    // in Java all non-primitive types are just a reference (pointer) so head is just a pointer to a ListElement
    private ListElement head;

    public LinkedList() {
        head = null;
    }

    public void addFirst(Object o) {
        head = new ListElement(o, head);
    }

    public Object getFirst() {
        return head.first();
    }

    public Object removeFirst() {
        if (head == null) return null;
        Object temp = head.first();
        head = head.rest();
        return temp;
    }

    public Object get(int n) {
        ListElement d = head;
        while (n > 0) {
            d = d.rest();
            n--;
        }
        return d.first();
    }

    public String toString() {
        String s = "(";
        ListElement d = head;
        while (d != null) {
            s += d.first().toString();
            s += " ";
            d = d.rest();
        }
        s += ")";
        return s;
    }

    public int size() {
        ListElement d = head;
        int count = 0;
        while (d != null) {
            d = d.rest();
            count++;
        }
        return count;
    }

    public void add(int n, Object o) {
        ListElement d = head;
        while (n > 2) {
            d = d.rest();
            n--;
        }
        ListElement temp = d.rest();
        ListElement temp2 = new ListElement(o, temp);
        d.setRest(temp2);
        temp2.setRest(temp);
    }

    public void set(int n, Object o) {
        ListElement d = head;
        while (n > 1) {
            d = d.rest();
            n--;
        }
        d.setFirst(o);
    }

    public Object getLast() {
        ListElement d = head;
        if (d == null) {
            return null;
        }
        while (d.rest() != null) {
            d = d.rest();
        }
        return d.first();
    }

    public void addLast(Object o) {
        if (head == null) {
            head = new ListElement(o);
            return;
        }

        ListElement current = head;
        while (current.rest() != null) {
            current = current.rest();
        }

        current.setRest(new ListElement(o));
    }

    public Object removeLast() {
        if (head.rest() == null) {
            Object value = head.first();
            head = null;
            return value;
        }

        ListElement temp = head;
        while (temp.rest().rest() != null) {
            temp = temp.rest();
        }

        Object value = temp.rest().first();
        temp.setRest(null);
        return value;
    }

    public Object search(int n) {
        ListElement d = head;

        if (d.rest() == null) {
            if (d.first().equals(n)) {
                return d.first();
            }
        }

        while (d.rest() != null) {
            if (d.first().equals(n)) {
                return d.first();
            }
            d = d.rest();
        }
        return null;
    }
}