package com.daa.demo.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Scaling;
import com.daa.demo.Assets;

public class HealthBarWidget implements WidgetFactory<Image> {
    @Override
    public Image create(Skin skin) {
        var image2 = Assets.getHeartsBarUi()[0];
        Image image = new Image(image2);
        image.setScaling(Scaling.fill);

        return image;
    }
}
