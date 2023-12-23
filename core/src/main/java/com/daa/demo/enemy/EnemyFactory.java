package com.daa.demo.enemy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.daa.demo.Settings;

import java.util.function.Supplier;

public class EnemyFactory implements Supplier<Enemy> {
    public EnemyFactory() {
        /* Keep public for Supplier call: EnemyFactory::new */
    }

    public static Enemy create() {
        int x = MathUtils.random(Settings.screenWidth);
        int y = MathUtils.random(Settings.screenHeight);

        var enemy = new Enemy(new Vector2(x,y));
        Gdx.app.log("EnemyFactory", enemy.getPosition().toString());
        return enemy;
    }

    @Override
    public Enemy get() {
        return EnemyFactory.create();
    }
}
