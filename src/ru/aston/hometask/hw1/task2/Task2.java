package ru.aston.hometask.hw1.task2;

public class Task2 {
    public static void main(String[] args) {
        CarryCargo[] carryCargo = {
                new Truck(), new Airplane(), new Boat(), new Helicopter(), new Tanker()
        };

        for (CarryCargo cargo : carryCargo) {
            cargo.deliverCargo();
        }
    }
}
