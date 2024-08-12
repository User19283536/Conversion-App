package com.edp.edp_proj;

public class Interpreter {

    public boolean checkFloat(String input)
    {
        return input.matches("([0-9]*)|(\\.)|(\\b)");
    }

    public boolean checkNames(String input)
    {
        //System.out.print(input.matches("(a-zA-Z*)|( )|(\\b)"));
        return input.matches("[a-zA-Z]*|( )");
    }
}
