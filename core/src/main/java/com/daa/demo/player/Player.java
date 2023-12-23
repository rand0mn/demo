package com.daa.demo.player;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.mvp.BaseModel;


/* TODO:
    баг-1, если персонаж идет налево, то после остановки он всегд поварачивается вправо.
    баг-2, движение только в четрыех направления, нельзя двигать по диагнонали.
    баг-3, зажать вправо, не отпуская вправо нажать и отпустить влево. Итог: зажата кнопка вправо, персонаж идет влево.
 */
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

    public Vector2 getDirection() {
        return _direction.cpy();
    }

    public void setDirection(Vector2 direction) {
        this._direction = direction.cpy();
    }
}
