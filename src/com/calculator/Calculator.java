package com.calculator;

public class Calculator {
    public static void main(String[] args) {

        System.out.println("Введите выражение: ");

        Expression expression = new Expression();
        expression.getNumbers();
        expression.getResult();

    }
}
