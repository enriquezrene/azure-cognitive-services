package com.distribuida.azure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@SpringBootApplication
//@Docu
public class AzureDemoApplication {

//    public static void main(String[] args) {
        public static void main(String[] args) {
            List<Integer> myList =
                    Arrays.asList(1, 2, 3);
            int result = myList.stream()
                    .sum();
            System.out.println(result);
        }
    }
}
