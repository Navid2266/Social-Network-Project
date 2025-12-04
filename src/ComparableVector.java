public class ComparableVector<T extends Comparable<T>> {

    private T[] data;
    private int count;

    @SuppressWarnings("unchecked")
    public ComparableVector(int capacity) {
        data = (T[]) new Comparable[capacity];
        count = 0;
    }

    public int size() {
        return count;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public T get(int index) {
        return data[index];
    }

    public void set(int index, T value) {
        data[index] = value;
    }

    public void addLast(T item) {
        data[count++] = item;
    }

    public void addFirst(T item) {
        for (int i = count; i > 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = item;
        count++;
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

    // SORTING feature for ads and posts
    public void addSorted(T item) {
        if (count == 0) {
            data[count++] = item;
            return;
        }

        int i = count - 1;

        // shift elements to the right until the correct spot is found
        while (i >= 0 && data[i].compareTo(item) > 0) {
            data[i + 1] = data[i];
            i--;
        }

        data[i + 1] = item;
        count++;
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
