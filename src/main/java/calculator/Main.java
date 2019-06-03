import parser.*;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        String operation;
        System.out.println("Ingrese la operacion a resolver");
        operation = keyboard.next();
        Parser calculator = new Parser();
        calculator.toPostfix(operation);
    }
}
