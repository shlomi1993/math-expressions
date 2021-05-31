import java.util.List;
import java.util.Map;

/**
 * The type Mult.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Mult extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Mult for multiplication operation.
     *
     * @param expression1 the first sub-expression of the multiplication.
     * @param expression2 the second sub-expression of the multiplication.
     */
    public Mult(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the multiplication using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return getExpression1().evaluate(assignment) * getExpression2().evaluate(assignment);
    }

    /**
     * Evaluate the multiplication without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        return getExpression1().evaluate() * getExpression2().evaluate();
    }

    /**
     * A getter to Mult's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints (x * y) nicely, where x and y are the expressions.
     *
     * @return a string representation of multiplication expression.
     */
    @Override
    public String toString() {
        return "(" + getExpression1() + " * " + getExpression2() + ")";
    }

    /**
     * Assign expression to multiplication expression.
     *
     * <p>Returns a new multiplication in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned multiplication expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Mult(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * Returns multiplication derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated multiplication expression.
     */
    @Override
    public Expression differentiate(String var) {
        Expression mult1 = new Mult(getExpression1().differentiate(var), getExpression2());
        Expression mult2 = new Mult(getExpression1(), getExpression2().differentiate(var));
        return new Plus(mult1, mult2);
    }

    /**
     * Return readable version current multiplication.
     *
     * @return the simplified multiplication expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            boolean zeroCase1 = getExpression1().simplify().toString().equals("0.0");
            boolean zeroCase2 = getExpression2().simplify().toString().equals("0.0");
            boolean zeroCase3 = getExpression1().simplify().toString().equals("-0.0");
            boolean zeroCase4 = getExpression1().simplify().toString().equals("-0.0");
            if (zeroCase1 || zeroCase2 || zeroCase3 || zeroCase4) {
                return new Num(0);
            }
            if (getExpression1().simplify().toString().equals("1.0")) {
                return getExpression2().simplify();
            }
            if (getExpression2().simplify().toString().equals("1.0")) {
                return getExpression1().simplify();
            }
            if (getExpression1().simplify().toString().equals("-1.0")) {
                return new Neg(getExpression2().simplify());
            }
            if (getExpression2().simplify().toString().equals("-1.0")) {
                return new Neg(getExpression1().simplify());
            }
            return new Mult(getExpression1().simplify(), getExpression2().simplify());
        }
    }

}
