package ru.aston.hometask.hw2.task1;

import ru.aston.hometask.hw2.task1.list.MyArrayList;
import ru.aston.hometask.hw2.task1.list.MyList;
import ru.aston.hometask.hw2.task1.set.MyHashSet;
import ru.aston.hometask.hw2.task1.set.MySet;

import java.util.List;

public class Task1 {

    public static void main(String[] args) {

        checkList();

        checkSet();
    }

    private static void checkSet() {

        System.out.println("\n\tSet examples");

        MySet<String> mySet = new MyHashSet<>(0.1F, 10);

        mySet.add("123");
        mySet.add("222");
        mySet.add("321");
        mySet.add("122");
        mySet.add("125");
        mySet.add("127");
        mySet.add("132");
        mySet.add("431");

        System.out.println("Size (expect 8) - " + mySet.size());
        System.out.println("Added exist value - " + mySet.add("123"));
        System.out.println("Contain exist value - " + mySet.contains("123"));
        System.out.println("Remove exist value - " + mySet.remove("222"));
        System.out.println("Contain removed value - " + mySet.contains("222"));
        System.out.println("Size (expect 7) - " + mySet.size());

    }


    private static void checkList() {

        System.out.println("\n\tList examples");

        List<String> list = List.of("1", "2", "3", "4", "5", "6", "7", "8");

        MyList<String> myList = new MyArrayList<>(list);

        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
        System.out.println();


        System.out.println("Added '90' - " + myList.add("90"));
        System.out.println("Index of '90' - " + myList.indexOf("90"));

        System.out.println("Remove '4' - " + myList.remove("4"));
        System.out.println("Index of '4' - " + myList.indexOf("4"));

        myList.add(3, null);
        System.out.println("Index of 'null' (expected 3) - " + myList.indexOf(null));

        System.out.println("Remove item by index 4 - " + myList.remove(4));

        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
        System.out.println();


        List<String> addedList = List.of("11", "12", "13", "14", "15", "16", "17", "18");
        System.out.println(addedList);


        myList.addAll(addedList);

        for (int i = 0; i < myList.size(); i++) {
            System.out.print(myList.get(i) + " ");
        }
        System.out.println();

    }


}
