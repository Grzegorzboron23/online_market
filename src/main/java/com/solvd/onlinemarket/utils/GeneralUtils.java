package com.solvd.onlinemarket.utils;

import com.solvd.onlinemarket.employee.Employee;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class GeneralUtils {

    public static Supplier<Double> generateRandomValue(List<Employee> employees) {
        return () -> Math.random(); // can be Math::random; but i have to show lambda
    }

    public static <T, R> List<R> transformList(List<T> list, Function<T, R> transformer) {
        return list.stream()
                .map(transformer)
                .collect(Collectors.toList());
    }


    public static <T> List<T> customFilter(List<T> list, Predicate<T> transformer) {
        return list.stream()
                .filter(transformer)
                .collect(Collectors.toList());
    }
}
