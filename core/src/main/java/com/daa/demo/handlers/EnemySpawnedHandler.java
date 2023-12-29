package com.daa.demo.handlers;

import com.daa.demo.enemy.Enemy;
import com.daa.demo.enemy.EnemyPresenter;
import com.daa.demo.enemy.EnemyView;
import com.daa.demo.events.EnemySpawnedEvent;
import com.daa.demo.events.EventHandler;
import com.daa.demo.mvp.View;
import com.daa.demo.mvp.ViewFactory;

import java.util.ArrayList;
import java.util.Collection;

public class EnemySpawnedHandler implements EventHandler<EnemySpawnedEvent> {
    private final Collection<View> _views = new ArrayList<>();
     @Override
    public void handle(EnemySpawnedEvent event) {
        var views = new ArrayList<View>();
        for(Enemy enemy: event.getEnemies()) {
            views.add(ViewFactory.create(EnemyView.class, EnemyPresenter.class, enemy));
        }
        this._views.addAll(views);
    }

    public Collection<View> getResult() {
         return this._views;
    }
}
