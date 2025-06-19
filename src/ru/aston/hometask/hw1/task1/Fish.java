package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.habitat.Habitat;

public class Fish extends Animal {
    public Fish(String name) {
        super(name, Habitat.WATER);
    }

    @Override
    public void feedChildren() {
        System.out.println("Children are feed with seaweed");
    }

    @Override
    public void speak() {
        System.out.println("Gurgle, Gurgle");
    }

    @Override
    public String toString() {
        return "Fish{" +
                "name='" + name + '\'' +
                ", habitat=" + habitat +
                '}';
    }
}
