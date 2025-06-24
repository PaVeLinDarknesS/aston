package ru.aston.hometask.hw2.task1.list;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class MyArrayList<E> implements MyList<E> {

    public static final int MAX_CAPACITY = Integer.MAX_VALUE - 8;

    private static final int DEFAULT_CAPACITY = 10;

    private final E[] EMPTY_DATA = (E[]) new Object[0];

    private int size;

    private E[] elementData;

    public MyArrayList() {
        elementData = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int size) {
        if (size > 0) {
            elementData = (E[]) new Object[size];
        } else if (size == 0) {
            elementData = EMPTY_DATA;
        } else throw new IllegalArgumentException("Size must be greater than or equals to 0");
    }

    public MyArrayList(Collection<? extends E> c) {
        E[] o;
        if (c == null || (o = (E[]) c.toArray()).length == 0) {
            elementData = EMPTY_DATA;
        } else {
            elementData = o;
            size = o.length;
        }

    }

    @Override
    public boolean add(E element) {
        checkCapacity(1);
        elementData[size++] = element;
        return true;
    }

    private void checkCapacity(int addedCapacity) {
        if (elementData.length < size + addedCapacity) {
            elementData = grow(addedCapacity);
        }
    }

    private E[] grow(int minCapacity) {
        int oldCapacity = elementData.length;
        if (oldCapacity == 0) {
            return (E[]) new Object[Math.max(DEFAULT_CAPACITY, minCapacity)];
        }
        int newCapacity = countNewCapacity(oldCapacity, minCapacity, oldCapacity >> 1);
        return Arrays.copyOf(elementData, newCapacity);
    }

    private int countNewCapacity(int oldCapacity, int minGrow, int optimalGrow) {
        int newCapacity = oldCapacity + Math.max(minGrow, optimalGrow);
        if (newCapacity < 0 || MAX_CAPACITY < newCapacity) {

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
    public void add(int index, E element) {
        final int s = this.size;

        Objects.checkIndex(index, s);
        checkCapacity(1);

        System.arraycopy(elementData, index,
                elementData, index + 1, s - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        if (c.isEmpty()) {
            return false;
        }
        E[] a = (E[]) c.toArray();
        checkCapacity(a.length);
        final int s = size;
        System.arraycopy(a, 0, elementData, s, a.length);
        size += a.length;
        return true;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public boolean remove(E e) {

        int index = this.indexOf(e);

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
    public E remove(int index) {
        Objects.checkIndex(index, size);
        E deletedItem = elementData[index];
        fastRemove(index);
        return deletedItem;
    }

    @Override
    public int indexOf(E e) {

        final int s = this.size;

        for (int i = 0; i < s; i++) {
            if (Objects.equals(e, elementData[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }
}
