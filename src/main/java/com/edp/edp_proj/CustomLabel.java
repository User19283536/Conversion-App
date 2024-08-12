package com.edp.edp_proj;

import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.util.Duration;

public class CustomLabel extends Label{

    private FadeTransition ft;

    public CustomLabel() {
        super();
        setTextFill(Paint.valueOf("red"));
        ft = new FadeTransition(Duration.millis(3000), this);
        ft.setFromValue(1.0);
        ft.setToValue(0);
        ft.setCycleCount(1);


    }

    public void fade()
    {
        //FadeTransition ft = new FadeTransition(Duration.millis(3000), this);
        ft.play();
    }




}
