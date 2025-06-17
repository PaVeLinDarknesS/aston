package ru.aston.hometask.hw1.task1.habitat;

public abstract class Habitat {
    private final String name;

    public Habitat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "name='" + name + '\'' +
                '}';
    }
}