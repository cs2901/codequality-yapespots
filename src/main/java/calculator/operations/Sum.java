package operations;

public class Sum implements Operation {
    @Override
    public double operation(double operando1, double operando2) {
        return operando1 + operando2;
    }
}
