package ba.unsa.etf.rpr.packageOperator;

/*** Public class Multiplication implements interface BinaryOperator
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class Multiplication implements BinaryOperator {
    @Override
    public Double calculate(Double firstInput, Double secondInput) {
        return firstInput * secondInput;
    }
}
