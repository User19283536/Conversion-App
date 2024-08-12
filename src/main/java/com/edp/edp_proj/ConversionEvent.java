package com.edp.edp_proj;

import javafx.event.Event;
import javafx.event.EventType;

public class ConversionEvent extends Event {    //custom event class
    private ConversionData data;                  //data of event

    //event types
    public static final EventType<ConversionEvent> CONV = new EventType<>(Event.ANY, "ANY2");
    public static final EventType<ConversionEvent> ANY = CONV;
    public static final EventType<ConversionEvent> CONVERT = new EventType<>(ConversionEvent.ANY, "CONVERT");

    //event constructor
    public ConversionEvent(EventType<? extends Event> eventType,ConversionData Data ){
        super(eventType);
        this.data = Data;
    }

    //obtain event's data
    public ConversionData getData(){
        return this.data;
    }
}