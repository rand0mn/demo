package com.daa.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.utils.SimpleNonBlockingSemaphore;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.daa.demo.enemy.*;
import com.daa.demo.events.EnemySpawnedEvent;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.EventHandler;
import com.daa.demo.events.PlayerEvent;
import com.daa.demo.loop.GameLoop;
import com.daa.demo.loop.MainJob;
import com.daa.demo.loop.RoundJob;
import com.daa.demo.mvp.View;
import com.daa.demo.mvp.ViewFactory;
import com.daa.demo.player.KeyboardAdapter;
import com.daa.demo.player.Player;
import com.daa.demo.player.PlayerPresenter;
import com.daa.demo.player.PlayerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Kernel extends ApplicationAdapter {
    private SpriteBatch _batch;
    private Collection<View> _views = new ArrayList<>();
    private GameLoop _loop;

    /* костыльное создание вьюх по чере фабрику. Создавать в рендер треде */
    private final EventHandler<EnemySpawnedEvent> _onEnemySpawn = event -> Gdx.app.postRunnable(() -> {
        var views = new ArrayList<View>();
        for(Enemy enemy: event.getEnemies()) {
            views.add(ViewFactory.create(EnemyView.class, EnemyPresenter.class, enemy));
        }
        this._views.addAll(views);
    });

    /* TODO: move out. Check scenes api from libgdx. */
    private void createScene() {
        var player = ViewFactory.create(
            PlayerView.class,
            PlayerPresenter.class,
            new Player(new Vector2(10, 10))
        );

        this._views.add(player);
    }

    /* TODO: move out to factory */
    private GameLoop buildGameLoop() {
        /* TODO: Replace with Command/CommandHandlers?,
            difference between event handlers|command handler|jobs? */
        var loop = new GameLoop();
        loop.add(new MainJob());
        loop.add(new RoundJob(30));
        loop.add(new SpawnEnemyJob(10, 2));
        return loop;
    }
    @Override
    public void create() {
        this._loop = this.buildGameLoop();
        this._batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new KeyboardAdapter());

        /* TODO: why here? */
        EventDispatcher.getInstance().register(EnemySpawnedEvent.class, this._onEnemySpawn);

        this.createScene();
        this._loop.run();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (!this._loop.isRunning()) {
            return;
        }

        this._batch.begin();

        for (View view: this._views) {
            view.render(_batch);
        }

        this._batch.end();
    }

    @Override
    public void dispose() {
        this._batch.dispose();
        this._batch = null;
        for (View view: this._views) {
            view.dispose();
        }
        this._views = null;
        System.exit(0);
    }
}
