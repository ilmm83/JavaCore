package edu.java_core.datastructures.collections;

import java.util.ArrayList;
import java.util.List;

public class WorkingWithList {
    public static void main(String[] args) {

        List<String> colors = new ArrayList<>();
        colors.add("Green");
        colors.add("Blue");
        colors.add("Red");

        colors.forEach(x ->{
            if (x.endsWith("n")){
                System.out.println(x + "\n");
            }
        });
    }
}
