/**
 * The implementation of the ArrayList ADT.
 * It is not meant to be a complete implementation of an ArrayList ADT.
 */
package structure;

/**
 * Implements a customized version of the ArrayList ADT.
 * Note: The clone method below only make a shallow copy of
 * this MyArrayList object.
 *
 * @author X. David Zheng
 * @version 2.0
 */
public class MyArrayList implements Cloneable {
    private static final int INITIAL_CAPACITY = 100;
    private Object[] data;
    private int size;

    /**
     * Default constructor
     */
    public MyArrayList() {
        data = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Appends the element at the end of the ArrayList.
     *
     * @param    element    The element to be appended.
     */
    public void append(Object element) {
        if (size == data.length) expand();

        data[size++] = element;
    }

    /*
     * Doubles the current capacity.
     */
    private void expand() {
        Object[] temp = new Object[data.length * 2];

        for (int i = 0; i < size; ++i) {
            temp[i] = data[i];
        }

        data = temp;
    }

    /**
     * Make the ArrayList collection empty.
     */
    public void clear() {
        for (int i = 0; i < size; ++i)
            data[i] = null;
        size = 0;
    }

    /**
     * Check whether the ArrayList contains the element.
     *
     * @return true if the element is in the ArrayList, false otherwise.
     * @param    element    The element to be checked upon.
     */
    public boolean contains(Object element) {
        for (int i = 0; i < size; ++i)
            if (element.equals(data[i]))
                return true;
        return false;
    }

    /**
     * Access the element at the given index.
     *
     * @param    index    The index of the element to be accessed.
     * @return The Object at the index.
     */
    public Object elementAt(int index) {
        if (index < 0 || index >= size)
            return null;
        return data[index];
    }

    /**
     * Find the index of the element.
     *
     * @param    element    The element that its index needs to be found.
     * @return The index of the element, if no such element return -1.
     */
    public int indexOf(Object element) {
        for (int i = 0; i < size; ++i)
            if (element.equals(data[i]))
                return i;
        return -1;
    }

    /**
     * Insert the element at the given index.
     *
     * @param    index    The index of the location to be inserted.
     * @param    element    The element to be inserted.
     * @return true if succeeds, false otherwise.
     */
    public boolean insertAt(int index, Object element) {
        if (index < 0 || index >= size) return false;

        if (size == data.length) expand();

        for (int i = size; i > index; --i)
            data[i] = data[i - 1];
        data[index] = element;
        ++size;
        return true;
    }

    /**
     * Check whether the ArrayList is empty.
     *
     * @return true if it is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Remove the element at the given index.
     *
     * @param    index    The index of the element to be removed.
     * @return The element being removed.
     */
    public Object removeAt(int index) {
        if (index < 0 || index >= size)
            return null;

        Object temp = data[index];
        while (index < size - 1) {
            data[index] = data[index + 1];
            index++;
        }
        data[--size] = null;
        return temp;
    }

    /**
     * Remove the element from the ArrayList.
     *
     * @param    element    The element to be removed.
     * @return true if succeeds, false otherwise.
     */
    public boolean remove(Object element) {
        return removeAt(indexOf(element)) != null;
    }

    /**
     * Replace the current element at the given index with the given object.
     *
     * @param    index    The index of the element to be replaced.
     * @param    element    The element used to replace the current one.
     * @return true if succeeds, false otherwise.
     */
    public boolean replace(int index, Object element) {
        if (index < 0 || index >= size)
            return false;
        data[index] = element;
        return true;
    }

    /**
     * Get the number of elements in the current ArrayList.
     *
     * @return size of the current ArrayList.
     */
    public int size() {
        return size;
    }

    /**
     * Make sure the ArrayList gets at least the given capacity.
     *
     * @param    minCapacity    The minimal value of capacity.
     */
    public void ensureCapacity(int minCapacity) {
        if (minCapacity <= data.length)
            return;
        Object[] newData = new Object[minCapacity];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * Clone a copy of the current ArrayList.
     *
     * @return The cloned copy of the current ArrayList.
     */
    public MyArrayList clone() {
        MyArrayList cal = new MyArrayList();
        cal.ensureCapacity(this.size);
        for (int i = 0; i < size; ++i) {
            cal.data[i] = this.data[i];
        }
        cal.size = this.size;
        return cal;
    }

    /**
     * Merge the elements from the pal into this MyArrayList.
     *
     * @param    pal        The MyArrayList to copy elements from.
     */
    public void merge(MyArrayList pal) {
        for (int i = 0; i < pal.size(); ++i) {
            this.append(pal.elementAt(i));
        }
    }

    /**
     * Removes a range of elements from this ArrayList.
     *
     * @param    fromIndex    The starting index, inclusive.
     * @param    toIndex        The ending index, exclusive.
     */
    public void removeRange(int fromIndex, int toIndex) {
        if (fromIndex >= toIndex)
            return;
        if (fromIndex < 0)
            fromIndex = 0;
        if (toIndex >= size)
            toIndex = size;
        int num = toIndex - fromIndex;
        for (int i = fromIndex; i < size - num; ++i) {
            data[i] = data[i + num];
        }
        for (int j = size - num; j < size; ++j) {
            data[j] = null;
        }
        size = size - num;
    }

    /**
     * Reverse the order of the elements in the ArrayList.
     */
    public void reverse() {
        Object temp;
        for (int i = 0; i < size / 2; ++i) {
            temp = data[i];
            data[i] = data[size - i - 1];
            data[size - i - 1] = temp;
        }
    }

    /**
     * Get a String representation of the current ArrayList.
     *
     * @return The String representation.
     */
    public String toString() {
        String str = "++++++++++++++++++++++++++++++++++++++++++\n" +
                "The current ArrayList contains the following: \n";
        str += "size = " + size + "\n";
        str += "capacity = " + data.length + "\n";
        for (int i = 0; i < size; ++i) {
            str += i + ": " + data[i] + "\t";
            if ((i + 1) % 5 == 0)
                str += "\n";
        }
        str += "\n+++++++++++++++++++++++++++++++++++++++++++++\n";
        return str;
    }
}
