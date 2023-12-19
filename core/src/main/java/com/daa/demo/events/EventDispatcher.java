package com.daa.demo.events;

import java.util.*;

public final class EventDispatcher {
    private static volatile EventDispatcher _instance;
    private final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> _eventHandlers;

    private EventDispatcher() {
        this._eventHandlers = new HashMap<>();
    }

    public static EventDispatcher getInstance() {
        EventDispatcher result = _instance;
        if (result == null) {
            _instance = new EventDispatcher();
        }

        return _instance;
    }
    public void register(Class<? extends Event> event, EventHandler<? extends Event> handler) {
        this._eventHandlers
            .computeIfAbsent(event, x -> new ArrayList<>())
            .add(handler);
    }

    public <T extends Event> T dispatch(T event) {
        var handlers = this._eventHandlers.getOrDefault(event.getClass(), Collections.emptyList());
        handlers.forEach(handler -> invoke((EventHandler<T>)handler, event));

        return event;
    }

    public <T extends Event> T invoke(EventHandler<T> handler, T event) {
        handler.handle(event);
        return event;
    }
}
