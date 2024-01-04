package com.daa.demo.ui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.utils.Align;
import com.daa.demo.mvp.View;

public class HudView implements View {
    private final Root _root;
    private final WidgetFactory<?> _healthBarWidget = new HealthBarWidget();
    private final WidgetFactory<TextArea> _debugInfoWidget = new DebugInfoWidget();


    public HudView(Root root) {
        this._root = root;

        this._root.createWidget(this._healthBarWidget).align(Align.topLeft).expandX();
        this._root.createWidget(this._debugInfoWidget).align(Align.topRight).minWidth(200);
    }

    public void setDebugMessage(String message) {
        this._root.getWidget(this._debugInfoWidget).setText(message);
    }

    @Override
    public void render(Batch batch) {
        this._root.render(batch);
    }

    @Override
    public void dispose() {

    }
}
