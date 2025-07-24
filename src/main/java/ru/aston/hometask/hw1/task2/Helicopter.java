package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public class Helicopter extends Vehicle implements CarryCargo {

    public Helicopter(Wheel[] wheels, Propeller[] propellers) {
        super(
                new Wing[0],
                wheels,
                propellers
        );
    }

    public Helicopter() {
        this(
                new Wheel[]{
                        new Wheel(20), new Wheel(20), new Wheel(20)},
                new Propeller[]{new Propeller("Big"), new Propeller("Small")}
        );
    }

    @Override
    public void deliverCargo() {
        System.out.println("Helicopter delivered the cargo");
    }
}
