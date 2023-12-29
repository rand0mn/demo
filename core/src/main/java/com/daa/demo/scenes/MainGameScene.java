package com.daa.demo.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.daa.demo.mvp.View;
import com.daa.demo.player.KeyboardAdapter;
import com.daa.demo.player.PlayerView;
import com.daa.demo.ui.Hud;
import com.daa.demo.ui.Root;

import java.util.Collection;

/**
 * Стандартная геймпленая сцена, камера следует за игроком, доступен ввод.
 */
public class MainGameScene implements Scene {
    private Hud _hud;
    private PlayerView _player;
    private Collection<View> _actors;
    private SpriteBatch _mainSpriteBatch;
    private SpriteBatch _hudSpriteBatch;

    public MainGameScene(PlayerView player, Collection<View> actors) {
        this._player = player;
        this._actors = actors;
        this._hud = new Hud(new Root());


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

        this._player.render(this._mainSpriteBatch);
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
        this._player.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
    }
}
