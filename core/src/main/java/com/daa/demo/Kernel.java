package com.daa.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.daa.demo.mvp.View;
import com.daa.demo.mvp.ViewFactory;
import com.daa.demo.player.KeyboardAdapter;
import com.daa.demo.player.Player;
import com.daa.demo.player.PlayerPresenter;
import com.daa.demo.player.PlayerView;

import java.util.ArrayList;
import java.util.Collection;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Kernel extends ApplicationAdapter {
    private SpriteBatch _batch;
    private Collection<View> _views = new ArrayList<>();


    private void createScene() {
        this._views.add(
            ViewFactory.create(
                PlayerView.class,
                PlayerPresenter.class,
                new Player(new Vector2(10, 10))
            )
        );
    }

    @Override
    public void create() {
        _batch = new SpriteBatch();

        Gdx.input.setInputProcessor(new KeyboardAdapter());

        this.createScene();
    }

    @Override
    public void render() {
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
