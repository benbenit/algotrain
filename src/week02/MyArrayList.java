package week02;

/**
 * Java中支持动态扩容的数组: ArrayList
 */
public class MyArrayList {
    public int[] data;
    private int n = 10;
    private int count = 0;

    public MyArrayList() {
        data = new int[n];
    }

    public void add(int element) {
        if (count == n) {
            int[] newData = new int[2 * n];
            if (n >= 0) System.arraycopy(data, 0, newData, 0, n);

            n *= 2;
            data = newData;
        }
        data[count] = element;
        count++;
    }
}
