package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public class Tanker extends Vehicle implements CarryCargo {

    public Tanker(Propeller[] propellers) {
        super(
                new Wing[0],
                new Wheel[0],
                propellers
        );
    }

    public Tanker() {
        this(
                new Propeller[]{new Propeller("Left"), new Propeller("Right")}
        );
    }

    @Override
    public void deliverCargo() {
        System.out.println("Tanker delivered the cargo");
    }
}
