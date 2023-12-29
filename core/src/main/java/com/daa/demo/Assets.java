package com.daa.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.stream.Stream;

/* TODO: Пока грузим как-нибудь. Нет понимания как хранить ассеты,
    и как с ними раотать в libgdx */
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

    public static TextureRegion[] getSlimeMovingTexture1() {
        /* две анимации в одном файле по строчно */
        return Arrays.stream(
                Assets.loadFrames("big_slime/Slime_SS.png", 7, 2, false, false)
            ).limit(6).toArray(TextureRegion[]::new);
    }

    public static TextureRegion[] getHeartsBarUi() {
        return Arrays.stream(
            Assets.loadFrames("ui/Hearts.png",5, 3, false, false)
        ).limit(5).toArray(TextureRegion[]::new);
    }
}
