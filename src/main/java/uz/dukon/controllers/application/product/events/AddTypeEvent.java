package uz.dukon.controllers.application.product.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 27.02.2019.
 */
public class AddTypeEvent extends Event
{
    public static final EventType<AddTypeEvent> ANY =
            new EventType<>(Event.ANY, "Add_TYPE_EVENT");
    public AddTypeEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }
}
