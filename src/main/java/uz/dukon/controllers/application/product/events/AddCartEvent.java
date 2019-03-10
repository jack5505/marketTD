package uz.dukon.controllers.application.product.events;

import javafx.beans.NamedArg;
import javafx.event.Event;
import javafx.event.EventType;
import uz.dukon.controllers.newmodel.CartTable;

/**
 * Created by Jack on 19.02.2019.
 */
public class AddCartEvent extends Event
{
    CartTable cartTable = new CartTable();
    public static final EventType<AddCartEvent> ANY =
            new EventType<>(Event.ANY, "Add_PRODUCT_CART");
    public AddCartEvent(@NamedArg("eventType") EventType<? extends Event> eventType, CartTable cartTable) {
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
