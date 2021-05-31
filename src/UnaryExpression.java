/**
 * The type UnaryExpression.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-16
 */
public abstract class UnaryExpression extends BaseExpression implements Expression {

    /**
     * Private. An unary expression holds one expression.
     */
    private Expression expression;

    /**
     * Instantiates a new Binary expression and add the variables to it's list.
     *
     * @param expression a value to be set for the expression.
     */
    public UnaryExpression(Expression expression) {
        this.expression = expression;
        if (!expression.getVariables().isEmpty()) {
            super.setVariables(expression.getVariables());
        }
    }

    /**
     * A getter for the expression.
     *
     * @return the expression.
     */
    public Expression getExpression() {
        return expression;
    }

    /**
     * A setter to the expression, allows to set new expression.
     *
     * @param expr a value to be set for expression.
     */
    public void setExpression(Expression expr) {
        this.expression = expr;
    }

}
