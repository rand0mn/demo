package com.daa.demo.player;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.daa.demo.Assets;
import com.daa.demo.Settings;
import com.daa.demo.mvp.BaseView;

public class PlayerView extends BaseView {
    private final Animation<TextureRegion> _idleAnimation;
    private final Animation<TextureRegion> _runLeftAnimation;
    private final Animation<TextureRegion> _runRightAnimation;

    private final Camera _camera;
    private final Viewport _playerViewport;

    public PlayerView() {
        this._camera = new OrthographicCamera();
        this._playerViewport = new ScreenViewport(this._camera);

        this._idleAnimation = new Animation<>(Settings.frameDuration, Assets.getPlayerIdleTextures());
        this._runLeftAnimation = new Animation<>(Settings.frameDuration, Assets.getPlayerRunningTextures(true));
        this._runRightAnimation = new Animation<>(Settings.frameDuration, Assets.getPlayerRunningTextures(false));

        this.setAnimation(this._idleAnimation);
    }

    public void setState(PlayerAction action) {

        switch (action) {
            case LEFT: {
                this.setAnimation(this._runLeftAnimation);
                break;
            }
            case RIGHT: {
                this.setAnimation(this._runRightAnimation);
                break;
            }
            case UP:
            case DOWN:
                break;
            default: this.setAnimation(this._idleAnimation);
        }
    }

    public void updateCameraPosition() {
        this._camera.position.set(this._position.cpy(), 0f);
        this._camera.update();
    }

    @Override
    public void render(Batch batch) {
        this._playerViewport.apply();
        batch.setProjectionMatrix(this._camera.combined);

        this.updateCameraPosition();
        super.render(batch);
    }

    public Camera getCamera() {
        return this._camera;
    }

    public Viewport getViewport() {
        return this._playerViewport;
    }
}
