package com.daa.demo;

import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.PlayerEvent;
import com.daa.demo.player.PlayerAction;
import com.daa.demo.utils.Vector2Utils;

public class IdleState implements MovementState {
    @Override
    public void idle(MovementContext context) {
        context._lastAction = PlayerAction.IDLE;
        context._lastDirection = null;
    }


    @Override
    public void move(MovementContext context) {
        context.setState(new MoveState());
    }
}
