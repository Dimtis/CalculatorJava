package com.calculator;

import java.util.Scanner;

public class Expression {
    StringBuilder num1 = new StringBuilder();
    String num1Str;
    Integer num1Int;
    StringBuilder num1Rome = new StringBuilder();
    String num1StrRome;

    StringBuilder num2 = new StringBuilder();
    String num2Str;
    Integer num2Int;
    StringBuilder num2Rome = new StringBuilder();
    String num2StrRome;

    char [] signs = new char[] {'+', '-', '*', '/'};
    char op;

    int result;
    String resultRome;

    Scanner scan = new Scanner(System.in);
    String expression = scan.nextLine();
    char[] expr = expression.toCharArray();


    int getNumbers() {
        int counter = 0;
        for (int i = 0; i < expr.length; i++) {
            if(counter > 0 && !Character.isWhitespace(expr[i])) {
                num2.append(expr[i]);
                if (expr[i] == 'I' || expr[i] == 'V') num2Rome.append(expr[i]);
                else num2Rome.setLength(0);
            }

            for (int j = 0; j < signs.length; j++) {
                if(expr[i] == signs[j]) {
                    counter++;
                    op = signs[j];
                }
            }
            if(counter == 0 && !Character.isWhitespace(expr[i])) {
                num1.append(expr[i]);
                if (expr[i] == 'I' || expr[i] == 'V') num1Rome.append(expr[i]);
                else num1Rome.setLength(0);
            }

        }

        if (counter !=1) {
            System.out.println("Вы ввели некорректное выражение");
            System.exit(0);
        }

        num1Str = num1.toString();
        num2Str = num2.toString();

        if (num1Rome.length() != 0) {
            num1Int = 0;
            for (int j = 0; j < num1Rome.length(); j++) {
                if (num1Rome.charAt(j) == 'I') num1Int++;
                else if (num1Rome.charAt(j) == 'V' && num1Int == 0) {
                    num1Int += 5;
                } else num1Int = 4;
            }
        }

        System.out.println(num1Int);

        if (num2Rome.length() != 0) {
            num2Int = 0;
            for (int j = 0; j < num2Rome.length(); j++) {
                if (num2Rome.charAt(j) == 'I') num2Int++;
                else if (num2Rome.charAt(j) == 'V' && num2Int == 0) {
                    num2Int += 5;
                } else num2Int = 4;
            }
        }

        System.out.println(num2Int);

        num1StrRome = num1Rome.toString();
        num2StrRome = num2Rome.toString();

        System.out.println(num1StrRome + " " + num2StrRome);

        if (num1Int == null && num2Int == null) {
            try {
                num1Int = Integer.valueOf(num1Str);
                num2Int = Integer.valueOf(num2Str);
            } catch (Exception e) {
                System.out.println("Вы ввели не целое число");
                System.exit(0);
            }
        }

        if (num1Int > 10 || num2Int > 10) {
            System.out.println("Калькулятор работает с числами не более 10");
            System.exit(0);
        }

        switch (op) {
            case ('+'):
                result = num1Int + num2Int;
                break;
            case ('-'):
                result = num1Int - num2Int;
                break;
            case ('*'):
                result = num1Int * num2Int;
                break;
            case ('/'):
                result = num1Int / num2Int;
                break;
        }
        return result;
    }


    void getResult() {
        System.out.println("Ответ: " + result);
    }
}
