package com.daa.demo.enemy;

import com.daa.demo.events.EnemySpawnedEvent;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.loop.RepeatableJob;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpawnEnemyJob  implements RepeatableJob {
    private final int _spawnIntervalSec;
    private final int _spawnCount;

    public SpawnEnemyJob(int spawnIntervalSec, int spawnCount) {
        this._spawnIntervalSec = spawnIntervalSec;
        this._spawnCount = spawnCount;
    }
    @Override
    public void execute() {
        var enemies = Stream.generate(EnemyFactory::new)
            .limit(this._spawnCount)
            .map(EnemyFactory::get)
            .collect(Collectors.toCollection(ArrayList::new));

        EventDispatcher.getInstance().dispatch(new EnemySpawnedEvent(enemies));
    }
    @Override
    public int getStartAtMs() {
        return 0;
    }

    @Override
    public String getTag() {
        return "Enemy spawner";
    }

    @Override
    public int getRepeatAtMs() {
        return this._spawnIntervalSec * 1000;
    }
}
