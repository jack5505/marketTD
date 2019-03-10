package uz.dukon.controllers.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;

public interface EventBus {
    /**
     * Register event handler for event type.
     *
     * @param eventType type
     * @param eventHandler handler
     * @param <T> event
     */
    <T extends Event> Subscriber addEventHandler(EventType<T> eventType,
                                                 EventHandler<? super T> eventHandler);

    /**
     * Remove event handler for event type.
     *
     * @param eventType type
     * @param eventHandler handler
     * @param <T> event
     */
    <T extends Event> void removeEventHandler(EventType<T> eventType,
                                              EventHandler<? super T> eventHandler);

    /**
     * Post (fire) given event. All listening parties will be notified.
     * Eventsf will be handled on the same thread that fired the event,
     * i.e. synchronous.
     *
     * <p>
     *     Note: according to JavaFX doc this must be called on JavaFX Application Thread.
     *     In reality this doesn't seem to be true.
     * </p>
     *
     * @param event the event
     */
    void fireEvent(Event event);
}
