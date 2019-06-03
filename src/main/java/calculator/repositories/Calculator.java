package repositories;

import java.util.Stack;

public class Calculator {
    private Stack<Double> calculator = new Stack<>();

    public Stack<Double> getStack() {
        return calculator;
    }
}
