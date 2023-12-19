package com.daa.demo.mvp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public abstract class BaseView implements View {
    private float _stateTime = 0;

    private Vector2 _position = new Vector2(0,0);
    private Animation<TextureRegion> _animation;

    @Override
    public void render(Batch batch) {
        Vector2 position = this.getPosition();
        var frame = this.getCurrentFrame();

        batch.draw(
            this.getCurrentFrame(),
            position.x,
            position.y,
            frame.getRegionWidth() * 3f,
            frame.getRegionHeight() * 3f
        );
    }

    public Vector2 getPosition() {
        return this._position;
    }

    public void setPosition(Vector2 position) {
        this._position = position.cpy();
        Gdx.app.log("view", this._position.toString());
    }

    public void setAnimation(Animation<TextureRegion> animation) {
        this._animation = animation;
    }

    public TextureRegion getCurrentFrame() {
        this._stateTime += Gdx.graphics.getDeltaTime();
        return this._animation.getKeyFrame(this._stateTime, true);
    }
}
