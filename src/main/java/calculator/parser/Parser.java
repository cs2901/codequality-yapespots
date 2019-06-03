package parser;

import repositories.*;
import operations.*;

import static java.lang.Character.isDigit;

public class Parser {

    private Operator operator = new Operator();
    private Calculator calculator = new Calculator();
    private Output output = new Output();

    public Parser() {
    }

    private void stackToOutput(boolean bracket) {
        do {
            if (operator.getStack().lastElement().equals("(")) {
                if (bracket) {
                    operator.getStack().pop();
                }
                break;
            }
            output.getStack().push(operator.getStack().lastElement());
            operator.getStack().pop();
        }while (!operator.getStack().empty());
    }

    private void calculate(){
        Sum sumCalculator = new Sum();
        Substract substractCalculator = new Substract();
        Multiplication multiplicationCalculator = new Multiplication();
        Division divisionCalculator = new Division();
        Power powerCalculator = new Power();

        do {
            try {
                calculator.getStack().push(Double.parseDouble(operator.getStack().lastElement()));
                operator.getStack().pop();
            }catch (Exception e){
                double aloneNum = 0, num2=0, num1=0;
                if (calculator.getStack().size() == 1) {
                    aloneNum = calculator.getStack().lastElement();
                    calculator.getStack().pop();
                }else{
                    num2 = calculator.getStack().lastElement();
                    calculator.getStack().pop();
                    num1 = calculator.getStack().lastElement();
                    calculator.getStack().pop();
                }
                boolean isDivision = operator.getStack().lastElement().equals("/");
                boolean isPower = operator.getStack().lastElement().equals("^");
                boolean isMultiplication = operator.getStack().lastElement().equals("*");
                boolean isSubstract = operator.getStack().lastElement().equals("-");
                boolean isAdd = operator.getStack().lastElement().equals("+");
                if (isAdd) {
                    if (aloneNum != 0) {
                        calculator.getStack().push(aloneNum);
                    }else {
                        calculator.getStack().push(sumCalculator.operation(num1,num2));
                    }
                }else if (isSubstract) {
                    if (aloneNum != 0) {
                        calculator.getStack().push(aloneNum*-1);
                    }else {
                        calculator.getStack().push(substractCalculator.operation(num1,num2));
                    }
                }else if (isMultiplication) {
                    calculator.getStack().push(multiplicationCalculator.operation(num1,num2));
                }else if (isDivision) {
                    calculator.getStack().push(divisionCalculator.operation(num1,num2));
                }else if (isPower) {
                    calculator.getStack().push(powerCalculator.operation(num1,num2));
                }
                operator.getStack().pop();
            }
        }while (!operator.getStack().empty());
        System.out.println("La respuesta es: " + calculator.getStack().lastElement());
    }

    public void toPostfix(String query) {
        boolean nextNum = false;
        int querySize = query.length();

        for (int i = 0; i < querySize; i++) {
            if (isDigit(query.charAt(i))) {
                if (nextNum) {
                    output.getStack().set(0,output.getStack().lastElement() + query.charAt(i));
                }else{
                    output.getStack().push(String.valueOf(query.charAt(i)));
                }
                try {
                    nextNum = isDigit(query.charAt(i + 1));
                }catch (Exception e) {
                    nextNum = false;
                }
            }else if (query.charAt(i) == '.') {
                output.getStack().set(0, output.getStack().lastElement() + query.charAt(i));
                nextNum = true;
            }else if (query.charAt(i) == '(') {
                operator.getStack().push(String.valueOf(query.charAt(i)));
            }else if (query.charAt(i) == ')') {
                stackToOutput(true);
            }else {
                boolean isDivision = false;
                boolean isPower = false;
                boolean isMultiplication = false;
                boolean isSubstract = false;
                if (!operator.getStack().empty()) {
                    isDivision = operator.getStack().lastElement().equals("/");
                    isPower = operator.getStack().lastElement().equals("^");
                    isMultiplication = operator.getStack().lastElement().equals("*");
                    isSubstract = operator.getStack().lastElement().equals("-");
                }
                if (query.charAt(i) == '*') {
                    if (!operator.getStack().empty()) {
                        if (isDivision || isPower) {
                            stackToOutput(false);
                        }
                    }
                }else if (query.charAt(i) == '-') {
                    if (!operator.getStack().empty()) {
                        if (isMultiplication || isPower || isSubstract || isDivision) {
                            stackToOutput(false);
                        }
                    }
                    if (query.charAt(i+1) == '-') {
                        query = query.replaceAll(query, query.substring(0,i) + '|' + query.substring(i+1));
                        query = query.replaceAll(query, query.substring(0,i+1) + '+' + query.substring(i+2));
                    }else if (query.charAt(i+1) == '+') {
                        query = query.replaceAll(query, query.substring(0,i) + '|' + query.substring(i+1));
                        query = query.replaceAll(query, query.substring(0,i+1) + '-' + query.substring(i+2));
                    }
                }else if (query.charAt(i) == '+') {
                    if (!operator.getStack().empty()) {
                        if (isMultiplication || isPower || isSubstract || isDivision) {
                            stackToOutput(false);
                        }
                    }
                    if (query.charAt(i+1) == '-') {
                        query = query.replaceAll(query, query.substring(0,i) + '|' + query.substring(i+1));
                        query = query.replaceAll(query, query.substring(0,i+1) + '-' + query.substring(i+2));
                    }else if (query.charAt(i+1) == '+') {
                        query = query.replaceAll(query, query.substring(0,i) + '|' + query.substring(i+1));
                    }
                }
                if (query.charAt(i) != '|') {
                    operator.getStack().push(String.valueOf(query.charAt(i)));
                }
            }
        }
        stackToOutput(false);
        do {
            operator.getStack().push(output.getStack().lastElement());
            output.getStack().pop();
        }while (!output.getStack().empty());
        calculate();
    }
}
