package com.daa.demo.mvp;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.Disposable;
import com.daa.demo.player.PlayerAction;

public interface View extends Position2d, Disposable {
    void render(Batch batch);
}
