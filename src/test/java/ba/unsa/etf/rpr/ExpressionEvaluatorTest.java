package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/***
 * Test class for class ExpressionEvaluator
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class ExpressionEvaluatorTest {

    /* Test for checking is expression valid */
    @Test
    public void isExpressionValid () {
        assertAll("notValid",
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( 1 + 2 + 3 )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("1 + 2 + 3 ");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( ( 3 * 8 ) + 8 + ( sqrt ( 4 ) + sqrt ( 4 ) ) )");}));
    }

    /* Test for checking are operators in expression valid */
    @Test
    public void areOperatorsValid () {
        assertAll("notValid",
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( 1 % 3 )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("(( 1 + ( 3 * 4 ) )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( 1 + ( 3 * 4 ))");}));
    }

    @Test
    public void divisionWithZero () {
        assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( 1 / ( 3 * 0 ) )");});
    }


    @Test
    public void correctExpressions() {
        assertAll ("correctExpressions",
                () ->assertEquals( 17.0 , new ExpressionEvaluator().evaluate("( 2 + ( 3 * 5 ) )")),
                () ->assertEquals( -4.35 , new ExpressionEvaluator().evaluate("( ( 2.15 + ( 5 / 2 ) ) - 9 )")),
                () -> assertEquals( 19.0 , new ExpressionEvaluator().evaluate("( ( ( 3 * 8 ) + sqrt ( 25 ) ) - 10 )")));
    }

    /* Test for checking is expression valid */
    @Test
    public void isExpressionValid2 () {
        assertAll("notValid",
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( ( 4 + 4 ) )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( + 9 )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( 1 + + ( 2 * 7 ) )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( 1 - ( 7 + 8 ) + 9 + 0 )");}));
    }

    /* The special cases */
    @Test
    public void specialCase () {
        assertAll("special",
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("2");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( + )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("sqrt ( 4 )");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("(2)");}),
                () -> assertThrows(RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("(sqrt ( 4 ))");}),
                () -> assertThrows( RuntimeException.class, () -> {new ExpressionEvaluator().evaluate("( ( ( 3 * 8 ) + sqrt ( 2 5 ) ) - 10 )");}),
                () -> assertEquals(2.0 , new ExpressionEvaluator().evaluate("( 2 )")),
                () -> assertEquals(2.0 , new ExpressionEvaluator().evaluate("( sqrt ( 4 ) )")));
                }
}
