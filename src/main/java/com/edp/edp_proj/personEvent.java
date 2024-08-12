package com.edp.edp_proj;

import javafx.event.Event;
import javafx.event.EventType;

public class personEvent extends Event {    //custom event class
    private personnalInformation person;                  //data of event

    //event types
    public static final EventType<personEvent> p1 = new EventType<>(Event.ANY, "ANY1");
    public static final EventType<personEvent> ANY = p1;
    public static final EventType<personEvent> PERSON = new EventType<>(personEvent.ANY, "PERSON");

    //event constructor
    public personEvent(EventType<? extends Event> eventType,personnalInformation Person ){
        super(eventType);
        this.person = Person;
    }

    //obtain event's data
    public personnalInformation getData(){
        return this.person;
    }
}