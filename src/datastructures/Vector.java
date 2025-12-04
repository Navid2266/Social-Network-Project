package datastructures;

public class Vector
{
    private Object data[];
    private int count;

    public Vector(int capacity)
    {
        data = new Object[capacity];
        count = 0;}

    public int size()
    {
        return count;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public Object get(int index)
    {
        return data[index];
    }

    public void set(int index, Object obj)
    {
        data[index] = obj;
    }

    public boolean contains(Object obj)
    {
        for(int i=0;i<count;i++)
        {
            if(data[i] == obj) return true;
        }
        return false;
    }

    public void addFirst(Object item)
    {
        for (int i = count; i >= 0; i--) {
            data[i] = data[i - 1];
        }
        data[0] = item;
        count++;
    }

    public void addLast(Object o)
    {
        data[count] = o;
        count++;
    }

    public Object getFirst()
    {
        return data[0];
    }

    public Object getLast()
    {
        return data[count - 1];
    }

    public boolean binarySearch(Object key)
    {
    int start = 0;
    int end = count - 1;
    while(start <= end)
    {int middle = (start + end + 1) / 2;
        if((int)key < (int)data[middle]) end = middle -1;
            else if((int)key > (int)data[middle]) start = middle + 1;
    else return true;
    }
    return false;
    }

    public void removeLast()
    {
        data[count - 1] = null;
        count--;
    }

    @Override
    public String toString()
    {
        String ans = "[ ";
        for(int i=0;i<count;i++)
        {
            ans += data[i] + " ";
        }
        ans += "]";
        return ans;
    }

    public void removeFirst()
    {
        for (int i = 0; i < count-1; i++ ) {
            data[i] = data[i+1];
        }
        count--;
    }

    public void reverse(){
        for (int i = 0; i <= (count-1)/2; i++)
        {
            Object temp = data[i];
            data[i] = data[count-1-i];
            data[count-1-i] = temp;
        }
    }

    public Vector repeat(){
        Vector v = new Vector(2*count);
        for (int i=0; i<count;i++){
            v.addLast(data[i]); //we cannot use set because the count is set to 0 and we will get []
            v.addLast(data[i]);

        }
        return v;
    }

    public Vector interleave (Vector vector2){
        Vector  v = new Vector(3*count);
        for (int i=0; i<count;i++){
            v.addLast(data[i]);
            v.addLast(vector2.get(i));
        }
        return v;
    }
}

