package com.daa.demo.enemy;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.mvp.BaseModel;

public class Enemy extends BaseModel {
    private float _speed = 2;
    private Vector2 _direction;

    public Enemy(Vector2 position){
        super(position);
    }

    public float getSpeed() {
        return this._speed;
    }

    public void setSpeed(float speed) {
        this._speed = speed;
    }

    public void move(Vector2 direction) {
        this._direction = direction.cpy();
        var position = this.getPosition().add(direction.cpy().scl(this._speed));
        this.setPosition(position);
    }
}
