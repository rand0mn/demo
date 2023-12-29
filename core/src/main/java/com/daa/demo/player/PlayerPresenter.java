package com.daa.demo.player;

import com.badlogic.gdx.math.Vector2;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.EventHandler;
import com.daa.demo.events.LoopUpdateEvent;
import com.daa.demo.events.PlayerEvent;
import com.daa.demo.mvp.BasePresenter;

public class PlayerPresenter extends BasePresenter<PlayerView, Player> {
    private final EventHandler<LoopUpdateEvent> _onUpdate = event -> {
        model.move(model.getDirection());
        view.setPosition(model.getPosition());
    };

    private final EventHandler<PlayerEvent> _onPlayerAction = event -> {
        view.setState(event.getAction());

        if (event.getAction() == PlayerAction.IDLE) {
            model.setDirection(Vector2.Zero);
        }
        if (!event.isMoveAction()) {
            return;
        }

        model.setDirection(event.getDirection());
        //view.setCameraPosition();

    };

    public PlayerPresenter(PlayerView view, Player model) {
        super(view, model);
        EventDispatcher.getInstance().register(LoopUpdateEvent.class, this._onUpdate);
        EventDispatcher.getInstance().register(PlayerEvent.class, this._onPlayerAction);
    }
}
