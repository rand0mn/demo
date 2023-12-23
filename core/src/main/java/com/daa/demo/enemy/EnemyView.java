package com.daa.demo.enemy;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.daa.demo.Assets;
import com.daa.demo.Settings;
import com.daa.demo.mvp.BaseView;
import com.daa.demo.player.PlayerAction;

public class EnemyView extends BaseView {
    private final Animation<TextureRegion> _runAnimation;

    public EnemyView() {
        _runAnimation = new Animation<>(Settings.frameDuration, Assets.getSlimeMovingTexture1());
        this.setAnimation(this._runAnimation);
    }

}
