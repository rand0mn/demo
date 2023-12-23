package com.daa.demo.loop;

import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.LoopUpdateEvent;

public class MainJob implements RepeatableJob {
    @Override
    public String getTag() {
        return "main";
    }

    @Override
    public int getStartAtMs() {
        return 0;
    }

    @Override
    public int getRepeatAtMs() {
        return 20;
    }

    @Override
    public void execute() {
        EventDispatcher.getInstance().dispatch(new LoopUpdateEvent());
    }
}
