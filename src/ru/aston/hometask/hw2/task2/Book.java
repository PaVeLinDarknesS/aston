package ru.aston.hometask.hw2.task2;

public record Book(String title, String author, Integer size, Integer year) {
    @Override
    public String toString() {
        return "\n\tBook{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", size=" + size +
                ", year=" + year +
                '}';
    }
}
