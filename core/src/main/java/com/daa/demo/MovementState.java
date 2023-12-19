package com.daa.demo;

public interface MovementState {
    void idle(MovementContext context);
    void move(MovementContext context);
}
