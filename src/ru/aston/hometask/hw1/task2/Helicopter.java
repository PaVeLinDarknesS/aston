package ru.aston.hometask.hw1.task2;

public class Helicopter implements CarryCargo {

    private Wheel[] wheels;
    private Propeller[] propeller;

    public Helicopter() {
        wheels = new Wheel[3];
        propeller = new Propeller[2];
    }

    @Override
    public void deliverCargo() {
        System.out.println("Helicopter delivered the cargo");
    }
}
