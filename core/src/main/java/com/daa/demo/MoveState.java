package com.daa.demo;

import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.PlayerEvent;

public class MoveState implements MovementState {
    @Override
    public void idle(MovementContext context) {
        context.setState(new IdleState());
    }

    @Override
    public void move(MovementContext context) {
        EventDispatcher.getInstance().dispatch(new PlayerEvent(context._lastAction, context._lastDirection));
        context.setState(new MoveState());
    }
}
