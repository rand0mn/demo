package com.daa.demo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.Disposable;
import com.daa.demo.stages.Stage;
import com.daa.demo.stages.TestStage;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Kernel extends ApplicationAdapter implements Disposable {
    private Stage _stage;


    @Override
    public void create() {
        this._stage = new TestStage();
        this._stage.getGameLoop().run();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!this._stage.getGameLoop().isRunning()) {
            return;
        }

        this._stage.getScene().render();

    }

    @Override
    public void resize(int width, int height) {
        this._stage.getScene().update();
    }

    @Override
    public void dispose() {
        /* TODO: Потоки не завершаются. Надо завершать потоки и сохранять состояние */
        System.exit(0);
    }

}
