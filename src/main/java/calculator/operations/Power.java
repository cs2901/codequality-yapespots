package operations;

public class Power implements Operation{
    @Override
    public double operation(double operando1, double operando2) {
        return Math.pow(operando1,operando2);
    }
}
