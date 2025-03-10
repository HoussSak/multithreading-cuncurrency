package com.houss.streamapi;

import java.util.*;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        List<Book> books = new ArrayList<>();
        books.add(new Book(Type.PHILOSOPHY, "Being and Time", "Heidegger", 560));
        books.add(new Book(Type.NOVEL, "The Trial", "Franz Kafka", 240));
        books.add(new Book(Type.THRILLER, "Death on the Nile", "Agatha Christine", 370));
        books.add(new Book(Type.HISTORY, "Ancient Greece", "Robert F", 435));
        books.add(new Book(Type.HISTORY, "Ancient Rome", "Robert F", 860));
        books.add(new Book(Type.NOVEL, "Death of Virgil", "Hermann Broch", 590));
        books.add(new Book(Type.NOVEL, "The Stranger", "Albert Camus", 560));

        List<String> filtredBooks = books.stream()
                .filter(book -> book.getType() == Type.NOVEL)
                .sorted(Comparator.comparing(Book::getAuthor).reversed())
                .map(Book::getTitle)
                .toList();

        Map<Type, List<Book>> booksByType = books.stream()
                .collect(Collectors.groupingBy(Book::getType));

        List<String> booksWithTitleTwoWords = books.stream()
                .map(Book::getTitle)
                .filter(title -> title.matches("^\\w+\\s\\w+$")) // ou .split(" ").length == 2
                .toList();

        // external iteration (Collections)
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            titles.add(book.getTitle());
        }
        // The problem <ith this approach is we pose whether there is a next item or not
        //inherently sequential because we keep getting the items one by one
        // [item, item2, item3, item4] if item 2 and 4 are located next to each other in the memory, this approach doesn't care about that
        // because hasNext() access to the next item...
        //also no parallelization
        Iterator<Book>iterator = books.iterator();
        while (iterator.hasNext()) {
            titles.add(iterator.next().getTitle());
        }

        // Stream API - Internal iteration
        // The opposite of external iteration, if two items are located next to each other in the RAM we iterate them so we optimize the process
        //parallel quite easily
        List<String> titles2 = books.stream().map(Book::getTitle).toList();
       // titles2.forEach(System.out::println);

        // Short-circuiting and loop fusion
        //filter() and map() are different operations, they
        // are merged into the same pass (loop fusion)
        // short-circuiting: some operations don't need to process the whole
        // stream to produce a result
        // here we are looking for just 2 items - so the algorithm
        // terminates after finding 2 items !!!
        List<String> longestBooks = books.stream()
                .filter(book -> {
                    System.out.println("Filtering " + book.getTitle());
                  return   book.getPages() > 500;
                })
                .map(b -> {
                    System.out.println("Mapping "+b.getTitle());
                    return b.getTitle();
                })
                .limit(2)
                .toList();
        //longestBooks.stream().forEach(System.out::println);



    }


}
