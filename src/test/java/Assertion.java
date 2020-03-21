import Service.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("Проверка рассчётов калькулятора")
class AssertionCalculation {
    @DisplayName("Сложение")
    @Test
    void addition() throws DivisionByZeroException, IllegalExpressionException, IllegalExpressionPositionException {
        assertEquals(55, Engine.mainProcess("1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9 + 10"));
    }

    @DisplayName("Вычитание")
    @Test
    void subtraction() throws DivisionByZeroException, IllegalExpressionException, IllegalExpressionPositionException {
        assertEquals(-44, Engine.mainProcess("10 - 2 - 3 - 4 - 5 - 6 - 7 - 8 - 9 - 10"));
    }

    @DisplayName("Умножение")
    @Test
    void multiplication() throws DivisionByZeroException, IllegalExpressionException, IllegalExpressionPositionException {
        assertEquals(3628800, Engine.mainProcess("1 * 2 * 3 * 4 * 5 * 6 * 7 * 8 * 9 * 10"));
    }

    @DisplayName("Деление")
    @Test
    void division() throws DivisionByZeroException, IllegalExpressionException, IllegalExpressionPositionException {
        assertEquals(0.5, Engine.mainProcess("10 / 5 / 2 / 2"));
    }
}

@DisplayName("Ожидаемые исключение")
class AssertionException {
    @DisplayName("Повторяющийся оператор")
    @Test
    void doubleOperator(){
        try {
            ExceptionCheck.parserCheck("1 ++ 2");
        } catch (IllegalExpressionPositionException | DivisionByZeroException | IllegalExpressionException e) {
            assertThat(e.getMessage(), equalTo("Строка должна быть в порядке \"операнд оператор операнд ... оператор операнд\", " +
                    "первый символ не может быть закрывающей, а последний открывающей скобкой, также скобки должны закрываться"));
        }
    }

    @DisplayName("Некорректный символ")
    @Test
    void uncorrectlySymbol() {
        try {
            ExceptionCheck.parserCheck("1 + f");
        } catch (IllegalExpressionPositionException | DivisionByZeroException | IllegalExpressionException e) {
            assertThat(e.getMessage(), equalTo("Выражение неверно построенно, в выражении не может быть симоволов, кроме операторов и операндов"));
        }
    }

    @DisplayName("Некорректные скобки")
    @Test
    void incorrectlyBkt () {
        try {
            ExceptionCheck.parserCheck(")1 + 2(");
        } catch (IllegalExpressionPositionException | DivisionByZeroException | IllegalExpressionException e) {
            assertThat(e.getMessage(), equalTo("Строка не может начинаться с закрывающейся скобки, и, наоборот, не может заканчиваться открывающейся скобкой"));
        }
    }

    @DisplayName("Пустая строка")
    @Test
    void lostLine() {
        try {
            ExceptionCheck.parserCheck("");
        } catch (IllegalExpressionPositionException | DivisionByZeroException | IllegalExpressionException e) {
            assertThat(e.getMessage(), equalTo("Выражение неверно построенно, в выражении не может быть симоволов, кроме операторов и операндов"));
        }
    }

    @DisplayName("Деление на ноль")
    @Test
    void divisionByZero() {
        try {
            ExceptionCheck.parserCheck("1 / 0");
        } catch (IllegalExpressionPositionException | DivisionByZeroException | IllegalExpressionException e) {
            assertThat(e.getMessage(), equalTo("Деление на ноль не допустимо"));
        }
    }

}
