package ru.aston.hometask.hw1.task2;

public class Airplane implements CarryCargo {

    private Wing[] wings;
    private Wheel[] wheels;
    private Propeller[] propellers;

    public Airplane() {
        wings = new Wing[4];
        propellers = new Propeller[2];
        wheels = new Wheel[3];
    }

    @Override
    public void deliverCargo() {
        System.out.println("Airplane delivered the cargo");
    }
}
