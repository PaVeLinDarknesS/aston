package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.habitat.Habitat;
import ru.aston.hometask.hw1.task1.habitat.Water;
import ru.aston.hometask.hw1.task1.skin.Skin;

public class Whale extends Mammal {


    public Whale(String name) {
        super(name, new Water(), new Spine(40),new Skin("Thick skin"));
    }

    @Override
    public void speak() {
        System.out.println("Wuuuuuuuuuu, Whuuuuuuuu");
    }

    @Override
    public String toString() {
        return "Whale{" +
                "name='" + name + '\'' +
                ", habitat=" + habitat +
                ", spine=" + spine +
                ", skin=" + skin +
                '}';
    }
}
