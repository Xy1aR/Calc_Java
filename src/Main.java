import java.util.Scanner;

class MyException extends Exception {
    public MyException(String desc) {
        super(desc);
    }
}

public class Main {

    public static int [] RomanToArabic(String num) throws NumberFormatException, MyException {

        String [] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        int i = 0;
        int[] arr = new int[2];

        for (String elem : romans) {
            i += 1;
            if (num.equals(elem)) {
                arr[0] = i;
                arr[1] = 1;
                return arr;
            }
        }

        try {
            arr[0] = Integer.parseInt(num);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("A natural number is required.");
        }

        if (arr[0] > 10 || arr[0] < 1) {
            throw new MyException("A numbers must be natural between 1 and 10.");
        }

        return arr;
    }

    public static String ArabicToRoman(int num) throws MyException {

        if (num <= 0) {
            throw new MyException("Roman number must be natural.");
        }

        String [] romans = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
                "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
                "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
                "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        return romans[num - 1];
    }

    public static String calc(String input) throws NumberFormatException, ArithmeticException, MyException {
        int answer = 0;
        String [] exp_parts = input.split(" ");

        if (exp_parts.length != 3) {
            throw new MyException("This app is only for calculating two numbers.");
        }

        int[] first_num = RomanToArabic(exp_parts[0]);
        int[] second_num = RomanToArabic(exp_parts[2]);

        if (first_num[1] != second_num[1]) {
            throw new MyException("Numbers must be in the same number system.");
        }

        String oper = exp_parts[1];

        switch (oper) {
            case "+":
                answer += first_num[0] + second_num[0];
                break;
            case "-":
                answer += first_num[0] - second_num[0];
                break;
            case "*":
                answer += first_num[0] * second_num[0];
                break;
            case "/":
                try {
                    answer += first_num[0] / second_num[0];
                    break;
                } catch (ArithmeticException e) {
                    throw new ArithmeticException("Devided by zero exception.");
                }

        }

        if (first_num[1] == 1) {
            return ArabicToRoman(answer);
        } else {
            return String.valueOf(answer);
        }
    }

    public static void main(String[] args) {
        System.out.println("Print Stop to stop the program");
        Scanner in = new Scanner(System.in);

        while (true) {
            String exp = in.nextLine();
            if (exp.equals("Stop")) {
                System.out.println("The program was stopped by user.");
                break;
            }

            try {
                String answer = calc(exp);
                System.out.println(answer);
            } catch (Exception e) {
                System.out.println("An exception was raised. " + e);
            }
        }
    }
}