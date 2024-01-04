package com.daa.demo.ui;

import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.EventHandler;
import com.daa.demo.events.LoopUpdateEvent;
import com.daa.demo.mvp.BasePresenter;
import com.daa.demo.player.Player;

public class UiPresenter extends BasePresenter<HudView, Player> {
    private final EventHandler<LoopUpdateEvent> _onLoopUpdate = event -> {
        this.view.setDebugMessage(String.format("Player Position: %s", this.model.getPosition()));
    };

    public UiPresenter(HudView view, Player model) {
        super(view, model);
        EventDispatcher.getInstance().register(LoopUpdateEvent.class, this._onLoopUpdate);

    }
}
