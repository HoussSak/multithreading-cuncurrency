package com.houss.streamapi;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamOtherConcepts {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(Type.PHILOSOPHY, "Being and Time", "Heidegger", 560));
        books.add(new Book(Type.NOVEL, "The Trial", "Franz Kafka", 240));
        books.add(new Book(Type.THRILLER, "Death on the Nile", "Agatha Christine", 370));
        books.add(new Book(Type.HISTORY, "Ancient Greece", "Robert F", 435));
        books.add(new Book(Type.HISTORY, "Ancient Rome", "Robert F", 860));
        books.add(new Book(Type.NOVEL, "Death of Virgil", "Hermann Broch", 590));
        books.add(new Book(Type.NOVEL, "The Stranger", "Albert Camus", 560));

        // total number of books
        //System.out.println(books.stream().count());
        // max to get the maximum value
        List<Integer> numbers = List.of(1,2,3,4);
        //numbers.stream().reduce(Integer::max).ifPresent(System.out::println);

        // the max number of pages
        Stream.ofNullable(books)
                .flatMap(List::stream)
                .map(Book::getPages)
                .reduce(Integer::max).ifPresent(System.out::println);


        // or we can also get un OptionalInt if we know certainly tha we're dealing with an int
        Stream.ofNullable(books)
                .flatMap(List::stream)
                .mapToInt(Book::getPages)
                .max().ifPresent(System.out::println);



        // we want to get the longest book
        Stream.ofNullable(books)
                .flatMap(List::stream)
                .reduce((b1, b2) -> b1.getPages() > b2.getPages() ? b1 : b2)
                .ifPresent(System.out::println);

        // to sum all pages of the books
        Stream.ofNullable(books)
                .flatMap(List::stream)
                .map(Book::getPages)
                .reduce(Integer::sum)
                .ifPresent(System.out::println);


        // to sum all pages of the books
        IntStream intStream = Stream.ofNullable(books)
                .flatMap(List::stream)
                .mapToInt(Book::getPages);

        System.out.println(intStream.sum());

        //if we want to transform an IntStream to a stream of integer we call boxed()
         Stream.ofNullable(books)
                .flatMap(List::stream)
                .mapToInt(Book::getPages)
                .boxed().forEach(System.out::println);

        // allMatch()
        boolean b1 = Stream.ofNullable(books)
                .flatMap(List::stream)
                .allMatch(b -> b.getPages() > 1000);

        // noneMatch(), example of short-circuiting
        boolean b2 = Stream.ofNullable(books)
                .flatMap(List::stream)
                .noneMatch(b -> b.getPages() > 100);
        // findAny() and findFirst()
        Stream.ofNullable(books)
                .flatMap(List::stream)
                .findAny().ifPresent(System.out::println);

        Stream.ofNullable(books)
                .flatMap(List::stream)
                .findFirst().ifPresent(System.out::println);

        // if we use parallelization we use findAny(), because we don't care, under the hood we may have multiple subArrays,
        // so if we use findFirst we have to check all the subArrays
    }
}
