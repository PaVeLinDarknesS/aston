package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public class Truck extends Vehicle implements CarryCargo {

    public Truck(Wheel[] wheels) {
        super(
                new Wing[0],
                wheels,
                new Propeller[0]
        );
    }

    public Truck() {
        this(new Wheel[]{
                new Wheel(25), new Wheel(25), new Wheel(25),
                new Wheel(25), new Wheel(25), new Wheel(25)}
        );
    }

    @Override
    public void deliverCargo() {
        System.out.println("Truck delivered the cargo");
    }
}
