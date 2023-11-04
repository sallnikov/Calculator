import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например, 2 + 3):");
        String input = scanner.nextLine();
        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] tokens = input.split(" ");
        if (tokens.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода. Используйте пример: '2 + 3'");
        }

        String firstToken = tokens[0];
        String operator = tokens[1];
        String secondToken = tokens[2];

        int a, b;
        boolean isRoman = false;

        try {
            a = Integer.parseInt(firstToken);
            b = Integer.parseInt(secondToken);
        } catch (NumberFormatException e) {

            a = RomanConverter.romanToArabic(firstToken);
            b = RomanConverter.romanToArabic(secondToken);
            isRoman = true;
        }

        if (a < 1 || a > 10 || b < 1 || b > 10) {
            throw new IllegalArgumentException("Числа должны быть от 1 до 10 включительно.");
        }

        int result;
        switch (operator) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "*":
                result = a * b;
                break;
            case "/":
                if (b == 0) {
                    throw new IllegalArgumentException("Деление на ноль.");
                }
                result = a / b;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция. Используйте +, -, *, /");
        }

        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException("Результат не может быть меньше единицы.");
            }
            return RomanConverter.arabicToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }
}

class RomanConverter {
    private static final String[] romanNumerals = {
            "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX",
            "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
            "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX",
            "C", "CI", "CII", "CIII", "CIV", "CV", "CVI", "CVII", "CVIII", "CIX", "CX",
            "CXI", "CXII", "CXIII", "CXIV", "CXV", "CXVI", "CXVII", "CXVIII", "CXIX", "CXX",
            "CXXI", "CXXII", "CXXIII", "CXXIV", "CXXV", "CXXVI", "CXXVII", "CXXVIII", "CXXIX", "CXXX",
            "CXXXI", "CXXXII", "CXXXIII", "CXXXIV", "CXXXV", "CXXXVI", "CXXXVII", "CXXXVIII", "CXXXIX", "CXL",
            "CXLI", "CXLII", "CXLIII", "CXLIV", "CXLV", "CXLVI", "CXLVII", "CXLVIII", "CXLIX", "CL"
    };

    public static int romanToArabic(String roman) {
        for (int i = 0; i < romanNumerals.length; i++) {
            if (romanNumerals[i].equals(roman)) {
                return i + 1;
            }
        }
        throw new IllegalArgumentException("Неверное римское число.");
    }

    public static String arabicToRoman(int number) {
        if (number < 1 || number > 100) {
            throw new IllegalArgumentException("Число должно быть от 1 до 10 включительно.");
        }
        return romanNumerals[number - 1];
    }
}