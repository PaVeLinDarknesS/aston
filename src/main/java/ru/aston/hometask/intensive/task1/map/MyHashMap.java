package ru.aston.hometask.intensive.task1.map;

import java.util.Objects;

public class MyHashMap<K, V> implements MyMap<K, V> {

    private static final int DEFAULT_CAPACITY = 16;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static final int RESIZE_BUCKET_CAPACITY = 8;

    private Node<K, V>[] table;

    private final float loadFactor;

    private int threshold;

    private int size;


    public MyHashMap() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public MyHashMap(float loadFactor, int initialCapacity) {

        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " +
                    initialCapacity);
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " +
                    loadFactor);
        }
        this.loadFactor = loadFactor;
        int capacity = tableSizeFor(initialCapacity);
        table = (Node<K, V>[]) new Node[capacity];
        this.threshold = (int) (capacity * this.loadFactor);
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

    private static class Node<K, V> {

        final int hashCode;
        final K key;
        V value;
        Node<K, V> next;

        public Node(int hashCode, K key, V value, Node<K, V> next) {
            this.hashCode = hashCode;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V newVal) {
            V oldVal = value;
            value = newVal;
            return oldVal;
        }

        public int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }

            return (o instanceof Node<?, ?> node)
                    && Objects.equals(key, node.getKey())
                    && Objects.equals(value, node.getValue());
        }
    }

    @Override
    public V put(K key, V value) {
        int index, hash = Objects.hash(key);
        int countNode = 1;
        Node<K, V> first;
        if ((first = table[index = (table.length - 1) & hash]) == null) {
            table[index] = new Node<>(hash, key, value, null);
            size++;
        } else if (comparingCondition(first, hash, key)) {
            return first.setValue(value);
        } else {
            for (; first.next != null; first = first.next, countNode++) {
                if (comparingCondition(first, hash, key)) {
                    return first.setValue(value);
                }
            }
            first.next = new Node<>(hash, key, value, null);
            size++;
        }
        if (size >= threshold || countNode >= RESIZE_BUCKET_CAPACITY) {
            table = resize();
        }
        return null;
    }

    private Node<K, V>[] resize() {

        Node<K, V>[] oldTable = table;
        int oldCapacity = table.length;
        int oldThr = threshold;

        int newCap;

        if (oldCapacity >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTable;
        } else {
            newCap = oldCapacity << 1;
            threshold = oldThr << 1;
        }

        Node<K, V>[] newTab = (Node<K, V>[]) new Node[newCap];
        table = newTab;
        for (int j = 0; j < oldCapacity; ++j) {
            Node<K, V> temp;
            if ((temp = oldTable[j]) != null) {
                oldTable[j] = null;
                if (temp.next == null) {
                    newTab[temp.hashCode & (newCap - 1)] = temp;
                } else {
                    resizeBucketList(j, temp, oldCapacity, newTab);
                }
            }

        }
        return newTab;
    }


    private void resizeBucketList(int bucket, Node<K, V> firstBuck, int oldCapacity, Node<K, V>[] newTab) {
        Node<K, V> oldHead = null, oldTail = null;
        Node<K, V> newHead = null, newTail = null;
        Node<K, V> next = firstBuck;
        while (next != null) {
            if ((next.hashCode & oldCapacity) == 0) {
                if (oldTail == null) {
                    oldHead = next;
                } else {
                    oldTail.next = next;
                }
                oldTail = next;
            } else {
                if (newTail == null) {
                    newHead = next;
                } else {
                    newTail.next = next;
                }
                newTail = next;
            }
            next = next.next;
        }

        if (oldTail != null) {
            oldTail.next = null;
            newTab[bucket] = oldHead;
        }

        if (newTail != null) {
            newTail.next = null;
            newTab[bucket + oldCapacity] = newHead;
        }
    }

    @Override
    public int size() {
        return size;
    }


    @Override
    public V get(K key) {
        Node<K, V> el;
        return (el = getRemoveNode(key, false)) == null ? null : el.getValue();
    }


    @Override
    public V remove(K key) {
        Node<K, V> el;
        return (el = getRemoveNode(key, true)) == null ? null : el.getValue();
    }


    private Node<K, V> getRemoveNode(K key, boolean removeFlag) {

        int hash = Objects.hash(key), index;
        Node<K, V> node = null, first;
        if (table != null && table.length > 0 &&
                (first = table[index = (table.length - 1) & hash]) != null) {
            Node<K, V> temp;
            if (comparingCondition(first, hash, key)) {
                node = first;
            } else {
                temp = first;
                while ((temp = temp.next) != null) {
                    if (comparingCondition(temp, hash, key)) {
                        node = temp;
                        break;
                    }
                    first = temp;
                }
            }


            if (removeFlag && node != null && comparingCondition(node, hash, key)) {
                if (node == first) {
                    table[index] = node.next;
                } else {
                    first.next = node.next;
                }
                size--;
            }
        }
        return node;
    }

    private boolean comparingCondition(Node<K, V> node, int hash, K key) {
        return node.hashCode == hash &&
                (node.getKey() == key ||
                        (node.getKey() != null &&
                                node.equals(key)));
    }
}