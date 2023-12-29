package com.daa.demo.stages;

import com.daa.demo.loop.GameLoop;
import com.daa.demo.scenes.Scene;

/**
 * Игровой уровень. Сцена - отвечает за вьюпорт, камеру и
 * отрисовку вьюх игровых объектов и GUI виджетов.
 *
 * Loop - генерация игровых и системных ивентов.
 */
public interface Stage {
    Scene getScene();
    GameLoop getGameLoop();
}
