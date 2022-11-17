package ba.unsa.etf.rpr.packageOperator;

/*** Interface  UnaryOperator
 * All instance of this class must have method 'calculate' with one argument
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public interface UnaryOperator extends Operator {
    Double calculate(Double input) ;
}
