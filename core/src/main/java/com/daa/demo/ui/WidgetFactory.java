package com.daa.demo.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

@FunctionalInterface
public interface WidgetFactory<T extends Widget> {
    T create(Skin skin);
}
