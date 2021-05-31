import java.util.List;
import java.util.Map;

/**
 * The type Pow.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Pow extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Pow for power operation.
     *
     * @param expression1 the first sub-expression of the power.
     * @param expression2 the second sub-expression of the power.
     */
    public Pow(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the power using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double base = getExpression1().evaluate(assignment);
        double expo = getExpression2().evaluate(assignment);
        return Math.pow(base, expo);

    }

    /**
     * Evaluate the power without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        double base = getExpression1().evaluate();
        double expo = getExpression2().evaluate();
        return Math.pow(base, expo);
    }

    /**
     * A getter to Pow's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints (x^y) nicely, where x is the base and y is the exponent.
     *
     * @return a string representation of power expression.
     */
    @Override
    public String toString() {
        return "(" + getExpression1() + "^" + getExpression2() + ")";
    }

    /**
     * Assign expression to power expression.
     *
     * <p>Returns a new power in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned power expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Pow(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * Returns power derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated power expression.
     */
    @Override
    public Expression differentiate(String var) {

        Expression e = new Var("e");
        Expression f = getExpression1();
        Expression g = getExpression2();

        Expression factor1 = new Pow(f, g);
        Expression fDiff = f.differentiate(var);
        Expression div = new Div(g, f);
        Expression mult1 = new Mult(fDiff, div);
        Expression gDiff = g.differentiate(var);
        Expression lnf = new Log(e, f);
        Expression mult2 = new Mult(gDiff, lnf);
        Expression factor2 = new Plus(mult1, mult2);

        return new Mult(factor1, factor2);
    }

    /**
     * Return readable version current power.
     *
     * @return the simplified power expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            String base = getExpression1().simplify().toString();
            String expo = getExpression2().simplify().toString();
            if (!base.equals("0.0") && expo.equals("0.0")) {
                return new Num(1);
            }
            if (!base.equals("0.0") && expo.equals("1.0")) {
                return getExpression1().simplify();
            }
            return new Pow(getExpression1().simplify(), getExpression2().simplify());
        }
    }

}
