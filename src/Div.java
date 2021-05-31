import java.util.List;
import java.util.Map;

/**
 * The type Div.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Div extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Div for division operation.
     *
     * @param expression1 the first sub-expression of the division.
     * @param expression2 the second sub-expression of the division.
     */
    public Div(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the division using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (Math.abs(getExpression2().evaluate(assignment)) <= super.getEpsilon()) {
            throw new Exception("Div: zero division error.");
        }
        return getExpression1().evaluate(assignment) / getExpression2().evaluate(assignment);
    }

    /**
     * Evaluate the division without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        if (Math.abs(getExpression2().evaluate()) <= super.getEpsilon()) {
            throw new Exception("Div: zero division error.");
        }
        return getExpression1().evaluate() / getExpression2().evaluate();
    }

    /**
     * A getter to Div's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints (x / y) nicely, where x and y are the expressions.
     *
     * @return a string representation of division expression.
     */
    @Override
    public String toString() {
        return "(" + getExpression1() + " / " + getExpression2() + ")";
    }

    /**
     * Assign expression to division expression.
     *
     * <p>Returns a new division in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned division expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Div(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * Returns division derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated division expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression mult1 = new Mult(getExpression1().differentiate(var), getExpression2());
        Expression mult2 = new Mult(getExpression1(), getExpression2().differentiate(var));
        Expression count = new Minus(mult1, mult2);
        Expression denom = new Pow(getExpression2(), new Num(2));
        return new Div(count, denom);
    }

    /**
     * Return readable version current division.
     *
     * @return the simplified division expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            String counter = getExpression1().simplify().toString();
            String denomin = getExpression2().simplify().toString();
            if (counter.equals(denomin)) {
                return new Num(1);
            }
            if (denomin.equals("1.0")) {
                return getExpression1().simplify();
            }
            return new Div(getExpression1().simplify(), getExpression2().simplify());
        }
    }

}
