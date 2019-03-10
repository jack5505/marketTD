package uz.dukon.controllers.application.product.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 20.02.2019.
 */
public class StartAgainEvent extends Event {
    public static final EventType<StartAgainEvent> ANY =
            new EventType<>(Event.ANY, "CLEAR_ALL_START_AGAIN");
    public StartAgainEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }
}
