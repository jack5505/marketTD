package uz.dukon.controllers.application.product.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

public class OrderSaveEvent extends Event {
    public static final EventType<OrderSaveEvent> ANY =
            new EventType<>(Event.ANY, "ORDER_SAVE_EVENT");

    private Long productId;

    public OrderSaveEvent(@NamedArg("eventType") EventType<? extends Event> eventType) {
        super(eventType);
    }
}

