package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.packageOperator.*;
import java.util.*;

/*** Class for evaluating arithmetic expression using Dijkstra's Two Stack Algorithm
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class ExpressionEvaluator {
    /*** Two private static fields
     * stack Values is for operands in the expression
     * stack Operators is for operators in the expression
     */
    private static Stack<String> operators;
    private static Stack<Double> values;

    /*** Deafault constructor
     */
    public ExpressionEvaluator () {
        operators = new Stack<>();
        values = new Stack<>();
    }

    /*** Public method for evaluating arithmetic expression
     * @param expression for evaluating
     * @return double results of evaluating
     * @throws RuntimeException if expression isn't valid
     */
    public Double evaluate (String expression) throws RuntimeException {

        /* The input expression splits into a list of strings
         * A space means new string
         */
        ArrayList<String> tokens = new ArrayList<>(Arrays.asList(expression.split(" ")));

        /* Check is the input expression valid, if it isn't throw RuntimeException */
        if (!isExpressionValid (tokens)) {
            throw new RuntimeException("The arithmetic expression isn't valid!");
        }

        /* Do something with tokens
         * Push token in the stack Operators or in the stack Values
         * Calculate the value of the expression
         */
        for (String token : tokens) {
            if (!token.equals(")") && !token.equals("(")) {
                pushToken(token);
            } else if (token.equals(")")) {
                if (operators.size() != 0) {
                    calculateExpression(values.pop(), operators.pop());
                }
            }
        }

        if (values.size() != 1) {
            throw new RuntimeException("The arithmetic expression isn't valid!");
        }
        /* Return result of the arithmetic expression */
        return values.pop();
    }

    /*** Private method for pushing token
     * @param token for pushing
     * If token is operator, push it into the stack Operators
     * Else if token is value, push it into the stack Values
     * */
    private static void pushToken (String token) {

        if (isTokenValue(token)) {
            /* Check is it possible to parse token into Double */
            values.push(Double.parseDouble(token));
        } else {
            /* Token isn't possible to parse into Double */
            operators.push(token);
        }
    }

    /*** Private method for calculating expression
     */
    private static void calculateExpression (Double value, String operator) {

        for (Map.Entry<String, Operator> entry : getValidOperators().entrySet()) {
            if (entry.getKey().equals(operator)) {

                /* If variable operator is equal to the key of the HashMap, check is operator binary or unary
                 * If operator is binary call abstract method 'calculate' with two arguments
                 * If operator is unary call abstract method 'calculate' with one argument
                 * The result push into the stack Value
                 */
                if (isOperatorBinary(entry.getValue())) {
                    if (values.isEmpty()) {
                        throw new RuntimeException ("The arithmetic expression isn't valid.");
                    }
                    values.push(((BinaryOperator) entry.getValue()).calculate(values.pop(), value));
                } else {
                    values.push(((UnaryOperator) entry.getValue()).calculate(value));
                }
            }
        }
    }

    /*** Private static method for checking is token valid
     * @param token that should be checked
     * @return Boolean
     */
    private static Boolean isTokenValid (String token) {

        if (isTokenValue(token)) {
            /* Check is it possible to parse token into Double */
            return true;
        } else if (token.equals("(") || token.equals(")") || isOperatorValid(token)) {
            /* Token isn't possible parse into Double
             * Check is it token equal to ")" or "(" or is token operator
             */
            return true;
        }

        /* Token isn't valid */
        return false;
    }

    /*** Private static method for checking is token value
     * @param token that should be checked
     * @return Boolean
     */
    private static Boolean isTokenValue (String token) {

        try {
            /* Check is it possible parse token into Double */
            Double.parseDouble(token);
            return true;

        } catch (NumberFormatException e) {}

        /* Token isn't value */
        return false;
    }

    /*** Private static method for checking is operator valid
     * @param operator that should be checked
     * @return Boolean
     */
    private static Boolean isOperatorValid (String operator) {

        for (Map.Entry<String, Operator> entry : getValidOperators().entrySet()) {
            if (entry.getKey().equals(operator)) {
                /* Operator is equal to the key, so operator is valid */
                return true;
            }
        }

        /* Operator isn't valid */
        return false;
    }

    /*** Private static method for checking is expression valid
     * @param tokens is expression that should be checked
     * @return Boolean
     */
    private static Boolean isExpressionValid (ArrayList<String> tokens) {

        /* The first and the last token of the expression must be bracket and the expression must have at least one value */
        if (!(tokens.get(0)).equals("(") || !(tokens.get(tokens.size() - 1)).equals(")") || (getNumberOfValues(tokens).equals(0))) {
            return false;
        }

        if (!getNumberOfBrackets(tokens).equals(getNumberOfOperators (tokens))) {

            /* Special cases when the expression have only one number or one unary operation */
            if (getNumberOfOperators(tokens).equals(0) && getNumberOfValues(tokens).equals(1)) {
                return true;
            } else if (getNumberOfOperators(tokens).equals(1) && getNumberOfValues(tokens).equals(1)) {
                return true;
            }

            /* If numbers of brackets ')' are't equal to the numbers of operators in expression, return false */
            return false;
        }

        /* If one of the token in expression isn't valid, return false */
        for (String token : tokens) {
            if (!isTokenValid(token)) {
                return false;
            }
        }

        /* Expression is valid */
        return true;
    }

    /*** Private static method for checking is operator Binary or Unary
     * @param operator that should be checked
     * @return Boolean
     */
    private static Boolean isOperatorBinary (Operator operator) {

        if (operator instanceof BinaryOperator) {
            return true;
        }

        return false;
    }

    /*** Private static method for getting number of brackets
     * @param tokens is expression
     * @return Integer numberOfBrackets
     */
    private static Integer getNumberOfBrackets (ArrayList<String> tokens) {

        Integer numberOfBrackets = 0;
        for (String token : tokens) {
            /* If token is equal to ")", increment variable 'numberOfBrackets' */
            if (token.equals(")")) {
                numberOfBrackets++;
            }
        }

        return numberOfBrackets;
    }

    /*** Private static method for getting number of operators
     * @param tokens is expression
     * @return Integer numberOfOperators
     */
    private static Integer getNumberOfOperators (ArrayList<String> tokens) {

        Integer numberOfOperators = 0;
        for (String token : tokens) {
            /* If token is valid operator, increment variable 'numberOfOperators' */
            if (isOperatorValid(token)) {
                numberOfOperators++;
            }
        }

        return numberOfOperators;
    }

    /*** Private static method for getting number of values
     * @param tokens is expression
     * @return Integer numberOfValues
     */
    private static Integer getNumberOfValues (ArrayList<String> tokens) {

        Integer numberOfValues = 0;
        for (String token : tokens) {
            /* If token is value, increment variable 'numberOfValues' */
            if (isTokenValue(token)) {
                numberOfValues++;
             }
            }

        return numberOfValues;
    }

    /*** Private static method for getting hashMap of validOperators
     * @return hashMap of valid operators, where the key is String and the value is object of interface Operators*/
    private static HashMap<String, Operator> getValidOperators () {
        return new HashMap<String, Operator>() {
            {
                put("+", new Addition());
                put("-", new Subtraction());
                put("*", new Multiplication());
                put("/", new Division());
                put("sqrt", new Sqrt());
            }
        };
    }

    /*** Getter method
     * @return stack 'operators'*/
    public static Stack<String> getOperators() {
        return operators;
    }

    /*** Setter method
     * @param operators that are Strings
     **/
    public static void setOperators(Stack<String> operators) {
        ExpressionEvaluator.operators = operators;
    }

    /*** Getter method
     * @return stack 'values'*/
    public static Stack<Double> getValues() {
        return values;
    }

    /*** Setter method
     * @param values that are Doubles
     */
    public static void setValues(Stack<Double> values) {
        ExpressionEvaluator.values = values;
    }
}

