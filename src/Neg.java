import java.util.List;
import java.util.Map;

/**
 * The type Neg.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-16
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Neg for negative operation.
     *
     * @param expression the expression to be set inside the negative.
     */
    public Neg(Expression expression) {
        super(expression);
    }

    /**
     * Evaluate the negative using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return (-1) * getExpression().evaluate(assignment);
    }

    /**
     * Evaluate the negative without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        return (-1) * getExpression().evaluate();
    }

    /**
     * A getter to Neg's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints (-x) nicely, where x is the expression.
     *
     * @return a string representation of negative expression.
     */
    @Override
    public String toString() {
        return "(-" + getExpression() + ")";
    }

    /**
     * Assign expression to negative expression.
     *
     * <p>Returns a new negative in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned negative expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Neg(getExpression().assign(var, expression));
    }

    /**
     * Returns negative derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated negative expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression negDiff = new Mult(new Num(-1), getExpression());
        return negDiff.differentiate(var);
    }

    /**
     * Return readable version current negative.
     *
     * @return the simplified negative expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return new Neg(getExpression().simplify());
        }
    }

}
