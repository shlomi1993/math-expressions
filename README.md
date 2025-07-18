# Math Expressions

## In General

In this repo I share my imiplementation of a system that can represent nested mathematical expressions that include variables, evaluate their values for specific variable assignments, differentiate them, and simplify the results.

This implementation is based on **Object-Oriented priniples** and **Composite/Interpreter Design-Pattern**.

## The Project

This project is devided to three main parts:

### Part 1 -- Mathematical Expressions

In this part I created a representation for a mathematical expressions such as sin(((2x + y) * 4)^x).
I created classes for unary expressions such as:
- Var("x")
- Sin(x)
and for binary expression such as:
- Plus(x, y)
- Mul(x, y)
- Pow(x, y)

#### Mathematical Expression Representation

The idea is to represent a mathematical expression using:

Expression e = new Sin(
                     new Pow(
                        new Mul(
                           new Plus(
                              new Mul(new Num(2), new Var("x")),
                              new Var("y")),
                           new Num(4)),
                     new Var("x")))
                     
That is how we get a tree like this:
![image](https://user-images.githubusercontent.com/72878018/120223169-d6f95e00-c249-11eb-8246-da98db67fb19.png)

#### Class Hirarchy

![image](https://user-images.githubusercontent.com/72878018/120223262-03ad7580-c24a-11eb-8039-9947d1a673ed.png)


### Part 2 -- Automatic Differentitation

In this part I created a mechanism to differentiate them according to a given variable.

### Part 3 -- Simplification

The representation of the mathematical expression is saturated with parenthesis.
This part is about simplify the representation by reducing the number of parenthesis.

## Compile and Run

To compile the program, use ant compiler.
Run the command "ant compile" to compile the program, and then "ant run" to run it.
Note that the compilation required the build.xml file that in the main project directory.
The program will run a basic main code that written in ExpressionsTest.java file and contains an example.

The main in ExpressionsTest.java file is just a driver/test code and one can change it a she/he wishes. 

#### Running Example

![image](https://user-images.githubusercontent.com/72878018/120226033-0c547a80-c24f-11eb-9101-74f553f0e5cf.png)


## Language

- 100% Java.

## IDEs, Writers and tools

1. JetBrains IntelliJ IDEA
2. Apache Ant - https://ant.apache.org/
3. Notepad++
4. CheckStyle - for maintaining Java coding convention.
