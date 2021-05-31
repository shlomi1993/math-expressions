import java.util.List;
import java.util.Map;

/**
 * The type Log.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Log extends BinaryExpression implements Expression {

    /**
     * Instantiates a new Log for logarithm operation.
     *
     * @param expression1 the first sub-expression of the logarithm.
     * @param expression2 the second sub-expression of the logarithm.
     */
    public Log(Expression expression1, Expression expression2) {
        super(expression1, expression2);
    }

    /**
     * Evaluate the logarithm using provided map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        double base = getExpression1().evaluate(assignment);
        if (base < 0 || base == 1) {
            throw new Exception("Log: base cannot be 1 or lower than 0.");
        }
        double argu = getExpression2().evaluate(assignment);
        if (argu <= 0) {
            throw new Exception("Log: argument cannot be 0 or lower.");
        }
        return Math.log(argu) / Math.log(base);

    }

    /**
     * Evaluate the logarithm without a provided map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        double base = getExpression1().evaluate();
        if (base < 0 || base == 1) {
            throw new Exception("Log: base cannot be 1 or lower than 0.");
        }
        double argu = getExpression2().evaluate();
        if (argu <= 0) {
            throw new Exception("Log: argument cannot be 0 or lower.");
        }
        return Math.log(argu) / Math.log(base);
    }

    /**
     * A getter to Log's list of variables.
     *
     * @return a list of variables.
     */
    @Override
    public List<String> getVariables() {
        return super.getVariables();
    }

    /**
     * Prints log(x, y) nicely, where x is the base and y is the argument.
     *
     * @return a string representation of logarithm expression.
     */
    @Override
    public String toString() {
        return "log(" + getExpression1() + ", " + getExpression2() + ")";
    }

    /**
     * Assign expression to logarithm expression.
     *
     * <p>Returns a new logarithm in which all occurrences of the variable 'var'
     * are replaced with the provided expression.</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned logarithm expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        return new Log(getExpression1().assign(var, expression), getExpression2().assign(var, expression));
    }

    /**
     * Returns logarithm derivative relative to 'var'.
     *
     * <p>Note: to deal with variables in both of the expressions, I used the complex
     * formula as appeared on the Piazza and approved by T.A (post @1462).</p>
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated logarithm expression.
     */
    @Override
    public Expression differentiate(String var) {

        Expression base = getExpression1();
        Expression argu = getExpression2();
        Expression baseDiff = base.differentiate(var);
        Expression arguDiff = argu.differentiate(var);
        Expression lnBase = new Log(new Var("e"), base);
        Expression lnArgu = new Log(new Var("e"), argu);
        Expression factor1 = new Div(new Mult(lnBase, arguDiff), argu);
        Expression factor2 = new Div(new Mult(lnArgu, baseDiff), base);
        Expression counter = new Minus(factor1, factor2);
        Expression denomin = new Pow(lnBase, new Num(2));

        return new Div(counter, denomin);

    }

    /**
     * Return readable version current logarithm.
     *
     * @return the simplified logarithm expression.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            String base = getExpression1().simplify().toString();
            String argu = getExpression2().simplify().toString();
            if (base.equals(argu)) {
                return new Num(1);
            }
            if (argu.equals("1.0")) {
                return new Num(0);
            }
            return new Log(getExpression1().simplify(), getExpression2().simplify());
        }
    }

}
