package com.daa.demo.events;

import com.daa.demo.enemy.Enemy;

import java.util.Collection;

public class EnemySpawnedEvent extends Event {
    private final Collection<Enemy> _enemies;

    public EnemySpawnedEvent(Collection<Enemy> enemies) {
        this._enemies = enemies;
    }

    public Collection<Enemy> getEnemies() {
        return this._enemies;
    }
}
