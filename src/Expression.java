import java.util.List;
import java.util.Map;

/**
 * The interface Expression.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-16
 */
public interface Expression {
    /**
     * Evaluate the expression using provided variable values and return the result.
     *
     * <p>If the expression contains a variable which is not in the assignment,
     * an exception is thrown.</p>
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;

    /**
     * Evaluate the expression without a map.
     *
     * <p>Usually for expressions without variables like (2+8).</p>
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    double evaluate() throws Exception;

    /**
     * A getter to the expression's list of variables.
     *
     * @return a list of variables.
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     *
     * @return a string representation of the expression.
     */
    String toString();

    /**
     * Assign expression.
     *
     * <p>Returns a new expression in which all occurrences of the variable 'var'
     * are replaced with the provided expression (Does not modify the current expression).</p>
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return a new assigned expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression derivative relative to 'var'.
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiated expression.
     */
    Expression differentiate(String var);

    /**
     * Return a more readable version of the current expression.
     *
     * @return the simplified expression.
     */
    Expression simplify();

}