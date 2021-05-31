import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type Num.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Num implements Expression {

    /**
     * Private. A number represented by double type.
     */
    private double num;

    /**
     * Instantiates a new Num which is an atomic expression, a basic number.
     *
     * @param num a numeric value to be set for the number.
     */
    public Num(double num) {
        this.num = num;
    }

    /**
     * Return this number.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.num;
    }

    /**
     * Return this number.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        return this.num;
    }

    /**
     * Return an empty list because an atomic number doesn't have any variables.
     *
     * @return an empty list of variables.
     */
    @Override
    public List<String> getVariables() {
        return new LinkedList<>();
    }

    /**
     * Prints the number as a String.
     *
     * @return a string representation of the expression.
     */
    @Override
    public String toString() {
        return String.valueOf(this.num);
    }

    /**
     * A number cannot be assigned so return this number as an expression.
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return this Num.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Returns 0 because the derivative of any atomic number is 0.
     *
     * @param var The variable to derive the expression in relate to.
     * @return Num(0).
     */
    @Override
    public Expression differentiate(String var) {
        return new Num(0);
    }

    /**
     * Return this Num because an atomic number is simplified already.
     *
     * @return this Num.
     */
    @Override
    public Expression simplify() {
        return this;
    }


}
