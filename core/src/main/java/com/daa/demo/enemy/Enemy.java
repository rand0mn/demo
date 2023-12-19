package com.daa.demo.enemy;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.mvp.BaseModel;

public class Enemy extends BaseModel {
    private float _speed = 2;

    public Enemy(Vector2 position){
        super(position);
    }

    public float getSpeed() {
        return this._speed;
    }

    public void setSpeed(float speed) {
        this._speed = speed;
    }
}
