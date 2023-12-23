package com.daa.demo.loop;

import java.util.ArrayList;
import java.util.Collection;

public class GameLoop {
    private final Collection<RepeatableJob> _jobs = new ArrayList<>();
    private final Collection<SimpleJob> _singleRunJobs = new ArrayList<>();

    private final Scheduler _scheduler;

    private boolean _isRunning = false;
    private boolean _isInitialized = false;
    public GameLoop() {
        this._scheduler = new Scheduler();
    }

    public void add(RepeatableJob job) {
        this._jobs.add(job);
    }

    public void add(SimpleJob job) {
        this._singleRunJobs.add(job);
    }

    public void run() {
        for (RepeatableJob job: this._jobs) {
            this._scheduler.submit(job);
        }

        for (Job job: this._singleRunJobs) {
            this._scheduler.submit(job);
        }

        this._isInitialized = true;
        this._isRunning = true;
    }


    public boolean isRunning() {
        return this._isRunning;
    }

    public boolean isInitialized() {
        return this._isInitialized;
    }
}
