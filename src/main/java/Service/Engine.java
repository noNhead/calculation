package Service;

import static Service.StringToExpression.Postfix;
import static Service.StringToExpression.calc;

public class Engine {
    public static double mainProcess(String string) throws DivisionByZeroException, IllegalExpressionException, IllegalExpressionPositionException { //Процесс обрабатывающий консоль и вызывающий все остальные методы.
        ExceptionCheck.parserCheck(string);

        //System.out.println(calc(Postfix(string)));
        return calc(Postfix(string));
    }
}
