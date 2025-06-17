package ru.aston.hometask.hw1.task2;

public class Tanker implements CarryCargo {

    private Propeller[] propeller;

    public Tanker() {
        propeller = new Propeller[2];
    }

    @Override
    public void deliverCargo() {
        System.out.println("Tanker delivered the cargo");
    }
}
