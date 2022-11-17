package ba.unsa.etf.rpr.packageOperator;

/*** Public class Addition implements interface BinaryOperator
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class Addition implements BinaryOperator {
    @Override
    public Double calculate(Double firstInput, Double secondInput) {
        return firstInput + secondInput;
    }
}
