import java.util.List;
import java.util.Map;

/**
 * The type Minus.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Minus extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Minus for subtraction operation.
     *
     * @param expression1 the first sub-expression of the subtraction.
     * @param expression2 the second sub-expression of the subtraction.
     */
    public Minus(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the subtraction using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getExpression1().evaluate(assignment) - getExpression2().evaluate(assignment);
    }

    /**
     * Evaluate the subtraction without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        return getExpression1().evaluate() - getExpression2().evaluate();
    }

    /**
     * A getter to Minus's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints (x - y) nicely, where x and y are the expressions.
     *
     * @return a string representation of subtraction expression.
     */
    @Override
    public String toString() {
        return "(" + getExpression1() + " - " + getExpression2() + ")";
    }

    /**
     * Assign expression to subtraction expression.
     *
     * <p>Returns a new subtraction in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned subtraction expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Minus(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * Returns subtraction derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated subtraction expression.
     */
    @Override
    public Expression differentiate(String var) {
        return new Minus(getExpression1().differentiate(var), getExpression2().differentiate(var));
    }

    /**
     * Return readable version current subtraction.
     *
     * @return the simplified subtraction expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            if (getExpression1().simplify().toString().equals(getExpression2().simplify().toString())) {
                return new Num(0);
            }
            if (getExpression1().simplify().toString().equals("0.0")) {
                return new Neg(getExpression2().simplify());
            }
            if (getExpression2().simplify().toString().equals("0.0")) {
                return getExpression1().simplify();
            }
            return new Minus(getExpression1().simplify(), getExpression2().simplify());
        }
    }

}
