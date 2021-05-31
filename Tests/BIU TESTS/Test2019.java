import java.util.Map;
import java.util.TreeMap;

/**
 * this is class which contains the main function for the expression test.
 * @author matan epel
 *
 */
public class Test2019 {
    /**
     * this is the main function.
     * @param args cmd arguments
     */
    public static void main(String[] args) {
		Expression e1 = new Pow(new Plus(new Plus(new Var("x"), new Var("y")), new Var("z")), new Num(2.0));
		System.out.println("\n\ntest 1:");
		e1 = e1.assign("x", new Num(35.0));
		e1 = e1.assign("y", new Pow(new Var("y"), new Num(4)));
		e1 = e1.assign("z", new Num(4.75));
		System.out.println(e1.toString());

		Expression e2 = new Pow(new Plus(new Plus(new Var("x"), new Var("y")), new Plus(new Var("z"), new Var("w"))), new Num(4));
		System.out.println("\n\ntest 2:");
		System.out.println(e2.differentiate("x").simplify());
		System.out.println("test 4" + e2.getVariables());
		System.out.println("\n\ntest 3:");
		e2 = e2.assign("x", new Num(2.0));
		e2 = e2.assign("y", new Num(4.0));
		e2 = e2.assign("z", new Num(6.0));
		e2 = e2.assign("w", new Num(10.0));
		try {
			System.out.println(e2.evaluate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		System.out.println("\n\ntest 4:");
		System.out.println(e2);
		System.out.println(e2.getVariables());

		Expression e3 = new Plus(new Plus(new Mult(new Num(2), new Var("x")) , new Sin(new Mult(new Num(4), new Var("y")))) , new Pow(new Var("e"), new Var("x")));
		System.out.println("\n\ntest 5:");
		System.out.println(e3.toString());

		System.out.println("\n\ntest 7:");
		System.out.println(e3.differentiate("x").simplify());

		System.out.println("\n\ntest 6:");
		e3 = e3.assign("x", new Num(2.0));
		e3 = e3.assign("y", new Num(0.25));
		e3 = e3.assign("e", new Num(java.lang.Math.E));
		try {
			System.out.println(e3.evaluate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


		Expression e4 = new Div(new Plus(new Sin(new Num(1)) , new Cos(new Num(1))) , new Num(10));
		System.out.println("\n\ntest 8:");
		System.out.println(e4.simplify());

		Expression e5 = new Mult(new Log(new Mult(new Num(9), new Var("x")),new Mult(new Num(9), new Var("x"))) , new Mult(new Num(2), new Var("y")));
		System.out.println("\n\ntest 9:");
		System.out.println(e5.simplify());

		Expression e6 = new Plus( new Neg(new Mult(new Div(new Var("y") , new Neg(new Div(new Var("y") , new Var("x")))) , new Num(0))) , new Neg(new Neg(new Plus(new Minus(new Neg(new Neg(new Minus(new Neg(new Num(2)) , new Num(0)))) ,new Num(4)) , new Var("x")))));
		System.out.println("\n\ntest 10:");
		System.out.println(e6.simplify());

		Expression e7 = new Minus(new Plus(new Pow(new Var("x"),new Num(2)) , new Pow(new Var("y"),new Num(2))) , new Pow(new Var("z"),new Num(3)));
		e7 = e7.assign("x", new Num(7.0));
		e7 = e7.assign("y", new Num(5.0));
		e7 = e7.assign("z", new Num(0.75));
		try {
			System.out.println("\n\ntest 11:");
			System.out.println(e7.evaluate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		Expression e8 = new Div(new Log(new Var("x"),new Var("x")) , new Log(new Var("x"),new Var("x")));
		System.out.println("\n\ntest 12:");
		System.out.println(e8.simplify());

		Expression e9 = new Minus(new Num(0) , new Mult(new Var("x"), new Div(new Num(1) , new Num(1))));
		System.out.println("\n\ntest 13:");
		System.out.println(e9.simplify());

		Expression e10 = new Plus(new Plus(new Cos(new Var("x")) , new Log(new Var("e"),new Var("x"))) ,new Plus(new Div(new Num(1),new Var("x")) , new Sin(new Pow(new Var("x"),new Num(2)))));
		System.out.println("\n\ntest 14:");
		System.out.println(e10.differentiate("x"));

		Expression e11 = new Plus(new Mult(new Plus(new Num(3) , new Num(6)) ,new Var("x")) , new Mult(new Mult(new Num(4),new Var("x")) , new Sin(new Num(0))));
		System.out.println("\n\ntest 15:");
		System.out.println(e11.simplify());

		Expression e12 = new Plus(new Mult(new Cos(new Mult(new Num(5),new Pow(new Var("x"),new Num(7)))) , new Var("e")) , new Pow(new Var("y"),new Plus(new Div(new Var("x") , new Num(2)) , new Num(5))));
		System.out.println("\n\ntest 16:");
		e12 = e12.assign("x", new Num(25.0));
		e12 = e12.assign("y", new Num(36.75));
		e12 = e12.assign("e", new Num(java.lang.Math.E));
		try {
			System.out.println(e12.evaluate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		Expression e13 = new Plus(new Mult(new Mult(new Var("x") , new Num(1)) , new Num(1)) , new Minus(new Num(0) , new Neg(new Plus(new Var("x") , new Num(0)))));
		System.out.println("\n\ntest 17:");
		System.out.println(e13.simplify());

//		Expression e14 = new Mult(new Mult(new Mult(new Mult(new Mult(20.0 , new Pow(new Var("x"),5.0)) , new Mult(3.0 , new Pow(new Var("e"), new Var("x")))) , new Cos(new Pow(new Var("x"), new Num(2)))) , new Minus(new Var("x"), new Var("x"))),
//				new Mult(new Mult(new Mult(new Mult(20.0, new Pow(new Var("x"), 5.0)) , new Mult(3.0,new Pow(new Var("e"), new Var("x"))) ) , new Cos(new Pow(new Var("x"),new Num(2)))) ,new Minus(new Var(new Var("x")) , new Var("x"))));
//		System.out.println("\n\ntest 18:");
//		System.out.println(e14.simplify());

		Expression e15 = new Mult(new Div(new Mult(new Pow(new Var("e"), new Var("x")) ,new Pow(new Pow(new Var("e"), new Var("x")),new Num(2))) , new Pow(new Pow(new Var("e"), new Var("x")),new Num(3))) ,
		new Div(new Mult(new Pow(new Pow(new Var("e"), new Var("x")),new Num(4)) , new Pow(new Pow(new Var("e"), new Var("x")),new Num(3))) , new Pow(new Pow(new Var("e"), new Var("x")),new Num(7))));
		System.out.println("\n\ntest 19:");
		e15 = e15.assign("e", new Num(2.71));
		e15 = e15.assign("x", new Num(0.0));
		try {
			System.out.println(e15.evaluate());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

		Expression e16 = new Sin(new Pow(new Var("e"), new Div(new Sin(new Var("x")), new Cos(new Var("x")))));
		System.out.println("\n\ntest 20:");
		System.out.println(e16.differentiate("x"));

    }
}
