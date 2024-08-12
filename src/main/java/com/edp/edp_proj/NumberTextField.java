package com.edp.edp_proj;

import javafx.scene.control.TextField;

public class NumberTextField extends TextField
{
    private Interpreter interpreter = new Interpreter();


    @Override
    public void replaceText(int first, int last, String input)
    {
        if (interpreter.checkFloat(input))
        {
            super.replaceText(first, last, input);
        }
    }

    @Override
    public void replaceSelection(String input)
    {
        if (interpreter.checkFloat(input))
        {
            super.replaceSelection(input);
        }
    }


}