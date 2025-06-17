package ru.aston.hometask.hw1.task2;

public class Vehicle {

    private Airplane airplane;
    private Boat boat;
    private Helicopter helicopter;
    private Tanker tanker;
    private Truck truck;
    private Taxi taxi;

    public Vehicle(Airplane airplane, Boat boat, Helicopter helicopter, Tanker tanker, Truck truck, Taxi taxi) {
        this.airplane = airplane;
        this.boat = boat;
        this.helicopter = helicopter;
        this.tanker = tanker;
        this.truck = truck;
        this.taxi = taxi;
    }
}
