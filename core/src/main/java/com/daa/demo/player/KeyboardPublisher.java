package com.daa.demo.player;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.daa.demo.events.EventDispatcher;
import com.daa.demo.events.PlayerEvent;
import com.daa.demo.utils.Vector2Utils;

import java.util.Collection;
import java.util.HashSet;

public class KeyboardPublisher extends InputAdapter {
    private final EventDispatcher _events = EventDispatcher.getInstance();
    private final Collection<Integer> _keysDown = new HashSet<>();



    @Override
    public boolean keyDown(int keycode) {
        this._keysDown.add(keycode);

        switch (keycode) {
            case Input.Keys.A: {
                this._events.dispatch(new PlayerEvent(PlayerAction.LEFT, Vector2Utils.left));
                return true;
            }
            case Input.Keys.D: {
                this._events.dispatch(new PlayerEvent(PlayerAction.RIGHT, Vector2Utils.right));
                return true;
            }
            case Input.Keys.W: {
                this._events.dispatch(new PlayerEvent(PlayerAction.UP, Vector2Utils.top));
                return true;
            }
            case Input.Keys.S: {
                this._events.dispatch(new PlayerEvent(PlayerAction.DOWN, Vector2Utils.down));
                return true;
            }
            default:
                return false;
        }
    }

    @Override
    public boolean keyUp(int keycode) {
        this._keysDown.remove(keycode);

        if (this._keysDown.isEmpty()) {
            this._events.dispatch(new PlayerEvent(PlayerAction.IDLE, Vector2Utils.zero));
        }

        return true;
    }
}
