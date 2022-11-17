package ba.unsa.etf.rpr;

/*** Entry point to math program from cmd
 * Operator 'x' is operator for multiplication in cmd
 * @author Zerina Jasarspahic
 * @version 1.0
 */

public class App 
{
    public static void main(String[] args ) {
      try {
          StringBuilder expression = new StringBuilder();
          for (String s : args) {
              if (s.equals("x")) {
                  s = "*";
              }
              expression.append(s);
              expression.append(" ");
          }
          System.out.println("The result of arithmetic expression is: " + new ExpressionEvaluator().evaluate(expression.toString()));
    } catch (RuntimeException e) {
          System.out.println("Exception:" + e);
      }
    }
}
