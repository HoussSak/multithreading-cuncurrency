package com.houss.streamapi.parallelstream.files;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class ParallelSaveOnOperation {

    public static final String DIRECTORY = System.getProperty("user.dir") + "/test/";

    public static void main(String[] args) throws IOException {

        // create the directory

        Files.createDirectories(Paths.get(DIRECTORY));

        ParallelSaveOnOperation app = new ParallelSaveOnOperation();

        List<Person> people = app.generatePeople(100000);

        // sequential algorithm
        long start = System.currentTimeMillis();
        people.stream().forEach(ParallelSaveOnOperation::save);
        System.out.println("Time taken sequential: " + (System.currentTimeMillis() - start));

        // parallel algorithm
         start = System.currentTimeMillis();
        people.parallelStream().forEach(ParallelSaveOnOperation::save);
        System.out.println("Time taken parallel: " + (System.currentTimeMillis() - start));

    }

    private static void save(Person person) {
        try (FileOutputStream fos = new FileOutputStream(new File(DIRECTORY+ person.getPersonId()+ ".txt"))){

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Person> generatePeople(int num) {
        return Stream.iterate(0, n -> n+1)
                .limit(num)
                .map(Person::new).toList();
    }
}
