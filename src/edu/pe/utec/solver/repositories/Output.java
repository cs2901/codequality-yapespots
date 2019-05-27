package edu.pe.utec.solver.repositories;

import java.util.Stack;

public class Output {
    private static Stack<String> output = new Stack<String>();

    public static Stack<String> getStack() {
        return output;
    }
}
