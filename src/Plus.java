import java.util.List;
import java.util.Map;

/**
 * The type Plus.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Plus extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Plus for addition operation.
     *
     * @param expression1 the first sub-expression of the addition.
     * @param expression2 the second sub-expression of the addition.
     */
    public Plus(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the addition using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getExpression1().evaluate(assignment) + getExpression2().evaluate(assignment);
    }

    /**
     * Evaluate the addition without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        return getExpression1().evaluate() + getExpression2().evaluate();
    }

    /**
     * A getter to Plus's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints (x + y) nicely, where x and y are the expressions.
     *
     * @return a string representation of addition expression.
     */
    @Override
    public String toString() {
        return "(" + getExpression1() + " + " + getExpression2() + ")";
    }

    /**
     * Assign expression to addition expression.
     *
     * <p>Returns a new addition in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned addition expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Plus(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * Returns addition derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated addition expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Plus(getExpression1().differentiate(var), getExpression2().differentiate(var));
    }

    /**
     * Return readable version current addition.
     *
     * @return the simplified addition expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            String exp1 = getExpression1().simplify().toString();
            String exp2 = getExpression2().simplify().toString();
            if (exp1.equals("0.0") || exp1.equals("(-0.0)")) {
                return getExpression2().simplify();
            }
            if (exp2.equals("0.0") || exp2.equals("(-0.0)")) {
                return getExpression1().simplify();
            }
            return new Plus(getExpression1().simplify(), getExpression2().simplify());
        }
    }

}
