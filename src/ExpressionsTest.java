import java.util.Map;
import java.util.TreeMap;

/**
 * The required ExpressionsTest.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class ExpressionsTest {

    /**
     * A main method that does:
     *   1) Create the expression (2x) + (sin(4y)) + (e^x).
     *   2) Print the expression.
     *   3) Print the value of the expression with (x=2,y=0.25,e=2.71).
     *   4) Print the differentiated expression according to x.
     *   5) Print the value of the differentiated expression according to x with the assignment above.
     *   6) Print the simplified differentiated expression.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        Map<String, Double> assignment = new TreeMap<String, Double>();

        Expression x = new Var("x");
        Expression y = new Var("y");
        Expression e = new Var("e");

        Expression exp = new Plus(new Plus(new Mult(new Num(2), x), new Sin(new Mult(new Num(4), y))), new Pow(e, x));
        Expression expDiff = exp.differentiate("x");
        Expression expDiffSimp = exp.differentiate("x").simplify();

        System.out.println(exp);

        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        assignment.put("e", 2.71);

        try {
            System.out.println(exp.evaluate(assignment));
        } catch (Exception t) {
            t.printStackTrace();
        }

        System.out.println(expDiff);

        try {
            System.out.println(expDiff.evaluate(assignment));
        } catch (Exception t) {
            t.printStackTrace();
        }

        System.out.println(expDiffSimp);


    }

}