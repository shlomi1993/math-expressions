import java.util.LinkedList;
import java.util.List;

/**
 * The type BaseExpression.
 *
 * @author Shlomi Ben-Shushan <shlomiggl @ gmail.com>
 * @version 1.0
 * @since 2020-05-16
 */
public abstract class BaseExpression {

    /**
     * Every expression in the assignment should return it's own list of variables.
     */
    private List<String> vars = new LinkedList<String>();

    /**
     * A getter to the list.
     *
     * @return the variables
     */
    public List<String> getVariables() {
        return vars;
    }

    /**
     * A setter to the list.
     *
     * @param list a list of variables to be added to 'vars' list.
     */
    public void setVariables(List<String> list) {
        vars.addAll(list);
    }

    /**
     * A getter for "epsilon" value for equality check deviation.
     *
     * @return 10 power -14.
     */
    public double getEpsilon() {
        return Math.pow(10, -14);
    }

}
