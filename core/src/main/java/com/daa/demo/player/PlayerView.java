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

    public PlayerView() {
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
}
