package com.houss.streamapi.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\55154m\\workspaces\\multithreading-cuncurrency\\src\\main\\java\\com\\houss\\streamapi\\files\\names";

        Stream<String> namesStream = Files.lines(Paths.get(path));
        List<String> names = namesStream.filter(x -> x.startsWith("S")).toList();

    }
}
