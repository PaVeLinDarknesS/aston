package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public class Taxi extends Vehicle {

    public Taxi(Wheel[] wheels) {
        super(
                new Wing[0],
                wheels,
                new Propeller[0]
        );
    }

    public Taxi() {
        this(new Wheel[]{
                new Wheel(19), new Wheel(19), new Wheel(19), new Wheel(19)}
        );
    }
}
