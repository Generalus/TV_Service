package ru.thesn.tvs.etvsl;

import java.util.ArrayList;
import java.util.List;

public class Start {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        new Thread(
                () -> System.out.println("hello world")
        ).start();
    }
}
