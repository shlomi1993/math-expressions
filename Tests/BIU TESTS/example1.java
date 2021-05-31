import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Var implements Expression {

    private String var;

    public Var(String var) {
        this.var = var;
    }

    @Override
    public double evaluate(Map<String, Double> assignment) throws Exception {

        if (!assignment.containsKey(var)) {
            throw new Exception("Var: couldn't find variable in the map.");
        } else if (assignment.get(var) == null) {
            throw new Exception("Var: the variable wasn't assigned.");
        } else {
            return assignment.get(var);
        }

    }

    @Override
    public double evaluate() throws Exception {
        throw new Exception("Var: couldn't find a map.");
    }

    @Override
    public List<String> getVariables() {
        List<String> vars = new LinkedList<String>();
        vars.add(var);
        return vars;
    }

    @Override
    public String toString() {
        return this.var;
    }

    @Override
    public Expression assign(String var, Expression expression) {
        if (this.var.equals(var)) {
            return expression;
        } else {
            return new Var(this.var);
        }
    }

}
