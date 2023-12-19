package com.daa.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.daa.demo.player.PlayerAction;

public class MovementContext {
    public Vector2 _lastDirection;
    public PlayerAction _lastAction;

    private MovementState state = new IdleState();

    public void idle() {
        Gdx.app.log("MovementContext", "idle");
        state.idle(this);
    }

    public void move() {
        Gdx.app.log("MovementContext", "move");
        this.state.move(this);
    }
    public void setState(MovementState state) {
        Gdx.app.log("MovementContext", state.toString());
        this.state = state;
    }
}
