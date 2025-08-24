package ru.aston.hometask.intensive.task1.map;

public interface MyMap<K, V> {

    V get(K key);

    V put(K key, V value);

    V remove(K key);

    int size();
}
