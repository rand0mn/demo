package com.daa.demo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Scaling;
import com.daa.demo.mvp.BatchRender;

public class Hud implements BatchRender {
    private final Root _root;

    public Hud(Root root) {
        this._root = root;

        this._root.add(() -> {
                var image2 = new TextureRegion(new Texture(Gdx.files.internal("libgdx.png")));

                Image image = new Image(image2);
                image.setScaling(Scaling.fill);
                image.setPosition(0, 0);
                return image;
            }
        ).row();
    }

    @Override
    public void render(Batch batch) {
        this._root.render(batch);
    }
}
