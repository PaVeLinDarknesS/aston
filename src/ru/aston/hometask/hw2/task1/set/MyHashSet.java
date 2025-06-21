package ru.aston.hometask.hw2.task1.set;

import java.util.Objects;

public class MyHashSet<T> implements MySet<T> {

    private static final int DEFAULT_CAPACITY = 16;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static final int RESIZE_BUCKET_CAPACITY = 8;

    private Node<T>[] table;

    private final float loadFactor;

    private int threshold;

    private int size;

    public MyHashSet(float loadFactor, int initialCapacity) {

        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        this.loadFactor = loadFactor;
        int capacity = tableSizeFor(initialCapacity);
        table = (Node<T>[]) new Node[capacity];
        this.threshold = (int) (capacity * this.loadFactor);
    }


    public MyHashSet() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }


    private static int tableSizeFor(int capacity) {
        if (capacity <= DEFAULT_CAPACITY) {
            return DEFAULT_CAPACITY;
        }
        for (int i = 5; i < 29; i++) {
            if (1 << i >= capacity) {
                return 1 << i;
            }
        }
        return MAXIMUM_CAPACITY;
    }


    private static class Node<T> {

        final int hashCode;
        final T value;
        Node<T> nextVal;

        public Node(int hashCode, T value, Node<T> nextVal) {
            this.hashCode = hashCode;
            this.value = value;
            this.nextVal = nextVal;
        }

        public T getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Node<?> node)) return false;
            return Objects.equals(value, node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(value);
        }
    }

    @Override
    public boolean add(T value) {
        int l, index, hash = Objects.hash(value);
        int countNode = 1;
        Node<T> first;
        if ((first = table[index = (table.length - 1) & hash]) == null) {
            table[index] = new Node<>(hash, value, null);
            size++;
        } else if (comparingCondition(first, hash, value)) {
            return false;
        } else {
            while (first.nextVal != null) {
                first = first.nextVal;
                countNode++;
                if (comparingCondition(first, hash, value)) {
                    return false;
                }
            }
            first.nextVal = new Node<>(hash, value, null);
            size++;

        }
        if (size >= threshold || countNode >= RESIZE_BUCKET_CAPACITY) {
            table = resize();
        }
        return true;
    }


    private Node<T>[] resize() {

        Node<T>[] oldTable = table;
        int oldCapacity = table.length;
        int oldThr = threshold;

        int newCap, newThr = 0;

        if (oldCapacity >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTable;
        } else if ((newCap = oldCapacity << 1) < MAXIMUM_CAPACITY) {
            newThr = oldThr << 1;
        }

        threshold = newThr;

        Node<T>[] newTab = (Node<T>[]) new Node[newCap];
        table = newTab;
        for (int j = 0; j < oldCapacity; ++j) {
            Node<T> temp;
            if ((temp = oldTable[j]) != null) {
                oldTable[j] = null;
                if (temp.nextVal == null)
                    newTab[temp.hashCode & (newCap - 1)] = temp;

                else { // placement items in table[j] or table[j+oldCapacity]
                    Node<T> oldHead = null, oldTail = null;
                    Node<T> newHead = null, newTail = null;
                    Node<T> next;
                    do {
                        next = temp.nextVal;
                        if ((temp.hashCode & oldCapacity) == 0) {
                            if (oldTail == null)
                                oldHead = temp;
                            else
                                oldTail.nextVal = temp;
                            oldTail = temp;
                        } else {
                            if (newTail == null)
                                newHead = temp;
                            else
                                newTail.nextVal = temp;
                            newTail = temp;
                        }
                    } while ((temp = next) != null);

                    if (oldTail != null) {
                        oldTail.nextVal = null;
                        newTab[j] = oldHead;
                    }

                    if (newTail != null) {
                        newTail.nextVal = null;
                        newTab[j + oldCapacity] = newHead;
                    }


                }
            }

        }
        return newTab;
    }


    @Override
    public int size() {
        return size;
    }


    @Override
    public boolean remove(Object o) {

        int hash = Objects.hash(o), index;
        Node<T> first;
        if (table != null && table.length > 0 &&
                (first = table[index = (table.length - 1) & hash]) != null) {
            Node<T> node = null, temp;
            if (comparingCondition(first, hash, o)) {
                node = first;
            } else if ((temp = first.nextVal) != null) {
                do {
                    if (comparingCondition(temp, hash, o)) {
                        node = temp;
                        break;
                    }
                    first = temp;
                } while ((temp = temp.nextVal) != null);
            }

            if (node != null && comparingCondition(node, hash, o)) {
                if (node == first) {
                    table[index] = node.nextVal;
                } else {
                    first.nextVal = node.nextVal;
                }
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(Object o) {
        int hash;
        Node<T> first;
        if (table != null && table.length > 0 &&
                (first = table[(table.length - 1) & (hash = Objects.hash(o))]) != null) {
            do {
                if (comparingCondition(first, hash, o)) {
                    return true;
                }
            } while ((first = first.nextVal) != null);
        }
        return false;
    }

    private boolean comparingCondition(Node<T> node, int hash, Object o) {
        return node.hashCode == hash &&
                (node.getValue() == o || (node.getValue() != null && node.equals(o)));
    }
}

