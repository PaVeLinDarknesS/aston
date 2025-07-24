package ru.aston.hometask.hw1.task2;

import ru.aston.hometask.hw1.task2.parts.Propeller;
import ru.aston.hometask.hw1.task2.parts.Wheel;
import ru.aston.hometask.hw1.task2.parts.Wing;

public abstract class Vehicle {

    protected final Wing[] wings;
    protected final Wheel[] wheels;
    protected final Propeller[] propellers;

    public Vehicle(Wing[] wings, Wheel[] wheels, Propeller[] propellers) {
        this.wings = wings;
        this.wheels = wheels;
        this.propellers = propellers;
    }
}
