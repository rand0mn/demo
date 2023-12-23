package com.daa.demo.enemy;

import com.badlogic.gdx.Gdx;
import com.daa.demo.events.*;
import com.daa.demo.mvp.BasePresenter;
import com.daa.demo.mvp.Model;
import com.daa.demo.mvp.View;

import java.util.concurrent.TimeUnit;

public class EnemyPresenter extends BasePresenter {

    private final EventHandler<RoundEndEvent> _onRoundEnd = event -> {
        Gdx.app.log("Enemy", "Round End");
    };

    private final EventHandler<LoopUpdateEvent> _onLoopUpdate = event -> {
    };



    public EnemyPresenter(View view, Model model) {
        super(view, model);
        EventDispatcher.getInstance().register(RoundEndEvent.class, this._onRoundEnd);
        EventDispatcher.getInstance().register(LoopUpdateEvent.class, this._onLoopUpdate);

    }
}
