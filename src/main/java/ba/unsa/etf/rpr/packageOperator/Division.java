package ba.unsa.etf.rpr.packageOperator;

/*** Public class Division implements interface BinaryOperator
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class Division implements BinaryOperator {
    @Override
    public Double calculate(Double firstInput, Double secondInput) throws RuntimeException {
        if (secondInput == 0) {
            throw new RuntimeException("The arithmetic expression isn't correct!");
        }
        return firstInput / secondInput;
    }
}
