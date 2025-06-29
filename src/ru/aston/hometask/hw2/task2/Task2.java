package ru.aston.hometask.hw2.task2;

import java.util.Comparator;
import java.util.List;

public class Task2 {

    public static void main(String[] args) {
        Book book1 = new Book("1984", "Джордж Оруэлл", 328, 1949);
        Book book2 = new Book("Убить пересмешника", "Харпер Ли", 376, 1960);
        Book book3 = new Book("Властелин колец", "Дж. Р. Р. Толкин", 1178, 1954);
        Book book4 = new Book("Гарри Поттер и философский камень", "Дж. К. Роулинг", 320, 1997);
        Book book5 = new Book("Преступление и наказание", "Фёдор Достоевский", 430, 1866);
        Book book6 = new Book("Мастер и Маргарита", "Михаил Булгаков", 384, 1967);
        Book book7 = new Book("Три товарища", "Эрих Мария Ремарк", 480, 1936);
        Book book8 = new Book("Тень горы", "Грегори Дэвид Робертс", 880, 2015);
        Book book9 = new Book("Атлант расправил плечи", "Айн Рэнд", 1168, 1957);
        Book book10 = new Book("Маленький принц", "Антуан де Сент-Экзюпери", 96, 1943);
        Book book11 = new Book("Шантарам", "Грегори Дэвид Робертс", 864, 2003);
        Book book12 = new Book("Сто лет одиночества", "Габриэль Гарсиа Маркес", 416, 1967);
        Book book13 = new Book("Код да Винчи", "Дэн Браун", 480, 2003);
        Book book14 = new Book("Тайная история", "Донна Тартт", 592, 2013);
        Book book15 = new Book("Исчезнувшая", "Гиллиан Флинн", 448, 2012);
        Book book16 = new Book("Нормальные люди", "Салли Руни", 288, 2018);
        Book book17 = new Book("Тринадцатая сказка", "Диана Сеттерфилд", 416, 2006);
        Book book18 = new Book("Тысяча сияющих солнц", "Халед Хоссейни", 384, 2007);

        List<Book> list1 = List.of(book1, book2, book3, book4, book5);
        List<Book> list2 = List.of(book6, book7, book8, book9, book10);
        List<Book> list3 = List.of(book11, book12, book13, book14, book15);
        List<Book> list4 = List.of(book16, book17, book18, book4, book1);
        List<Book> list5 = List.of(book8, book5, book15, book11, book1);
        List<Book> list6 = List.of(book14, book10, book14, book3, book9);

        List<Student> students = List.of(
                new Student("Petr", list1),
                new Student("Petya", list2),
                new Student("Anna", list3),
                new Student("Nicole", list4),
                new Student("Katie", list5),
                new Student("Stephanie", list6)
        );

        students.stream()
                .peek(System.out::println)
                .map(Student::getBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparing(Book::size))
                .distinct()
                .filter(x -> x.year() > 2000)
                .limit(3)
                .map(Book::year)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> System.out.println("Book not found")
                );
    }
}