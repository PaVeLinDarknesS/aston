package ru.aston.hometask.hw2.task1.list;

import java.util.Collection;

public interface MyList<E> {

    boolean add(E e);

    void add(int index, E element);

    boolean addAll(Collection<? extends E> c);

    E get(int index);

    boolean remove(E e);

    E remove(int index);

    int indexOf(E e);

    int size();

}
