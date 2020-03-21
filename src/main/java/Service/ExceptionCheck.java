package Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionCheck {
    public static void parserCheck(String string) throws DivisionByZeroException, IllegalExpressionException, IllegalExpressionPositionException {
        Matcher m = Pattern.compile("[^-+*/^0-9\\s()]").matcher(string);
        if (m.find()) {
            throw new IllegalExpressionException("Выражение неверно построенно, в выражении не может быть симоволов, кроме операторов и операндов");
        } else {
            m = Pattern.compile("^([(]+?\\d+[)]?+\\s[-+*/]\\s[(]+?\\d+[)]+?)+$").matcher(string);
            if (m.find()) {
                throw new IllegalExpressionPositionException("Строка должна быть в порядке \"операнд оператор операнд ... оператор операнд\", первый символ не может быть закрывающей, а последний открывающей скобкой, также скобки должны закрываться");
            } else {
                int count = 0;
                for (int i = 0; i < string.length(); i++) {
                    if (string.charAt(i) == '('){
                        count++;
                    }
                    if (string.charAt(i) == ')'){
                        count--;
                    }
                }
                if (count != 0) {
                    throw new IllegalExpressionPositionException("Есть не закрытые скобки");
                } else {
                    if (string.startsWith(")") || string.endsWith("(")) {
                        throw new IllegalExpressionPositionException("Строка не может начинаться с закрывающейся скобки, и, наоборот, не может заканчиваться открывающейся скобкой");
                    } else {
                        m = Pattern.compile("/\\s0").matcher(string);
                        if (m.find()) {
                            throw new DivisionByZeroException("Деление на ноль не допустимо");
                        }
                    }
                }
            }
        }
    } // Проверяет на возможные ошибки
}
