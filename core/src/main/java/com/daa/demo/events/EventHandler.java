package com.daa.demo.events;

@FunctionalInterface
public interface EventHandler<T extends Event> {
    void handle(T event);
}
