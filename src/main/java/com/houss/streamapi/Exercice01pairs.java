package com.houss.streamapi;

import java.util.Arrays;
import java.util.List;

public class Exercice01pairs {

    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 2, 3);
        List<Integer> list2 = List.of(4, 5);

        List<List<Integer>> pairs = list1.stream()
                .flatMap(x ->
                        list2.stream()
                                .map(j -> Arrays.asList(x, j)))
                .toList();

        System.out.println(pairs);

    }
}
