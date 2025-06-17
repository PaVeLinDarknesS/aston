package ru.aston.hometask.hw1.task1;

public abstract class Mammal extends Animal {
    protected Spine spine;
    protected Skin skin;

    protected Mammal(Habitat habitat) {
        super(habitat);
        spine = new Spine();
    }

    protected Mammal(Skin skin) {
        this.skin = skin;
        spine = new Spine();
    }
}