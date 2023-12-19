package com.daa.demo.mvp;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.daa.demo.player.PlayerAction;

public interface View extends Position2d {
    void render(Batch batch);
    void setState(PlayerAction action);
}
