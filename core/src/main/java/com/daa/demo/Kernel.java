package com.daa.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.daa.demo.enemy.EnemyFactory;
import com.daa.demo.enemy.EnemyPresenter;
import com.daa.demo.enemy.EnemyView;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Kernel extends ApplicationAdapter {
    private SpriteBatch _batch;
    private Collection<View> _views = new ArrayList<>();
    private GameLoop _loop;

    private void createScene() {
        var player = ViewFactory.create(
            PlayerView.class,
            PlayerPresenter.class,
            new Player(new Vector2(10, 10))
        );

        var enemy = ViewFactory.create(
            EnemyView.class,
            EnemyPresenter.class,
            EnemyFactory.create()
        );

        this._views.add(player);
        this._views.add(enemy);
    }

    private void spawnEnemies() {
        var enemies = Stream.generate(EnemyFactory::new)
            .limit(2)
            .map(enemy -> ViewFactory.create(EnemyView.class, EnemyPresenter.class, enemy.get()))
            .collect(Collectors.toCollection(ArrayList::new));
        this._views.addAll(enemies);
    }

    private GameLoop buildGameLoop() {
        var loop = new GameLoop();
        loop.add(new MainJob());
        loop.add(new RoundJob(30));

        return loop;
    }
    @Override
    public void create() {
        this._loop = this.buildGameLoop();
        this._batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new KeyboardAdapter());

        this._loop.run();
        this.createScene();
    }

    @Override
    public void render() {
        if (!this._loop.isRunning()) {
            return;
        }

        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

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
