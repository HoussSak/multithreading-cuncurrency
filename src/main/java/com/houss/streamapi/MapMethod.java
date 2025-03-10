package com.houss.streamapi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class MapMethod {

    public static void main(String[] args) throws IOException {

        List<Book> books = new ArrayList<>();
        books.add(new Book(Type.PHILOSOPHY, "Being and Time", "Heidegger", 560));
        books.add(new Book(Type.NOVEL, "The Trial", "Franz Kafka", 240));
        books.add(new Book(Type.THRILLER, "Death on the Nile", "Agatha Christine", 370));
        books.add(new Book(Type.HISTORY, "Ancient Greece", "Robert F", 435));
        books.add(new Book(Type.HISTORY, "Ancient Rome", "Robert F", 860));
        books.add(new Book(Type.NOVEL, "Death of Virgil", "Hermann Broch", 590));
        books.add(new Book(Type.NOVEL, "The Stranger", "Albert Camus", 560));

        //map() and flatmap() are similar to selecting a column in DQL
        // transform the original values
        // number pf Chars in every word
        String path = "C:\\Users\\55154m\\workspaces\\multithreading-cuncurrency\\src\\main\\java\\com\\houss\\streamapi\\files\\names";
        List<String> words = Files.lines(Paths.get(path)).toList();
        List<Integer> lengths = words.stream().map(String::length).toList();


        // create a list containing the squared values
        List<Integer> nums = Arrays.asList(1,2,3,4);

        nums.stream()
                .map(x -> x * x)
                .toList().forEach(System.out::println);

        // flatmap()
        //"hello" "shell" - get all the unique characters (h,e,l,o,s)
        List<String> unique = Stream.of("hello", "shell")
                .map(x -> x.split(""))
                .flatMap(Arrays::stream)
                .distinct().toList();
        unique.stream().forEach(System.out::println);

    }
}
