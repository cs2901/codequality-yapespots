package edu.pe.utec.solver.repositories;

import java.util.Stack;

public class Calculator {
    private static Stack<Double> calculator = new Stack<Double>();

    public static Stack<Double> getStack() {
        return calculator;
    }
}
