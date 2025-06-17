package ru.aston.hometask.hw1.task1.skin;

public class Skin implements Cloneable {

    private final String name;

    public Skin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Skin{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public Skin clone() {
        try {
            Skin clone = (Skin) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
