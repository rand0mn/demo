package com.daa.demo.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.daa.demo.mvp.BatchRender;


public class Root implements BatchRender {
    private final Table _root;
    private final int _padding = 10;

    public Root() {
        this._root = new Table();
        this._root.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        this._root.setFillParent(true);
        this._root.setBounds(this._padding, Gdx.graphics.getHeight(), Gdx.graphics.getWidth(), -this._padding);

        this._root.top();
        this._root.left();
    }

    public void render(Batch batch) {
        this._root.draw(batch, 1);
    }
    public Cell<?> add(Widget widget) {
        var element = widget.create();
        return this._root.add(element).width(element.getWidth()).height(element.getHeight());
    }
}
