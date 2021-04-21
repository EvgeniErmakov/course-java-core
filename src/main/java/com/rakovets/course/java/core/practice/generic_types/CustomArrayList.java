package com.rakovets.course.java.core.practice.generic_types;

import java.util.Arrays;
import java.util.Random;

/**
 * ArrayList
 * @author Evgeni Ermakov
 * @version 1.0
 */
public class CustomArrayList<T> {
    private T[] data;
    private int size = 0;
    private int capacity = 10;

    public CustomArrayList(int capacity) {
        data = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public CustomArrayList() {
    }

    public int getSize() {
        return size;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            int i = 0;
            capacity = (capacity * 3 / 2) + 1;
            T[] newArray = (T[]) new Object[capacity];
            for (T object : data) {
                newArray[i] = object;
                i++;
            }
            data = newArray;
        }
    }

    public void printCustomArray() {
        System.out.println(Arrays.toString(data));
    }

    /**
     * adding an element to the end of the array
     */
    public void pushBack(T obT) {
        ensureCapacity();
        data[size] = obT;
        size++;
    }

    /**
     * removing the first element from the array
     */
    public void popFront() {
        ensureCapacity();
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 1; i < data.length; i++) {
            newArray[i - 1] = data[i];
        }
        data = newArray;
        size--;
    }

    /**
     * adding a new element to the beginning of the array
     */
    public void pushFront(T obT) {
        ensureCapacity();
        T[] newArray = (T[]) new Object[capacity];
        newArray[0] = obT;
        for (int i = 0; i < data.length - 1; i++) {
            newArray[i + 1] = data[i];
        }
        data = newArray;
        size++;
    }

    /**
     * inserting a new element at the specified index
     */
    public void insert(T obT, int i) throws IndexOutOfBoundsException {
        ensureCapacity();

        if (i < 0 || i >= size + 1) {
            throw new IndexOutOfBoundsException();
        }

        T[] newArray = (T[]) new Object[capacity];
        for (int j = 0; j < i; j++) {
            newArray[j] = data[j];
        }
        newArray[i] = obT;
        for (int j = i + 1; j < data.length; j++) {
            newArray[j] = data[j - 1];
        }
        data = newArray;
        size++;
    }

    /**
     * removing element at the specified index
     */
    public void removeAt(int i) {
        ensureCapacity();
        T[] newArray = (T[]) new Object[capacity];

        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            for (int j = 0; j < i; j++) {
                newArray[j] = data[j];
            }
            for (int j = i; j < data.length - 1; j++) {
                newArray[j] = data[j + 1];
            }
            data = newArray;
            size--;
        }

    }

    /**
     * removing element whose values match the value of the passed parameter
     */
    public void remove(T obT) {
        int index = 0;
        T[] newArray = (T[]) new Object[capacity];

        while ((data[index] != obT) && (index < size)) {
            newArray[index] = data[index];
            index++;
        }
        if (index < size) {
            size--;
            for (int i = index++; i < data.length - 1; i++) {
                newArray[i] = data[i + 1];
            }
        }
        data = newArray;

    }

    /**
     * removing elements whose values match the value of the passed parameter
     */
    public void removeAll(T obT) {
        int index = 0;
        T[] newArray = (T[]) new Object[capacity];

        for (int i = 0; i < data.length; i++) {
            if (data[i] != obT) {
                newArray[index] = data[i];
                index++;
            } else size--;
        }
        data = newArray;
    }

    /**
     * removing the last element from the array
     */
    public void popBack() {
        T[] newArray = (T[]) new Object[capacity];

        for (int i = 0; i < size - 1; i++) {
            newArray[i] = data[i];
        }
        size--;
        data = newArray;
    }

    /**
     * emptying the array
     */
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            data[i] = null;
        }
        size = 0;
    }

    /**
     * returns true if size = 0, and false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * adjusts the capacity value to size
     */
    public void trimToSize() {
        capacity = size;
        T[] newArray = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = data[i];
        }
        data = newArray;
    }

    /**
     * searching first occurrence in array of the specified value
     */
    public int indexOf(T obT) {
        int index = -1;

        for (int i = 0; i < data.length; i++) {
            if (data[i] == obT) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * searching last occurrence in array of the specified value
     */
    public int lastIndexOf(T obT) {
        int index = -1;

        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] == obT) {
                index = i;
                break;
            }
        }
        return index;
    }

    /**
     * changing the order of elements in an array to the opposite
     */
    public void reverse() {
        T[] newArray = (T[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newArray[i] = data[size - 1 - i];
        }
        data = newArray;
    }

    /**
     * random shuffling of array elements
     */
    public void shuffle() {
        T[] newArray = (T[]) new Object[capacity];
        int[] index = new int[size];
        int j = 0;
        boolean flag;

        for (int i = 0; i < index.length; i++) {
            index[i] = -1;
        }
        for (int i = 0; i < size; i++) {
            flag = true;
            while (flag) {
                flag = false;
                j = new Random().nextInt(size);
                for (int id : index) {
                    if (j == id) {
                        flag = true;
                    }
                }
            }
            index[i] = j;
            newArray[i] = data[j];
        }
        data = newArray;
    }

    /**
     * comparing arrays
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * retrieves the value by index
     */
    public T getElementAt(int j) {
        if (j < 0 && j > data.length) {
            throw new IndexOutOfBoundsException();
        }
        return data[j];
    }

    /**
     * creates an exact copy of the CustomArrayList
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


