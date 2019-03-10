package uz.dukon.controllers.application.product.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;

/**
 * Created by Jack on 17.02.2019.
 */
public class DeleteProductEvent extends Event {
    public static final EventType<DeleteProductEvent> ANY =
            new EventType<>(Event.ANY, "DELETE_PRODUCT");

    private Long productId;

    public DeleteProductEvent(@NamedArg("eventType") EventType<? extends Event> eventType, Long productId) {
        super(eventType);
        this.productId = productId;
    }


    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
