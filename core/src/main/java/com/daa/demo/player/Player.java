package com.daa.demo.player;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.mvp.BaseModel;


public class Player extends BaseModel {
    private final float _speed = 2;
    private Vector2 _direction = Vector2.Zero;

    public Player(Vector2 position) {
        super(position);
    }

    public void move(Vector2 direction) {
        this._direction = direction.cpy();
        var position = this.getPosition().add(direction.cpy().scl(this._speed));
        this.setPosition(position);
    }
}
