package algorithm;

/**
 * This class implements several sorting algorithms.
 * Part of the code is modified from a reference book.
 */

import structure.MyArrayList;

/**
 * The ArrayList passed into the sorting methods will be sorted.
 *
 * @author X. David Zheng
 * @version 2.0
 */
public class MySort {

    public MySort() { }  // Prevent instantiation.

    /**
     * This will sort a MyArrayList using a bubble sort.
     *
     * @param	pal	The ArrayList to be sorted
     * @return	The sorted ArrayList
     */
    public static MyArrayList bubbleSort(MyArrayList pal) {
        int i, j, n = pal.size();
        Comparable first, second;

        for (i = 1; i < n; i++)
        {
            for (j = n - 1; j >= i; j--)
            {
                first = (Comparable) pal.elementAt(j - 1);
                second = (Comparable) pal.elementAt(j);
                if (first.compareTo(second) > 0)
                {
                    swap(pal, j - 1, j);
                }
            }
        }
        return pal;
    }

    /**
     * The method for swapping two elements on the ArrayList.
     *
     * @param	pal	The ArrayList in which the swapping will happen.
     * @param	first	The index of the first element to be swapped.
     * @param	second	The index of the second element to be swapped.
     */
    public static void swap(MyArrayList pal, int first, int second) {
        Object temp = pal.elementAt(first);
        pal.replace(first, pal.elementAt(second));
        pal.replace(second, temp);
    }

    /**
     * This will sort a MyArrayList using a selection sort.
     *
     * @param	pal	The ArrayList to be sorted
     * @return	The sorted MyArrayList
     */
    public static MyArrayList selectionSort(MyArrayList pal) {
        int i, j, n = pal.size(), smallPos;
        Comparable smallest, current;

        for (i = 0; i < n - 1; i++)
        {
            smallPos = i;
            smallest = (Comparable) pal.elementAt(smallPos);
            for (j = i + 1; j < n; j++)
            {
                current = (Comparable) pal.elementAt(j);
                if (current.compareTo(smallest) < 0)
                {
                    smallPos = j;
                    smallest = (Comparable) pal.elementAt(smallPos); //= current
                }
            }
            if (smallPos != i)
            {
                swap(pal, i, smallPos);
            }
        }
        return pal;
    }

    /**
     * This method sorts a ArrayList by way of an insertion sort
     *
     * @param pal the ArrayList to be sorted
     * @param left the leftermost element in the ArrayList
     * @param right the rightermost element in the ArrayList
     */
    public static void insertionSort(MyArrayList pal, int left, int right) {
        int inner, outer;
        Object temp;

        for (outer = left + 1; outer <= right; outer++)
        {
            temp = pal.elementAt(outer);
            inner = outer;
            while (inner > left && ((Comparable) pal.elementAt(inner - 1)).compareTo(temp) > 0)
            {
                pal.replace(inner, pal.elementAt(inner - 1));
                inner--;

            }
            pal.replace(inner, temp);
        }

    }

    /**
     * Shell sorts an ArrayList
     *
     * @param pal the ArrayList to be sorted
     */
    public static void shellSort(MyArrayList pal) {
        int inner, outer;
        Object target;
        int h = 1;
        while (h <= pal.size() / 3)
        {
            h = 3 * h + 1;
        }
        while (h > 0)
        {
            for (outer = h; outer < pal.size(); outer++)
            {
                target = pal.elementAt(outer);
                inner = outer;
                while (inner > h - 1 && ((Comparable) pal.elementAt(inner - h)).compareTo(target) > 0)
                {
                    pal.replace(inner, pal.elementAt(inner - h));
                    inner -= h;
                }
                pal.replace(inner, target);
            }
            h = (h - 1) / 3;
        }
    }

    /**
     * Generates the median of three of a ArrayList
     *
     * @param pal the ArrayList
     * @param left the left object
     * @param right the right object
     */
    public static void medianOf3(MyArrayList pal, int left, int right) {
        int mid = (left + right) / 2;
        if (((Comparable) pal.elementAt(left)).compareTo(pal.elementAt(mid)) > 0)
        {
            swap(pal, left, mid);
        }
        if (((Comparable) pal.elementAt(mid)).compareTo(pal.elementAt(right)) > 0)
        {
            swap(pal, mid, right);
        }
        if (((Comparable) pal.elementAt(left)).compareTo(pal.elementAt(mid)) > 0)
        {
            swap(pal, left, mid);
        }
    }

    /**
     * Partitions a ArrayList
     *
     * @param pal the ArrayList
     * @param left the left object
     * @param right the right object
     * @return int the partition
     */
    public static int partition(MyArrayList pal, int left, int right) {
        Object pivot = pal.elementAt((left + right) / 2);
        while (true)
        {
            while (((Comparable) pal.elementAt(++left)).compareTo(pivot) < 0);
            while (((Comparable) pal.elementAt(--right)).compareTo(pivot) >= 0);
            if (left > right)
            {
                break;
            }
            else
            {
                swap(pal, left, right);
            }

        }
        return left;
    }

    /**
     * QuickSort method
     *
     * @param pal the method
     * @param left the left object
     * @param right the right object
     */
    public static void quickSort(MyArrayList pal, int left, int right) {
        if (right - left + 1 <= 10) {
            insertionSort(pal, left, right);
        } else {
            medianOf3(pal, left, right);
            int leftPtr = partition(pal, left, right);
            quickSort(pal, left, leftPtr - 1);
            quickSort(pal, leftPtr, right);
        }
    }

    /**
     * Merge Sorts a ArrayList
     *
     * @param pal the ArrayList
     * @param temp temporary comparable ArrayList
     * @param left the left object
     * @param right the right object
     */
    public static void mergeSort(MyArrayList pal, Comparable[] temp, int left, int right) {
        if (left == right) return;

        int mid = (left + right) / 2, i, j, k;
        mergeSort(pal, temp, left, mid); //can't use mid-1 and mid
        mergeSort(pal, temp, mid + 1, right); //since (left + right)/2 takes the floor.

        for (j = left; j <= right; j++)
        {
            temp[j] = (Comparable) pal.elementAt(j);
        }

        i = left;
        k = mid + 1;
        for (j = left; j <= right; j++)
        {
            if (i == mid + 1)
            {
                pal.replace(j, temp[k++]);
            }
            else
            {
                if (k > right)
                {
                    pal.replace(j, temp[i++]);
                }
                else
                {
                    if ((temp[i]).compareTo(temp[k]) < 0)
                    {
                        pal.replace(j, temp[i++]);
                    }
                    else
                    {
                        pal.replace(j, temp[k++]);
                    }
                }
            }
        }
    }
}
