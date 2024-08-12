package com.edp.edp_proj;
import com.google.common.eventbus.Subscribe;

public class ExitEventListener {

    @Subscribe
    public  void closeAppOrNot(ExitEvent exitEvent){

        if (exitEvent.exit().equals("exitApp"))
        {
            System.exit(0);
        }

    }

}
