package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public class Boat extends Vehicle implements CarryCargo {

    public Boat(Propeller[] propellers) {
        super(
                new Wing[0],
                new Wheel[0],
                propellers
        );
    }

    public Boat() {
        this(new Propeller[]{new Propeller("Boat propeller")});
    }

    @Override
    public void deliverCargo() {
        System.out.println("Boat delivered the cargo");
    }
}
