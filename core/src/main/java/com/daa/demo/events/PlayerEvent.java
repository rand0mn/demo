package com.daa.demo.events;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.player.PlayerAction;

import java.util.stream.Stream;

public class PlayerEvent extends Event {
    private final PlayerAction _action;
    private final Vector2 _direction;

    public PlayerEvent(PlayerAction action, Vector2 direction) {
        this._action = action;
        this._direction = direction;
    }

    public PlayerAction getAction() {
        return this._action;
    }
    public boolean isMoveAction() {
        return Stream.of(
            PlayerAction.UP,
            PlayerAction.DOWN,
            PlayerAction.LEFT,
            PlayerAction.RIGHT
        ).anyMatch(x -> x == this.getAction());
    }
    public Vector2 getDirection() {return this._direction; }
}
