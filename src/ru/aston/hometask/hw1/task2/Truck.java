package ru.aston.hometask.hw1.task2;

public class Truck implements CarryCargo {

    private Wheel[] wheels;

    public Truck() {
        wheels = new Wheel[6];
    }

    @Override
    public void deliverCargo() {
        System.out.println("Truck delivered the cargo");
    }
}
