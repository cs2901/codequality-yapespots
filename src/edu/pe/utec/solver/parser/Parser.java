package edu.pe.utec.solver.parser;

import edu.pe.utec.solver.repositories.Calculator;
import edu.pe.utec.solver.repositories.Operator;
import edu.pe.utec.solver.repositories.Output;

import static java.lang.Character.isDigit;

public class Parser {
    private Operator operator;
    private Calculator calculator;
    private Output output;

    public Parser() {
    }

    public void toPostfix(String query) {
        Boolean nextNum = false;
        int querySize = query.length();

        for (int i = 0; i < query.length(); i++) {
            if (isDigit(query.charAt(i))) {
                if (nextNum) {
                    //this.output.getStack().firstElement() = this.output.getStack().firstElement() + query.charAt(i);
                }

                if (isDigit(query.charAt(i + 1))) {
                    nextNum = true;
                } else {
                    nextNum = false;
                }
            }
        }
    }
}
