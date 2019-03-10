package uz.dukon.controllers.application.product.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;
import uz.dukon.controllers.newmodel.CartTable;

/**
 * Created by Jack on 19.02.2019.
 */
public class DeleteCartProductEvent extends Event {
    public static final EventType<DeleteCartProductEvent> ANY =
            new EventType<>(Event.ANY, "DELETE_CART_PRODUCT");

   private CartTable cartTable;

    public DeleteCartProductEvent(@NamedArg("eventType") EventType<? extends Event> eventType, CartTable cartTable) {
        super(eventType);
       this.cartTable = cartTable;
    }

    public CartTable getCartTable() {
        return cartTable;
    }

    public void setCartTable(CartTable cartTable) {
        this.cartTable = cartTable;
    }
}
