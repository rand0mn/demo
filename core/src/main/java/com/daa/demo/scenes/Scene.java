package com.daa.demo.scenes;

import com.daa.demo.mvp.Render;
import com.daa.demo.mvp.View;

import java.util.Collection;

public interface Scene extends Render {
    void addActors(Collection<View> actors);
}
