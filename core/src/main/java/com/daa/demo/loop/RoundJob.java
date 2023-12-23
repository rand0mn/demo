package com.daa.demo.loop;

import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.RoundEndEvent;

public class RoundJob implements SimpleJob {
    private int _roundLengthSeconds;
    public RoundJob(int roundLengthSeconds) {
        this._roundLengthSeconds = roundLengthSeconds;
    }
    @Override
    public int getStartAtMs() {
        return this._roundLengthSeconds * 1000;
    }

    @Override
    public void execute() {
        EventDispatcher.getInstance().dispatch(new RoundEndEvent());
    }
}
