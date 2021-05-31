import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * The type Var.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020 -05-16
 */
public class Var implements Expression {

    /**
     * Private. A variable represented by String type.
     */
    private String variable;

    /**
     * Instantiates a new Var which is an atomic expression, a basic variable.
     *
     * @param var a string value to be set for the variable.
     */
    public Var(String var) {
        this.variable = var;
    }

    /**
     * Try to return this correspondent numeric value to this variable according to a map.
     *
     * @param assignment the assignment which include a map of variable-value.
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {
        if (assignment.containsKey(variable) && assignment.get(variable) != null) {
            return assignment.get(variable);
        } else {
            throw new Exception("Var: couldn't find an assigned variable in the map.");
        }
    }

    /**
     * Throw exception because it's impossible to return a variable value without a map.
     *
     * @return a double value if succeed.
     * @throws Exception if couldn't evaluate the value.
     */
    @Override
    public double evaluate() throws Exception {
        throw new Exception("Var: couldn't find a map.");
    }

    /**
     * Return a list with this variable.
     *
     * @return an list of variables (with one variable).
     */
    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();
        vars.add(variable);
        return vars;
    }

    /**
     * Prints the variable as a String.
     *
     * @return a string representation of the expression.
     */
    @Override
    public String toString() {
        return this.variable;
    }

    /**
     * If this variable the given var, replace it with the given expression.
     *
     * @param var        a string that should be replaced with the expression.
     * @param expression the expression to be placed where 'var' is.
     * @return assigned expression.
     */
    @Override
    public Expression assign(String var, Expression expression) {
        if (this.variable.equals(var)) {
            return expression;
        } else {
            return this;
        }
    }

    /**
     * If this variable the given var, return Num(1). else, return Num(0).
     *
     * @param var The variable to derive the expression in relate to.
     * @return the differentiate of the variable.
     */
    @Override
    public Expression differentiate(String var) {
        if (this.variable.equals(var)) {
            return new Num(1);
        } else {
            return new Num(0);
        }
    }

    /**
     * Return this variable numeric value if possible. if not, return this Var.
     *
     * @return as described in the title.
     */
    @Override
    public Expression simplify() {
        try {
            return new Num(this.evaluate());
        } catch (Exception e) {
            return this;
        }
    }

}

