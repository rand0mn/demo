package com.daa.demo.player;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.daa.demo.Assets;
import com.daa.demo.mvp.BaseView;

public class PlayerView extends BaseView {
    private final Animation<TextureRegion> _idleAnimation;
    private final Animation<TextureRegion> _runLeftAnimation;
    private final Animation<TextureRegion> _runRightAnimation;
    public PlayerView() {
        this._idleAnimation = new Animation<>(0.15f, Assets.getPlayerIdleTextures());
        this._runLeftAnimation = new Animation<>(0.15f, Assets.getPlayerRunningTextures(true));
        this._runRightAnimation = new Animation<>(0.15f, Assets.getPlayerRunningTextures(false));

        this.setAnimation(this._idleAnimation);
    }

    @Override
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
