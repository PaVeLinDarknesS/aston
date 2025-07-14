package ru.aston.hometask.hw1.task1;

import ru.aston.hometask.hw1.task1.skin.Skin;

public class Task1 {
    public static void main(String[] args) {

        Cat cat = new Cat("Vasua");
        Bear bear = new Bear("Mum bear");
        Whale whale = new Whale("Blue whale");
        Fish fish = new Fish("Goldfish");

        Animal[] animals = new Animal[]{fish, whale, bear, cat};

        for (Animal animal : animals) {
            System.out.println("__________");
            System.out.println(animal);
            animal.speak();
            animal.feedChildren();
        }

        Skin oldSkin = cat.molt();
        System.out.println("\nCat has the same skin after molting? - " + (oldSkin == cat.skin));
        System.out.println("\nCat has the name skin after molting? - " + (oldSkin.getName().equals(cat.skin.getName())));

    }
}
