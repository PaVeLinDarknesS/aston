package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.habitat.Habitat;
import ru.aston.hometask.hw1.task1.skin.Skin;

public abstract class Mammal extends Animal {

    protected final Spine spine;
    protected Skin skin;

    public Mammal(String name, Habitat habitat, Spine spine, Skin skin) {
        super(name, habitat);
        this.spine = spine;
        this.skin = skin;
    }

    public Skin molt() {
        Skin oldSkin = skin;
        skin = oldSkin.clone();
        return oldSkin;
    }

    @Override
    public void feedChildren() {
        System.out.println("Children are feed with milk");
    }

    @Override
    public String toString() {
        return "Mammal{" +
                "name='" + name + '\'' +
                ", habitat=" + habitat +
                ", skin=" + skin +
                ", spine=" + spine +
                '}';
    }
}