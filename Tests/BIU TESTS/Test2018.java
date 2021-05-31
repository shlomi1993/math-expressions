
import java.util.Map;
import java.util.TreeMap;

public class Test2018 {
    public static void main(String[] args) {
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 1.0);
        assignment.put("y", 1.0);
        assignment.put("z", 2.0);
        assignment.put("w", 2.0);
        assignment.put("e", Math.E);
        // (((x+y)+z)^2.0)
        Expression ex1 = new Pow(new Plus(new Plus("x", "y"), "z"), new Num(2.0));
        System.out.println(ex1);
        if (!ex1.assign("x", new Num(1)).toString().equals("(((1.0 + y) + z)^2.0)")) {
            System.out.println("wrong assign ex1");
        }
        System.out.println(ex1.differentiate("x"));
        System.out.println(ex1.differentiate("x").simplify());////((((x + y) + z)^2.0) * (2.0 * ((x + y) + z)))
        if (!ex1.differentiate("x").simplify().toString().equals("((((x + y) + z)^2.0) * (2.0 / ((x + y) + z)))")) {
            System.out.println("wrong diff ex1");
        }
        // (((x + y) + z) ^ 2.0)
        Expression ex2 = new Pow(new Plus(new Plus("x", "y"), new Plus("z", "w")), new Num(4.0));
        System.out.println(ex2);
        if (!ex2.assign("x", new Num(1)).toString().equals("(((1.0 + y) + (z + w))^4.0)")) {
            System.out.println("wrong assign ex2");
        }
        // System.out.println(ex2.differentiate("x"));
        // System.out.println(ex2.differentiate("x").simplify());
        if (!ex2.differentiate("x").simplify().toString().equals("((((x + y) + (z + w))^4.0) * (4.0 / ((x + y) + (z + w))))")) {
            System.out.println("wrong diff ex2");
        }
        if (ex2.getVariables().size() != 4) {
            System.out.println("wrong getVar ex2");
        }
        try {
            if (ex2.evaluate(assignment) != 1296) {
                System.out.println("wrong evaluate ex2");
            }
        } catch (Exception e) {
            System.out.println("wrong evaluate ex2");
        }
        // (((2.0 * x) + Sin((4.0 * y))) + (e ^ x))
        Expression ex3 = new Plus(new Plus(new Mult(2.0, "x"), new Sin(new Mult(4.0, "y"))), new Pow("e", "x"));
        System.out.println(ex3);
        // System.out.println(ex3.differentiate("x").simplify());
        if (!ex3.assign("x", new Num(1)).toString().equals("(((2.0 * 1.0) + sin((4.0 * y))) + (e^1.0))")) {
            System.out.println("wrong assign ex3");
        }
        System.out.println(ex2.differentiate("x"));
        System.out.println(ex2.differentiate("x").simplify());
        if (!ex3.differentiate("x").simplify().toString().equals("(2.0 + (e^x))")) {
            System.out.println("wrong diff ex3");
        }
        if (ex3.getVariables().size() != 3) {
            System.out.println("wrong getVar ex3");
        }
        try {
            if (ex3.evaluate(assignment) != (2 + Math.sin(4.0) + Math.E)) {
                System.out.println("wrong evaluate ex3");
            }
        } catch (Exception e) {
            System.out.println("wrong evaluate ex3");
        }
        // (Sin(1.0) + Cos(1.0)) / 10.0
        Expression ex4 = new Div(new Plus(new Sin(1.0), new Cos(1.0)), 10.0);
        System.out.println(ex4);
        try {
            if (ex4.evaluate(assignment) != 0.13817732906760363) {
                //System.out.println(ex4.evaluate(assignment));
                System.out.println("wrong evaluate ex4");
            }
        } catch (Exception e) {
            System.out.println("wrong evaluate ex4");
        }
        // (log((9.0 * x), (9.0 * x)) * (2.0 * y))
        Expression ex5 = new Mult(new Log(new Mult(9.0, "x"), new Mult(9.0, "x")), new Mult(2.0, "y"));
        System.out.println(ex5);
        if (!ex5.simplify().toString().equals("(2.0 * y)")) {
            System.out.println("wrong simplify ex5");
        }
        // (-(-(-(((-2.0 - 0.0) - 4.0) + x))))
        Expression ex6 = new Neg(new Neg(new Neg(new Plus(new Minus(new Minus(-2.0, 0.0), 4.0), "x"))));
        System.out.println(ex6);
        System.out.println(ex6.simplify());
        if (!ex6.simplify().toString().equals("(-(-6.0 + x))")) {
            System.out.println("wrong simplify ex6");
        }
        //(((x^2.0) + (y^2.0)) - (z^3.0))
        Expression ex7 = new Minus(new Plus(new Pow("x", 2.0), new Pow("y", 2.0)), new Pow("z", 3.0));
        System.out.println(ex7.assign("y", new Num(1)));
        try {
            if (ex7.evaluate(assignment) != -6.0) {
                System.out.println("wrong evaluate ex7");
            }
        } catch (Exception e) {
            System.out.println("wrong evaluate ex7");
        }
        if (!ex7.assign("y", new Num(1)).toString().equals("(((x^2.0) + (1.0^2.0)) - (z^3.0))")) {
            System.out.println("wrong assign ex7");
        }
        // log(x, x) / log(x, x)
        Expression ex8 = new Div(new Log("x", "x"), new Log("x", "x"));
        System.out.println(ex8);
        if (!ex8.simplify().toString().equals("1.0")) {
            System.out.println("wrong simplify ex8");
        }
        // (0.0 - (x * 1.0) / 1.0)
        Expression ex9 = new Minus(0.0, new Div(new Mult("x", 1.0), 1.0));
        System.out.println(ex9);
        if (!ex9.simplify().toString().equals("(-x)")) {
            System.out.println("wrong simplify ex9");
        }
        //((Cos(x) + log(e, x)) + (1.0 / x + Sin((x^2.0))))
        Expression ex10 = new Plus(new Plus(new Cos("x"), new Log("e", "x")), new Plus(new Div(1.0, "x"), new Sin(new Pow("x", 2.0))));
        System.out.println(ex10);
        System.out.println(ex10.differentiate("x").simplify());
        if (!ex10.differentiate("x").simplify().toString().equals("(((-sin(x)) + (1.0 / x)) + ((-1.0 / (x^2.0)) + (cos((x^2.0)) * ((x^2.0) * (2.0 / x)))))")) {
            System.out.println("wrong diff ex10");
        }
        // (((3.0 + 6.0) * x) + ((4.0 * x) * Sin(0.0)))
        Expression ex11 = new Plus(new Mult(new Plus(3.0, 6.0), "x"), new Mult(new Mult(4.0, "x"), new Sin(0.0)));
        System.out.println(ex11);
        //System.out.println(ex11.simplify());
        if (!ex11.simplify().toString().equals("(9.0 * x)")) {
            System.out.println("wrong simplify ex11");
        }
        // ((Cos(((5.0 * x)^7.0)) * e) + (y^x / (2.0 + 5.0)))
        Expression ex12 = new Plus(new Mult(new Cos(new Pow(new Mult(5.0, "x"), 7.0)), "e"), new Pow("y", new Div("x", new Plus(2.0, 5.0))));
        System.out.println(ex12);
        //System.out.println(ex12.assign("y", new Num(1)));
        if (!ex12.assign("y", new Num(1)).toString().equals("((cos(((5.0 * x)^7.0)) * e) + (1.0^(x / (2.0 + 5.0))))")) {
            System.out.println("wrong assign ex12");
        }
        try {
            //System.out.println(ex12.evaluate(assignment));
            if (ex12.evaluate(assignment) != 3.696695228304834) {
                System.out.println("wrong evaluate ex12");
            }
        } catch (Exception e) {
            System.out.println("wrong evaluate ex12");
        }
        // (((x * 1.0) * 1.0) + (0.0 - (-(x + 0.0))))
        Expression ex13 = new Plus(new Mult(new Mult("x", 1.0), 1.0), new Minus(0.0, new Neg(new Plus("x", 0.0))));
        System.out.println(ex13);
        System.out.println(ex13.simplify());
        if (!ex13.simplify().toString().equals("(x + x)")) {
            System.out.println("wrong simplify ex13");
        }
        // (((20.0 * (x^5.0)) * (3.0 * (e * x))) * (Cos((x^2.0)) * (x - x)))
        Expression end = new Mult(new Cos(new Pow("x", 2.0)), new Minus("x", "x"));
        Expression start = new Mult(new Mult(20.0, new Pow("x", 5.0)), new Mult(3.0, new Mult("e", "x")));
        Expression ex14 = new Mult(start, end);
        System.out.println(ex14);
        try {
            if (ex14.simplify().evaluate(assignment) != 0) {
                System.out.println("wrong simplify ex14");
            }
        } catch (Exception x) {
            System.out.println("wrong simplify ex14");
        }
        // ex15
        Expression ex15 = new Mult(new Mult(new Pow(new Mult("e", "x"), 2.0), new Pow(new Mult("e", "x"), 3.0)), new Pow(new Mult("e", "x"), 4.0));
        System.out.println(ex15);
        try {
            //System.out.println(ex15.evaluate(assignment));
            if (ex14.simplify().evaluate(assignment) != 0) {
                System.out.println("wrong simplify ex15");
            }
        } catch (Exception x) {
            System.out.println("wrong simplify ex15");
        }
        // Sin((e^(Sin(x) / Cos(x))))
        Expression ex16 = new Sin(new Pow("e", new Div(new Sin("x"), new Cos("x"))));
        System.out.println(ex16);
        //    System.out.println(ex16.differentiate("x"));
        System.out.println(ex16.differentiate("x").simplify());
        //////////////////(cos((e^(sin(x) / cos(x)))) * ((e^(sin(x) / cos(x))) * (((cos(x) * cos(x)) - ((-sin(x)) * sin(x))) / (cos(x)^2.0))))
        String compare = "(cos((e^(sin(x) / cos(x)))) * ((e^(sin(x) / cos(x))) * (((cos(x) * cos(x)) - (sin(x) * (-sin(x)))) / (cos(x)^2.0))))";
        if (!ex16.differentiate("x").simplify().toString().equals(compare)) {
            System.out.println("wrong diff ex16");
        }
    }
}