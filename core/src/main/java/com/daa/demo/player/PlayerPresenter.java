package com.daa.demo.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Disposable;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.EventHandler;
import com.daa.demo.events.PlayerEvent;
import com.daa.demo.mvp.BasePresenter;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PlayerPresenter extends BasePresenter<PlayerView, Player> implements Disposable {
    private ScheduledExecutorService _executorService;
    private Future<?> _updatePositionTask;
    private final EventHandler<PlayerEvent> _onPlayerMove = event -> {
        Gdx.app.log("field handler", event.getAction().toString());

        view.setState(event.getAction());

        if (this._updatePositionTask != null && !this._updatePositionTask.isCancelled()) {
            this._updatePositionTask.cancel(true);
        }

        if (!event.isMoveAction()) {
            return;
        }

        this._updatePositionTask = this._executorService.scheduleAtFixedRate(() -> {
            model.move(event.getDirection());
            view.setPosition(model.getPosition());
        }, 0, 20, TimeUnit.MILLISECONDS);
    };

    public PlayerPresenter(PlayerView view, Player model) {
        super(view, model);
        this._executorService = Executors.newScheduledThreadPool(1);
        EventDispatcher.getInstance().register(PlayerEvent.class, this._onPlayerMove);
    }

    @Override
    public void dispose() {
        this._updatePositionTask.cancel(true);
        this._updatePositionTask = null;
        this._executorService.shutdown();
        this._executorService = null;
    }
}
