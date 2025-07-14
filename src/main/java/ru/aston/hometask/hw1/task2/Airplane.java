package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public class Airplane extends Vehicle implements CarryCargo {

    public Airplane(Wing[] wings, Wheel[] wheels, Propeller[] propellers) {
        super(wings, wheels, propellers);
    }

    public Airplane() {
        this(
                new Wing[]{
                        new Wing("Big left"), new Wing("Big right"), new Wing("Small left"), new Wing("Small right")},
                new Wheel[]{
                        new Wheel(27), new Wheel(27), new Wheel(25)},
                new Propeller[]{new Propeller("Wings left"), new Propeller("Wings right")}
        );
    }

    @Override
    public void deliverCargo() {
        System.out.println("Airplane delivered the cargo");
    }
}
