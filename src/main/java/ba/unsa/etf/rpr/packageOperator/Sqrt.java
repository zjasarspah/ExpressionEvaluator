package ba.unsa.etf.rpr.packageOperator;

/*** Public class Sqrt implements interface UnaryOperator
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class Sqrt implements UnaryOperator {
    @Override
    public Double calculate(Double input) {
        return Math.sqrt(input);
    }

}
