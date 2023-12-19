package com.daa.demo.player;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.MovementContext;
import com.daa.demo.mvp.BaseModel;

public class Player extends BaseModel {
    private final float _speed = 2;
    //private final MovementContext _state = new MovementContext();
    public Player(Vector2 position) {
        super(position);
    }

    public void move(Vector2 direction) {
        //this._state.move();
        var position = this.getPosition().add(direction.cpy().scl(this._speed));
        this.setPosition(position);
    }

//    public MovementContext getState() {
//        return this._state;
//    }
}
