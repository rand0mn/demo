package com.daa.demo.events;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.player.PlayerAction;

public class PlayerEvent extends Event {
    private final PlayerAction _action;
    private final Vector2 _direction;

    public PlayerEvent(PlayerAction action, Vector2 direction) {
        this._action = action;
        _direction = direction;
    }

    public PlayerAction getAction() {
        return _action;
    }
    public Vector2 getDirection() {return _direction; }
}
