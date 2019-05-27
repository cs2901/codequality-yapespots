package edu.pe.utec.solver.operations;


public class Division implements Operation {
    @Override
    public double operation(int operando1, int operando2) {
        if (operando2 == 0) {
            throw new IllegalArgumentException("El divisor no puede ser 0.");
        }

        return operando1 / operando2;
    }
}
