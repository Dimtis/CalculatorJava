package com.calculator;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {
    StringBuilder num1 = new StringBuilder();
    String num1Str;
    Integer num1Int;
    StringBuilder num1Rome = new StringBuilder();

    StringBuilder num2 = new StringBuilder();
    String num2Str;
    Integer num2Int;
    StringBuilder num2Rome = new StringBuilder();

    char [] signs = new char[] {'+', '-', '*', '/'};
    char op;

    int result;
    String romeRemainder = "";
    String romeDozens = "";
    String resultRome = "";

    Scanner scan = new Scanner(System.in);
    String expression = scan.nextLine();
    char[] expr = expression.toCharArray();

    int getNumbers() {
        int counter = 0;
        for (int i = 0; i < expr.length; i++) {
            if(counter > 0 && !Character.isWhitespace(expr[i])) {
                num1.append(expr[i]);
                if (Character.toLowerCase(expr[i]) == Character.toLowerCase('I') || Character.toLowerCase(expr[i]) == Character.toLowerCase('V')
                        || Character.toLowerCase(expr[i]) == Character.toLowerCase('X')) num1Rome.append(expr[i]);
                else num1Rome.setLength(0);
            }

            for (int j = 0; j < signs.length; j++) {
                if(expr[i] == signs[j]) {
                    counter++;
                    op = signs[j];
                }
            }
            if(counter == 0 && !Character.isWhitespace(expr[i])) {
                num2.append(expr[i]);
                if (Character.toLowerCase(expr[i]) == Character.toLowerCase('I') || Character.toLowerCase(expr[i]) == Character.toLowerCase('V')
                        || Character.toLowerCase(expr[i]) == Character.toLowerCase('X')) num2Rome.append(expr[i]);
                else num2Rome.setLength(0);
            }
        }

        num1Str = num1.toString();
        Matcher m1I = Pattern.compile("[iI]{4,}").matcher(num1Str);
        Matcher m1V = Pattern.compile("[vV]{2,}").matcher(num1Str);
        Matcher m1X = Pattern.compile("[xX]{2,}").matcher(num1Str);
        Matcher m1IV = Pattern.compile("[iI]{2,}[vV]").matcher(num1Str);
        num2Str = num2.toString();
        Matcher m2I = Pattern.compile("[iI]{4,}").matcher(num2Str);
        Matcher m2V = Pattern.compile("[vV]{2,}").matcher(num2Str);
        Matcher m2X = Pattern.compile("[xX]{2,}").matcher(num2Str);
        Matcher m2IV = Pattern.compile("[iI]{2,}[vV]").matcher(num2Str);
        if (m1I.find() || m1V.find() || m2I.find() || m2V.find() || m1IV.find() || m2IV.find()) {
            System.out.println("Вы ввели некорректную римскую цифру");
            System.exit(0);
        }

        if (m1X.find() || m2X.find()) more10();

        if (counter !=1) {
            System.out.println("Вы ввели некорректное выражение");
            System.exit(0);
        }

        if (num1Rome.length() != 0) {
            num1Int = 0;
            for (int j = 0; j < num1Rome.length(); j++) {
                if (Character.toLowerCase(num1Rome.charAt(j)) == Character.toLowerCase('I')) {
                    num1Int++;
                }
                else if (Character.toLowerCase(num1Rome.charAt(j)) == Character.toLowerCase('V') && num1Int == 0) {
                    num1Int += 5;
                } else if (Character.toLowerCase(num1Rome.charAt(j)) == Character.toLowerCase('X') && num1Int == 0) {
                    num1Int +=10;
                } else if (Character.toLowerCase(num1Rome.charAt(j)) == Character.toLowerCase('X') && num1Int == 1) {
                    num1Int = 9;
                } else {
                    num1Int = 4;
                }
            }
        }

        if (num2Rome.length() != 0) {
            num2Int = 0;
            for (int j = 0; j < num2Rome.length(); j++) {
                if (Character.toLowerCase(num2Rome.charAt(j)) == Character.toLowerCase('I')) {
                    num2Int++;
                }
                else if (Character.toLowerCase(num2Rome.charAt(j)) == Character.toLowerCase('V') && num2Int == 0) {
                    num2Int += 5;
                } else if (Character.toLowerCase(num2Rome.charAt(j)) == Character.toLowerCase('X') && num2Int == 0) {
                    num2Int +=10;
                } else if (Character.toLowerCase(num2Rome.charAt(j)) == Character.toLowerCase('X') && num2Int == 1) {
                    num2Int = 9;
                } else {
                    num2Int = 4;
                }
            }
        }

        if (num1Int == null && num2Int == null) {
            try {
                num1Int = Integer.valueOf(num1Str);
                num2Int = Integer.valueOf(num2Str);
            } catch (Exception e) {
                System.out.println("Вы ввели не целое число");
                System.exit(0);
            }
        }

        if ((num1Rome.length() != 0 && num2Rome.length() == 0) || (num1Rome.length() == 0 && num2Rome.length() != 0)) {
            System.out.println("Введите обе цифры либо арабскими, либо римскими");
            System.exit(0);
        }

        if (num1Int > 10 || num2Int > 10) more10();

        switch (op) {
            case ('+'):
                result = num1Int + num2Int;
                break;
            case ('-'):
                result = num2Int - num1Int;
                break;
            case ('*'):
                result = num1Int * num2Int;
                break;
            case ('/'):
                result = num2Int / num1Int;
                break;
        }
        return result;
    }

    void more10(){
        System.out.println("Калькулятор работает с числами не более 10");
        System.exit(0);
    }

    String less10(int remainder) {
        switch (remainder) {
            case (1):
                romeRemainder = "I";
                break;
            case (2):
                romeRemainder = "II";
                break;
            case (3):
                romeRemainder = "III";
                break;
            case (4):
                romeRemainder = "IV";
                break;
            case (5):
                romeRemainder = "V";
                break;
            case (6):
                romeRemainder = "VI";
                break;
            case (7):
                romeRemainder = "VII";
                break;
            case (8):
                romeRemainder = "VIII";
                break;
            case (9):
                romeRemainder = "IX";
                break;
        }
        return romeRemainder;
    }

    String dozensRome(int dozens) {
        switch (dozens) {
            case (1):
                romeDozens = "X";
                break;
            case (2):
                romeDozens = "XX";
                break;
            case (3):
                romeDozens = "XXX";
                break;
            case (4):
                romeDozens = "XL";
                break;
            case (5):
                romeDozens = "L";
                break;
            case (6):
                romeDozens = "LX";
                break;
            case (7):
                romeDozens = "LXX";
                break;
            case (8):
                romeDozens = "LXXX";
                break;
            case (9):
                romeDozens = "XC";
                break;
        }
        return romeDozens;
    }

    void getResult() {
        int remainder = result%10;
        int dozens = result/10;
        if (num1Rome.length() != 0 && num2Rome.length() != 0) {
            if (remainder != 0 || result <10) {
                less10(remainder);
            }
            if (dozens != 0) {
                dozensRome(dozens);
            }
            if (dozens == 10) romeDozens = "C";
            resultRome = romeDozens+romeRemainder;
            System.out.println("Ответ: " + resultRome);
        } else System.out.println("Ответ: " + result);
    }
}
