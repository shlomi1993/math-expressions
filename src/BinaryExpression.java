/**
 * The type BinaryExpression.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-16
 */
public abstract class BinaryExpression extends BaseExpression implements Expression {

    /**
     * Privates. A binary expression holds two sub-expressions.
     */
    private Expression exp1;
    private Expression exp2;

    /**
     * Instantiates a new Binary expression and add the variables to it's list.
     *
     * @param expression1 a value to be set for the first sub-expression.
     * @param expression2 a value to be set for the second sub-expression.
     */
    public BinaryExpression(Expression expression1, Expression expression2) {
        this.exp1 = expression1;
        this.exp2 = expression2;
        if (!expression1.getVariables().isEmpty()) {
            super.setVariables(expression1.getVariables());
        }
        if (!expression2.getVariables().isEmpty()) {
            super.setVariables(expression2.getVariables());
        }
    }

    /**
     * A getter to the first expression.
     *
     * @return the expression1.
     */
    public Expression getExpression1() {
        return exp1;
    }

    /**
     * A getter to the second expression.
     *
     * @return the expression2.
     */
    public Expression getExpression2() {
        return exp2;
    }


    /**
     * A setter to the first expression, allows to set new expression.
     *
     * @param expression a value to be set for expression1.
     */
    public void setExpression1(Expression expression) {
        this.exp1 = expression;
    }

    /**
     * A setter to the second expression, allows to set new expression.
     *
     * @param expression a value to be set for expression2.
     */
    public void setExpression2(Expression expression) {
        this.exp2 = expression;
    }

}
