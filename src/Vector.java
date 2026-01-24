public class Vector<T> {

    private T[] data;
    private int count;

    @SuppressWarnings("unchecked")
    public Vector(int capacity) {
        data = (T[]) new Object[capacity];
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T get(int index) {
        if (index < 0 || index >= count) return null;
        return data[index];
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    public boolean contains(T value) {
        for (int i = 0; i < count; i++) {
            if (data[i].equals(value)) return true;
        }
        return false;
    }

    public void addFirst(T item) {
        for (int i = count; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = item;
        count++;
    }

    public void addLast(T item) {
        data[count++] = item;
    }

    public T getFirst() {
        return data[0];
    }

    public T getLast() {
        return data[count - 1];
    }

    public void removeLast() {
        data[count - 1] = null;
        count--;
    }

    public void removeFirst() {
        for (int i = 0; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
    }

    //this is used for getting the index of the current node when using graph
    public int indexOf(T value)
    {
        for (int i = 0; i < count; i++)
        {
            if (data[i].equals(value))
            {
                return i;
            }
        }
        return -1; // not found
    }

    @Override
    public String toString() {
        String ans = "[ ";
        for (int i = 0; i < count; i++) {
            ans += data[i] + " ";
        }
        ans += "]";
        return ans;
    }
}
