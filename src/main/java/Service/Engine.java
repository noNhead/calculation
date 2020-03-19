package Service;

import java.util.Scanner;

import static Service.StringToExpression.Postfix;
import static Service.StringToExpression.calc;

public class Engine {
    public static void mainProcess(){ //Процесс обрабатывающий консоль и вызывающий все остальные методы.
        Scanner in = new Scanner(System.in);
        System.out.print("Введите выражение: ");
        String string = in.nextLine();
        try {
            ExceptionCheck.parserCheck(string);
        } catch (DivisionByZeroException | IllegalExpressionException | IllegalExpressionPositionException e) {
            e.printStackTrace();
            return;
        }
        //System.out.println(Postfix(string)); // Если нужно увидеть ОПЗ
        System.out.println(calc(Postfix(string)));
    }
}
