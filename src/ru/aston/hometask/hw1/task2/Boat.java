package ru.aston.hometask.hw1.task2;

public class Boat implements CarryCargo {

    private Propeller propeller;

    public Boat() {
        propeller = new Propeller();
    }

    @Override
    public void deliverCargo() {
        System.out.println("Boat delivered the cargo");
    }
}
