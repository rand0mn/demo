package com.daa.demo.loop;

public interface RepeatableJob extends Job {
    String getTag();
    int getRepeatAtMs();
}
