class TestPart1 {
    public static void main(String[] args) {
        Expression[] e = new Expression[28];
        e[0] = new Plus(new Num(0), new Var("x"));
        e[1] = new Plus(new Var("x"), new Num(0));
        e[2] = new Plus(new Var("x"), new Var("x"));
        e[3] = new Minus(new Var("x"), new Num(0));
        e[4] = new Minus(new Num(0), new Var("x"));
        e[5] = new Minus(new Var("x"), new Var("x"));
        e[6] = new Mult(new Num(0), new Var("x"));
        e[7] = new Mult(new Var("x"), new Num(0));
        e[8] = new Mult(new Num(1), new Var("x"));
        e[9] = new Mult(new Var("x"), new Num(1));
        e[10] = new Mult(new Var("x"), new Var("x"));
        e[11] = new Div(new Var("x"), new Num(3));
        e[12] = new Div(new Num(0), new Var("x"));
        e[13] = new Div(new Var("x"), new Num(1));
        e[14] = new Div(new Num(1), new Var("x"));
        e[15] = new Div(new Var("x"), new Var("x"));
        e[16] = new Pow(new Var("x"), new Num(0));
        e[17] = new Pow(new Var("x"),new Num(5));
        e[18] = new Pow(new Var("x"), new Num(1));
        e[19] = new Pow(new Var("e"), new Var("x"));
        e[20] = new Log(new Var("x"), new Var("x"));
        e[21] = new Log(new Var("x"), new Num(2));
        e[22] = new Log(new Num(1), new Var("x"));
        e[23] = new Log(new Var("x"), new Num(4));
        e[24] = new Log(new Num(2), new Var("x"));
        e[25] = new Neg(new Neg(new Sin(new Neg(new Sin(new Neg(new Var("x")))))));
        e[26] = new Mult(new Mult(new Sin(new Var("x")), new Sin(new Var("x"))), new Mult(new Sin(new Var("x")), new Sin(new Var("x"))));
        e[27] = new Plus(new Cos(new Var("x")), new Plus(new Plus(new Cos(new Var("x")), new Cos(new Var("x"))), new Cos(new Var("x"))));

        java.util.Map<String, Double> assignment = new java.util.TreeMap<String, Double>();
        assignment.put("x",5.4);
        assignment.put("y",3.0);
        assignment.put("e",2.71);
        for (int i = 0; i < e.length; i++) {
            System.out.println ("Expression "+(i)+" : "+e[i].toString());
            try {
                System.out.println("Expression "+(i)+" evaluate x = 5.4: "+e[i].evaluate(assignment));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            Expression e1=new Pow(new Var("y"),new Pow(new Var("y"),new Num(2)));
            e[i]=e[i].assign("x",e1);
            System.out.println("Expression "+(i)+" assign: "+e[i].toString());
            try {
                System.out.println("Expression "+(i)+" assign evaluate y = 3.0: "+e[i].evaluate(assignment));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
    /*
    answers:
Expression 0 : (0.0 + x)
Expression 0 evaluate x = 5.4: 5.4
Expression 0 assign: (0.0 + (y^(y^2.0)))
Expression 0 assign evaluate y = 3.0: 19683.0
Expression 1 : (x + 0.0)
Expression 1 evaluate x = 5.4: 5.4
Expression 1 assign: ((y^(y^2.0)) + 0.0)
Expression 1 assign evaluate y = 3.0: 19683.0
Expression 2 : (x + x)
Expression 2 evaluate x = 5.4: 10.8
Expression 2 assign: ((y^(y^2.0)) + (y^(y^2.0)))
Expression 2 assign evaluate y = 3.0: 39366.0
Expression 3 : (x - 0.0)
Expression 3 evaluate x = 5.4: 5.4
Expression 3 assign: ((y^(y^2.0)) - 0.0)
Expression 3 assign evaluate y = 3.0: 19683.0
Expression 4 : (0.0 - x)
Expression 4 evaluate x = 5.4: -5.4
Expression 4 assign: (0.0 - (y^(y^2.0)))
Expression 4 assign evaluate y = 3.0: -19683.0
Expression 5 : (x - x)
Expression 5 evaluate x = 5.4: 0.0
Expression 5 assign: ((y^(y^2.0)) - (y^(y^2.0)))
Expression 5 assign evaluate y = 3.0: 0.0
Expression 6 : (0.0 * x)
Expression 6 evaluate x = 5.4: 0.0
Expression 6 assign: (0.0 * (y^(y^2.0)))
Expression 6 assign evaluate y = 3.0: 0.0
Expression 7 : (x * 0.0)
Expression 7 evaluate x = 5.4: 0.0
Expression 7 assign: ((y^(y^2.0)) * 0.0)
Expression 7 assign evaluate y = 3.0: 0.0
Expression 8 : (1.0 * x)
Expression 8 evaluate x = 5.4: 5.4
Expression 8 assign: (1.0 * (y^(y^2.0)))
Expression 8 assign evaluate y = 3.0: 19683.0
Expression 9 : (x * 1.0)
Expression 9 evaluate x = 5.4: 5.4
Expression 9 assign: ((y^(y^2.0)) * 1.0)
Expression 9 assign evaluate y = 3.0: 19683.0
Expression 10 : (x * x)
Expression 10 evaluate x = 5.4: 29.160000000000004
Expression 10 assign: ((y^(y^2.0)) * (y^(y^2.0)))
Expression 10 assign evaluate y = 3.0: 3.87420489E8
Expression 11 : (x / 3.0)
Expression 11 evaluate x = 5.4: 1.8
Expression 11 assign: ((y^(y^2.0)) / 3.0)
Expression 11 assign evaluate y = 3.0: 6561.0
Expression 12 : (0.0 / x)
Expression 12 evaluate x = 5.4: 0.0
Expression 12 assign: (0.0 / (y^(y^2.0)))
Expression 12 assign evaluate y = 3.0: 0.0
Expression 13 : (x / 1.0)
Expression 13 evaluate x = 5.4: 5.4
Expression 13 assign: ((y^(y^2.0)) / 1.0)
Expression 13 assign evaluate y = 3.0: 19683.0
Expression 14 : (1.0 / x)
Expression 14 evaluate x = 5.4: 0.18518518518518517
Expression 14 assign: (1.0 / (y^(y^2.0)))
Expression 14 assign evaluate y = 3.0: 5.080526342529086E-5
Expression 15 : (x / x)
Expression 15 evaluate x = 5.4: 1.0
Expression 15 assign: ((y^(y^2.0)) / (y^(y^2.0)))
Expression 15 assign evaluate y = 3.0: 1.0
Expression 16 : (x^0.0)
Expression 16 evaluate x = 5.4: 1.0
Expression 16 assign: ((y^(y^2.0))^0.0)
Expression 16 assign evaluate y = 3.0: 1.0
Expression 17 : (5.0^x)
Expression 17 evaluate x = 5.4: 5948.918558487124
Expression 17 assign: (5.0^(y^(y^2.0)))
Expression 17 assign evaluate y = 3.0: Infinity
Expression 18 : (x^1.0)
Expression 18 evaluate x = 5.4: 5.4
Expression 18 assign: ((y^(y^2.0))^1.0)
Expression 18 assign evaluate y = 3.0: 19683.0
Expression 19 : (e^x)
Expression 19 evaluate x = 5.4: 217.78811234649754
Expression 19 assign: (e^(y^(y^2.0)))
Expression 19 assign evaluate y = 3.0: Infinity
Expression 20 : Log(x, x)
Expression 20 evaluate x = 5.4: 1.0
Expression 20 assign: Log((y^(y^2.0)), (y^(y^2.0)))
Expression 20 assign evaluate y = 3.0: 1.0
Expression 21 : Log(x, 2.0)
Expression 21 evaluate x = 5.4: 2.4329594072761065
Expression 21 assign: Log((y^(y^2.0)), 2.0)
Expression 21 assign evaluate y = 3.0: 14.264662506490406
Expression 22 : Log(1.0, x)
Expression 22 evaluate x = 5.4: 0.0
Expression 22 assign: Log(1.0, (y^(y^2.0)))
Expression 22 assign evaluate y = 3.0: 0.0
Expression 23 : Log(x, 4.0)
Expression 23 evaluate x = 5.4: 1.2164797036380532
Expression 23 assign: Log((y^(y^2.0)), 4.0)
Expression 23 assign evaluate y = 3.0: 7.132331253245203
Expression 24 : Log(2.0, x)
Expression 24 evaluate x = 5.4: 0.4110220651480496
Expression 24 assign: Log(2.0, (y^(y^2.0)))
Expression 24 assign evaluate y = 3.0: 0.07010330595238416
Expression 25 : (-(-Sin((-Sin((-x))))))
Expression 25 evaluate x = 5.4: -0.6981172311445862
Expression 25 assign: Sin(Sin((y^(y^2.0))))
Expression 25 assign evaluate y = 3.0: -0.7151417847400416
Expression 26 : ((Sin(x) * Sin(x)) * (Sin(x) * Sin(x)))
Expression 26 evaluate x = 5.4: 0.35660598136340216
Expression 26 assign: ((Sin((y^(y^2.0))) * Sin((y^(y^2.0)))) * (Sin((y^(y^2.0))) * Sin((y^(y^2.0)))))
Expression 26 assign evaluate y = 3.0: 0.40314011477159395
Expression 27 : (Cos(x) + ((Cos(x) + Cos(x)) + Cos(x)))
Expression 27 evaluate x = 5.4: 2.5387715037705387
Expression 27 assign: (Cos((y^(y^2.0))) + ((Cos((y^(y^2.0))) + Cos((y^(y^2.0)))) + Cos((y^(y^2.0)))))
Expression 27 assign evaluate y = 3.0: -2.416830460849245
     */
}