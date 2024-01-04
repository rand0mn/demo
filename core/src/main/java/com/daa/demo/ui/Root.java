package com.daa.demo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.daa.demo.mvp.BatchRender;

import java.util.HashMap;
import java.util.Map;


public class Root implements BatchRender {
    private final Map<WidgetFactory<?>, Widget> _widgets;

    private final Skin _skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

    private final Table _root;
    private final int _padding = 10;

    public Root() {
        this._widgets = new HashMap<>();
        this._root = new Table();
        this._root.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this._root.setFillParent(true);

        this._root.top();
        this._root.left();
        this._root.debug();
    }

    public void render(Batch batch) {
        this._root.draw(batch, 1);
    }
    public Cell<?> createWidget(WidgetFactory<?> widget) {
        var element = widget.create(this._skin);
        this._widgets.put(widget, element);
        return this._root.add(element);
    }

    public <T extends Widget> T getWidget(WidgetFactory<T> widget) {
        return (T) this._widgets.get(widget);
    }
}
