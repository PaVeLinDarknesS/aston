package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.habitat.Habitat;
import ru.aston.hometask.hw1.task1.skin.Wool;

public class Bear extends Mammal {

    public Bear(String name) {
        super(name, Habitat.FOREST, new Spine(33), new Wool("Long wool"));
    }

    @Override
    public void speak() {
        System.out.println("Growls, growls");
    }

    @Override
    public String toString() {
        return "Bear{" +
                "name='" + name + '\'' +
                ", habitat=" + habitat +
                ", skin=" + skin +
                ", spine=" + spine +
                '}';
    }
}
