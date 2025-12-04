package datastructures;

public class SortedLinkedList {

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

    public SortedLinkedList() {
        head = null;
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

    public void addSorted(Object o) {
        Comparable value = (Comparable) o;

        if (head == null) {
            head = new ListElement(o, null);
            return;
        }

        if (value.compareTo(head.first()) < 0) {
            head = new ListElement(o, head);
            return;
        }

        ListElement current = head;
        while (current.rest() != null && value.compareTo(current.rest().first()) > 0) {
            current = current.rest();
        }

        current.setRest(new ListElement(o, current.rest()));
    }

    public Object removeLast() {
        if  (head == null) return null;

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

    public Object search(Object target) {
        ListElement d = head;
        while (d != null) {
            if (d.first().equals(target)) {
                return d.first();
            }
            d = d.rest();
        }
        return null;
    }

}

/*    public ListElement fropple(){
        ListElement d = head;
        for (int i = 0; i < size()-1; i++){
            Object first = d.first();
            d = d.rest();
            i++;
        }
    }
}
*/
