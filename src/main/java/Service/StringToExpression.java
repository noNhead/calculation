package Service;

import java.util.*;
import java.util.function.BiFunction;

public class StringToExpression{
    static String Postfix(String infix) {//Превращает строку в ОПЗ
        final String operands = "-+/*^";
        StringBuilder sb = new StringBuilder();
        Stack<Integer> s = new Stack<>();
        for (String token : infix.split("\\s")) {
            if (token.isEmpty())
                continue;
            char c = token.charAt(0);
            int idx = operands.indexOf(c);
            if (idx != -1) {
                if (!s.isEmpty()) {
                    while (!s.isEmpty()) {
                        int prec2 = s.peek() / 2;
                        int prec1 = idx / 2;
                        if (prec2 > prec1 || (prec2 == prec1 && c != '^'))
                            sb.append(operands
                                .charAt(s.pop()))
                                .append(' ');
                        else break;
                    }
                }
                s.push(idx);
            } else if (c == '(') s.push(-2);
                 else if (c == ')') {
                    while (s.peek() != -2) sb.append(operands.charAt(s.pop())).append(' ');
                    s.pop();
                    } else sb.append(token).append(' ');
        }
        while (!s.isEmpty())
            sb.append(operands.charAt(s.pop())).append(' ');
        return sb.toString();
    }

    public static Double calc(String input) {//Решает выражение ОПЗ
        Stack<Double> numbers = new Stack<>();
        Arrays.asList(input.split(" ")).forEach(number -> {
            switch(number) {
                case "+":
                    calcSign(numbers, (n1, n2) -> n2 + n1);
                    break;
                case "-":
                    calcSign(numbers, (n1, n2) -> n2 - n1);
                    break;
                case "*":
                    calcSign(numbers, (n1, n2) -> n2 * n1);
                    break;
                case "/":
                    calcSign(numbers, (n1, n2) -> n2 / n1);
                    break;
                default:
                    numbers.push(Double.parseDouble(number));
            }
        });
        return numbers.pop();
    }

    protected static void calcSign(Stack<Double> numbers, BiFunction<Double, Double, Double> operation) { //Суб процесс
        numbers.push(operation.apply(numbers.pop(), numbers.pop()));
    }
}


