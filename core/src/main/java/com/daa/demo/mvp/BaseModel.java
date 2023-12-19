package com.daa.demo.mvp;

import com.badlogic.gdx.math.Vector2;

public abstract class BaseModel implements Model {
    private Vector2 _position;

    protected BaseModel(Vector2 position) {
        this.setPosition(position);
    }

    @Override
    public Vector2 getPosition() {
        return this._position;
    }

    public void setPosition(Vector2 position) {
        this._position = position.cpy();
    }
}
