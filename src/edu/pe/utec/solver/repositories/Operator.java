package edu.pe.utec.solver.repositories;

import java.util.Stack;

public class Operator {
    private static Stack<String> operator = new Stack<String>();

    public static Stack<String> getStack() {
        return operator;
    }
}
