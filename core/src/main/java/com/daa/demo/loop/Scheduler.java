package com.daa.demo.loop;

import com.badlogic.gdx.Gdx;

import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class Scheduler {
    private final Map<String, SimpleEntry<ScheduledFuture<?>, Runnable>> _jobs = new HashMap<>();
    private final ExecutorService _mainExecutorService;
    private final ScheduledExecutorService _schedulerExecutorService;

    public Scheduler() {
        this._mainExecutorService = Executors.newCachedThreadPool();
        this._schedulerExecutorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void submit(RepeatableJob job) {
        this.submitJob(job.getTag(), job::execute, job.getStartAtMs(), job.getRepeatAtMs());
    }

    public void submit(Job job) {
        this.submitOnce(job::execute, job.getStartAtMs());
    }

    public ScheduledFuture<?> submitJob(String tag, final Runnable runnable, int startAtMillisecond, int intervalMilliseconds) {
        if (this._jobs.containsKey(tag)) {
            return this._jobs.get(tag).getKey();
        }

        Runnable jobRun = () -> this._mainExecutorService.submit(runnable);


        var job = this._schedulerExecutorService.scheduleAtFixedRate(jobRun, startAtMillisecond, intervalMilliseconds, TimeUnit.MILLISECONDS);

        Runnable cancelJobRun = () -> job.cancel(true);
        this._jobs.put(tag, new SimpleEntry<>(job, cancelJobRun));

        return job;
    }

    public void submitOnce(final Runnable runnable, int startAtMillisecond) {
        var executor = CompletableFuture.delayedExecutor(startAtMillisecond, TimeUnit.MILLISECONDS);
        CompletableFuture.runAsync(runnable, executor);
    }

    public void cancelJob(String tag) {
        var job = this._jobs.get(tag);

        if (job == null) {
            return;
        }

        var schedulerTask = job.getKey();
        var cancelMainTaskRunnable = job.getValue();

        schedulerTask.cancel(true);
        this._mainExecutorService.submit(cancelMainTaskRunnable);
    }
}
