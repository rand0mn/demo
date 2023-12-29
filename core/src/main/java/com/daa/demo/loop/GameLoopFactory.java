package com.daa.demo.loop;

import com.daa.demo.enemy.SpawnEnemyJob;

public class GameLoopFactory {
    public static GameLoop create() {
        /* TODO: Replace with Command/CommandHandlers?,
            difference between event handlers|command handler|jobs? */
        var loop = new GameLoop();
        loop.add(new MainJob());
        loop.add(new RoundJob(30));
        loop.add(new SpawnEnemyJob(10, 2));
        return loop;
    }
}
