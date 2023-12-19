package com.daa.demo.enemy;

import com.badlogic.gdx.math.Vector2;

public class EnemyFactory {
    public static Enemy create() {
        return new Enemy(new Vector2(1,1));
    }
}
