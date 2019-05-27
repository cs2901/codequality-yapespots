package edu.pe.utec.solver.operations;

public class Multiplication implements Operation {
    @Override
    public double operation(int operando1, int operando2) {
        return operando1 * operando2;
    }
}
