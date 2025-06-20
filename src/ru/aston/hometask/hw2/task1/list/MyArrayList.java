package ru.aston.hometask.hw2.task1.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class MyArrayList<T> implements MyList<T> {

    public static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_CAPACITY = 10;

    private static final Object[] EMPTY_DATA = {};

    private int size;

    private Object[] elementData;

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int size) {
        if (size > 0) {
            elementData = new Object[size];
        } else if (size == 0) {
            elementData = EMPTY_DATA;
        } else throw new IllegalArgumentException("Size must be greater than or equals to 0");
    }

    public MyArrayList(Collection<? extends T> c) {
        Object[] o;
        if (c == null || (o = c.toArray()).length == 0) {
            elementData = EMPTY_DATA;
        } else {
            elementData = o;
            size = o.length;
        }

    }

    @Override
    public boolean add(T element) {
        checkCapacity(1);
        elementData[size++] = element;
        return true;
    }

    private void checkCapacity(int addedCapacity) {
        if (elementData.length < size + addedCapacity) {
            elementData = grow(addedCapacity);
        }
    }

    private Object[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity == 0) {
            return new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
        int newCapacity = countNewCapacity(oldCapacity, minCapacity, oldCapacity >> 1);
        return Arrays.copyOf(elementData, newCapacity);
    }

    private int countNewCapacity(int oldCapacity, int minGrow, int optimalGrow) {

        // Count perfect Capacity
        int newCapacity = oldCapacity + Math.max(minGrow, optimalGrow);
        if (newCapacity < 0 || MAX_CAPACITY < newCapacity) {

            // Count limit Capacity
            newCapacity = oldCapacity + minGrow;
            if (newCapacity < 0) {
                throw new OutOfMemoryError("Required capacity is too large");
            } else if (newCapacity <= MAX_CAPACITY) {
                return MAX_CAPACITY;
            }
        }

        return newCapacity;
    }

    @Override
    public void add(int index, T element) {
        final int s = this.size;

        Objects.checkIndex(index, s);
        checkCapacity(1);

        System.arraycopy(elementData, index,
                elementData, index + 1, s - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c.isEmpty()) {
            return false;
        }
        Object[] a = c.toArray();
        checkCapacity(a.length);
        final int s = size;
        System.arraycopy(a, 0, elementData, s, a.length);
        size += a.length;
        return true;
    }

    @Override
    public T get(int index) {
        Objects.checkIndex(index, size);
        return (T) elementData[index];
    }

    @Override
    public boolean remove(Object o) {

        int index = this.indexOf(o);

        if (index == -1) {
            return false;
        }
        fastRemove(index);
        return true;
    }

    private void fastRemove(int index) {
        final int newSize = size - 1;
        if (newSize > index)
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        size = newSize;
        elementData[newSize] = null;
    }

    @Override
    public T remove(int index) {
        Objects.checkIndex(index, size);
        Object deletedItem = elementData[index];
        fastRemove(index);
        return (T) deletedItem;
    }

    @Override
    public int indexOf(Object o) {

        final int s = this.size;

        if (o == null) {
            for (int i = 0; i < s; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < s; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }
}
