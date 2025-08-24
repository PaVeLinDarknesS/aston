package ru.aston.hometask.intensive.task1;

import ru.aston.hometask.intensive.task1.map.MyHashMap;
import ru.aston.hometask.intensive.task1.map.MyMap;

public class Task1 {

    public static void main(String[] args) {

        System.out.println("\n\tMap examples");

        MyMap<String, String> myMap = new MyHashMap<>();

        myMap.put("123", "123");
        myMap.put("222", "222");
        myMap.put("321", "321");
        myMap.put("122", "122");
        myMap.put("125", "125");
        myMap.put("127", "127");
        myMap.put("132", "132");
        myMap.put("431", "431");

        System.out.println("Size (expect 8) - " + myMap.size());
        System.out.println("Added exist value - " + myMap.put("123", "exist"));
        System.out.println("Contain new value. (expect 'exist') - " + myMap.get("123"));
        System.out.println("Remove exist value - " + myMap.remove("222"));
        System.out.println("Contain removed value. (expect 'null') - " + myMap.get("222"));
        System.out.println("Size (expect 7) - " + myMap.size());
    }
}