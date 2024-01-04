package com.daa.demo.ui;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;

public class DebugInfoWidget implements WidgetFactory<TextArea> {
    @Override
    public TextArea create(Skin skin){
        var text = new TextArea("Debug", skin);
        text.setAlignment(Align.left);
        text.setText(String.format("Player position: %s", new Vector2(0, 0)));

        return text;
    }
}
