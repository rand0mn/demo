package com.daa.demo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.daa.demo.mvp.BatchRender;
import com.daa.demo.mvp.View;
import com.daa.demo.player.KeyboardAdapter;
import com.daa.demo.player.PlayerView;
import com.daa.demo.ui.HudView;

import java.util.Collection;

/**
 * Стандартная геймпленая сцена, камера следует за игроком, доступен ввод.
 */
public class MainGameScene implements Scene {
    private final BatchRender _hud;
    private final PlayerView _player;
    private final Collection<View> _actors;
    private final Batch _mainSpriteBatch;
    private final Batch _hudSpriteBatch;
    private final Viewport _viewport;
    private final Camera _camera;

    public MainGameScene(PlayerView player, HudView hudView, Collection<View> actors) {
        this._camera = new OrthographicCamera();
        this._viewport = new ScreenViewport(this._camera);

        this._player = player;
        this._actors = actors;
        this._hud = hudView; //new Hud(new Root());



        this._mainSpriteBatch = new SpriteBatch();
        this._hudSpriteBatch = new SpriteBatch();

        Gdx.input.setInputProcessor(new KeyboardAdapter());
    }

    public void addActors(Collection<View> actors) {
        this._actors.addAll(actors);
    }

    @Override
    public void render() {
        this._mainSpriteBatch.begin();

        this._viewport.apply();
        this._player.render(this._mainSpriteBatch);
        this._mainSpriteBatch.setProjectionMatrix(this._camera.combined);
        this._camera.position.set(this._player.getPosition(), 0f);
        this._camera.update();

        for (View view: this._actors) {
            view.render(this._mainSpriteBatch);
        }

        this._mainSpriteBatch.end();

        this._hudSpriteBatch.begin();
        this._hud.render(this._hudSpriteBatch);
        this._hudSpriteBatch.end();
    }

    @Override
    public void update() {
        this._viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
}
