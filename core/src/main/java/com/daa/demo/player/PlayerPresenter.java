package com.daa.demo.player;

import com.badlogic.gdx.Gdx;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.EventHandler;
import com.daa.demo.events.PlayerEvent;
import com.daa.demo.mvp.BasePresenter;

public class PlayerPresenter extends BasePresenter<PlayerView, Player> {
    private final EventHandler<PlayerEvent> _onPlayerMove = event -> {
        Gdx.app.log("field handler", event.getAction().toString());
        view.setState(event.getAction());
        model.move(event.getDirection());

        view.setPosition(model.getPosition());
    };

    public PlayerPresenter(PlayerView view, Player model) {
        super(view, model);

        EventDispatcher.getInstance().register(PlayerEvent.class, this._onPlayerMove);
    }
}
