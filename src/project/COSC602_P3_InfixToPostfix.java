package project;

import structure.*;
import java.io.*;

/**
 * @author Steven Hogenson on 4/7/2022
 * @project StevenHogenson_Java
 */
public class COSC602_P3_InfixToPostfix {
    private static boolean validExpression;

    /**
     * test method to call/run from Main class
     */
    public static void test() throws IOException {
        validExpression = true;
        String fileName = "../COSC602_P3_InfixInput.txt";
        BufferedReader br = null;
        FileWriter writer = new FileWriter("../COSC602_P3_PostfixOutput.txt");
        try {
            br = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("**FILE NOT FOUND**");
        }

        String line;
        String s;
        while (((line = br.readLine()) != null)) {
            if (line.length() > 0) {
                validExpression = true;

                s = line.replaceAll("\\s+", " ");
                writer.write("Original Infix:         " + s + "\n");
                String postFix = infixToPostfix(s);
                if (validExpression) {
                    writer.write("Corresponding Postfix:  " + postFix + "\n");
                    writer.write("Evaluation Result:      = " + evaluatePostfix(postFix) + "\n\n\n");

                } else {
                    writer.write("**Invalid expression**\n\n\n");
                }
            }
        }
        br.close();
        writer.close();
    }

    /**
     * Converts the given infix expression into the appropriate postfix expression.
     * If infixExpr is not a valid expression, then it is not converted.
     *
     * @param infixExpr the infix expression that's to be converted to postfix
     * @return a String that represents the postfix expression
     */
    static String infixToPostfix(String infixExpr) {
        String infix = infixExpr.replaceAll("\\s", "");
        MyStack ms = new MyStack();
        //counts operators and operands to compare the amount later
        int numOperators = 0;
        int numOperands = 0;
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < infix.length(); i++) {
            if (isOperator(infix.charAt(i))) {
                numOperators++;
                while (!ms.isEmpty() && priority(infix.charAt(i)) <= priority((Character) ms.top())) {
                    postfix.append(ms.pop()).append(" ");
                }
                ms.push(infix.charAt(i));
            } else if (infix.charAt(i) == '(') {
                ms.push(infix.charAt(i));
            } else if (infix.charAt(i) == ')') {
                char pop = (Character) ms.pop();
                try {
                    while (pop != '(') {
                        postfix.append(pop).append(" ");
                        pop = (Character) ms.pop();
                    }
                } catch (NullPointerException e) {
                    //if there is a closed parenthesis but no open one
                    validExpression = false;
                }
            } else {
                numOperands++;
                postfix.append(infix.charAt(i)).append(" ");
            }
        }
        //if there are any left-over symbols in the stack or an imbalance of operands/operators, then the expression is not valid
        while (!ms.isEmpty()) {
            if ((Character) ms.top() == '(' || isOperand((Character) ms.top()) || numOperands != (numOperators + 1)) {
                validExpression = false;
                break;
            }
            postfix.append(ms.pop()).append(" ");
        }
        return postfix.toString();
    }

    /**
     * Determines the priority of the operator
     *
     * @param c the operator Character
     * @return and integer value based on the standard mathematical order of operations
     */
    static int priority(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    /**
     * Determines if the current Character is an operator
     *
     * @param c the Character to analyze
     * @return boolean based on if c is an operator
     */
    static boolean isOperator(char c) {
        switch (c) {
            case '^':
            case '%':
            case '*':
            case '/':
            case '+':
            case '-':
                return true;
        }
        return false;
    }

    /**
     * Determines if the current Character is an operand
     *
     * @param c the Character to analyze
     * @return boolean based on if c is a digit (operand)
     */
    static boolean isOperand(char c) {
        return Character.isDigit(c);
    }

    /**
     * Evaluates the converted postfix expression
     *
     * @param postfixExpr the postfix expression to evaluate
     * @return an integer value of the evaluated postfix expression
     */
    static int evaluatePostfix(String postfixExpr) {
        String postfixExpression = postfixExpr.replaceAll("\\s", "");
        MyStack ms = new MyStack();
        for (int i = 0; i < postfixExpression.length(); i++) {
            if (isOperand(postfixExpression.charAt(i))) {
                ms.push(postfixExpression.charAt(i) - '0');
            } else {
                //when an operator is encountered, pop the top 2 operands from the stack and perform the appropriate operation on them
                int x = (int) ms.pop();
                int y = (int) ms.pop();

                if (postfixExpression.charAt(i) == '+') {
                    ms.push(y + x);
                } else if (postfixExpression.charAt(i) == '-') {
                    ms.push(y - x);
                } else if (postfixExpression.charAt(i) == '*') {
                    ms.push(y * x);
                } else if (postfixExpression.charAt(i) == '/') {
                    ms.push(y / x);
                } else if (postfixExpression.charAt(i) == '%') {
                    ms.push(y % x);
                } else if (postfixExpression.charAt(i) == '^') {
                    ms.push(Math.pow(y, x));
                }
            }
        }
        return (int) ms.pop();
    }
}
