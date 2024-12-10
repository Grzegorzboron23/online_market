package com.solvd.onlinemarket.employeeInterface.functionalInterface;

@FunctionalInterface
public interface CustomLambda<T, R> {

    R apply(T input);

    default <V> CustomLambda<T, V> andThen(CustomLambda<? super R, ? extends V> after) {
        return (T input) -> after.apply(this.apply(input));
    }
}
