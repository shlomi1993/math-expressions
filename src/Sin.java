import java.util.List;
import java.util.Map;

/**
 * The type Sin.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-16
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Instantiates a new Sin for sine operation.
     *
     * @param expression the expression to be set inside the sine.
     */
    public Sin(Expression expression) {
        super(expression);
    }

    /**
     * Evaluate the sine using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double val = getExpression().evaluate(assignment);
        double angle = Math.toRadians(val);
        double res = Math.sin(angle);
        if (Math.abs(res) <= super.getEpsilon()) {
            return 0;
        }
        return res;
    }

    /**
     * Evaluate the sine without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        double angle = Math.toRadians(getExpression().evaluate());
        double res = Math.sin(angle);
        if (Math.abs(res) <= super.getEpsilon()) {
            return 0;
        }
        return res;
    }

    /**
     * A getter to Sin's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints sin(x) nicely, where x is the expression.
     *
     * @return a string representation of sine expression.
     */
    @Override
    public String toString() {
        return "sin(" + getExpression() + ")";
    }

    /**
     * Assign expression to sine expression.
     *
     * <p>Returns a new sine in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned sine expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Sin(getExpression().assign(var, expression));
    }

    /**
     * Returns sine derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated sine expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression externalDiff = new Cos(getExpression());
        Expression innerDiff = getExpression().differentiate(var);
        return new Mult(externalDiff, innerDiff);
    }

    /**
     * Return readable version current sine.
     *
     * @return the simplified sine expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return new Sin(getExpression().simplify());
        }
    }

}
