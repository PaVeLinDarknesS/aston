package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.habitat.Habitat;

public abstract class Animal {

    protected final String name;

    protected final Habitat habitat;

    public Animal(String name, Habitat habitat) {
        this.name = name;
        this.habitat = habitat;
    }

    public abstract void feedChildren();

    public abstract void speak();

    public String getName() {
        return name;
    }

    public Habitat getHabitat() {
        return habitat;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", habitat=" + habitat +
                '}';
    }
}
