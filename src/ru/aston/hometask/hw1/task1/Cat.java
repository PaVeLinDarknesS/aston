package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.habitat.Habitat;
import ru.aston.hometask.hw1.task1.skin.Wool;

public class Cat extends Mammal {

    public Cat(String name) {
        super(name, Habitat.PEOPLE_HOUSE, new Spine(27), new Wool("Short wool"));
    }

    @Override
    public void speak() {
        System.out.println("Meow, meow");
    }

    @Override
    public void feedChildren() {
        System.out.println("Children are feed with rats and milk");
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", habitat=" + habitat +
                ", skin=" + skin +
                ", spine=" + spine +
                '}';
    }
}
