package com.daa.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.stream.Stream;

public class Assets {
    private Assets() {

    }

    private static TextureRegion[] loadFrames(
        String path,
        int frameColumns,
        int frameRows,
        boolean flipX,
        boolean flipY
    ) {
        var sheet = new Texture(Gdx.files.internal(path));

        var frames = TextureRegion.split(
            sheet,
            sheet.getWidth() / frameColumns,
            sheet.getHeight() / frameRows
        );
        return Stream.of(frames)
            .flatMap(Stream::of)
            .peek(texture -> texture.flip(flipX, flipY))
            .toArray(TextureRegion[]::new);
    }
    public static TextureRegion[] getPlayerIdleTextures() {
        return Assets.loadFrames("blue_witch/B_witch_idle.png", 1,6, false, false);
    }

    public static TextureRegion[] getPlayerRunningTextures(boolean flip) {
        return Assets.loadFrames("blue_witch/B_witch_run.png", 1, 8, flip, false);
    }
}
