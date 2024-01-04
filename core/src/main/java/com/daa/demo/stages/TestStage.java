package com.daa.demo.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.daa.demo.enemy.SpawnEnemyJob;
import com.daa.demo.events.EnemySpawnedEvent;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.EventHandler;
import com.daa.demo.handlers.EnemySpawnedHandler;
import com.daa.demo.loop.GameLoop;
import com.daa.demo.loop.GameLoopFactory;
import com.daa.demo.loop.MainJob;
import com.daa.demo.loop.RoundJob;
import com.daa.demo.mvp.ViewFactory;
import com.daa.demo.player.Player;
import com.daa.demo.player.PlayerPresenter;
import com.daa.demo.player.PlayerView;
import com.daa.demo.scenes.MainGameScene;
import com.daa.demo.scenes.Scene;

import java.util.ArrayList;

public class TestStage implements Stage {
    private Scene _scene;
    private GameLoop _loop;

    public TestStage() {
        var player = (PlayerView) ViewFactory.create(
            PlayerView.class,
            PlayerPresenter.class,
            new Player(new Vector2(0, 0))
        );

        this._scene = new MainGameScene(player, new ArrayList<>());
        this._loop = GameLoopFactory.create();

        EventDispatcher.getInstance().register(EnemySpawnedEvent.class, event -> Gdx.app.postRunnable(() -> {
            var handler = new EnemySpawnedHandler();
            handler.handle((EnemySpawnedEvent) event);
            this._scene.addActors(handler.getResult());
        }));
    }

    public Scene getScene() {
        return this._scene;
    }

    public GameLoop getGameLoop() {
        return this._loop;
    }
}
