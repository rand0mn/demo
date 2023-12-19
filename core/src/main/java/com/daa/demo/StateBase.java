package com.daa.demo;

import com.daa.demo.player.Player;

public abstract class StateBase {
    protected Player _player;

    StateBase(Player player) {
        this._player = player;
    }

    public abstract void idle();
    public abstract void move();
}
