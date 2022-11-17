package ba.unsa.etf.rpr.packageOperator;

/*** Interface  BinaryOperator
 * All instance of this class must have method 'calculate' with two arguments
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public interface BinaryOperator extends Operator {
    Double calculate(Double firstInput, Double secondInput);
}
