package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by stereoHeart on 28/01/2017.
 */
public class GameScreen extends ScreenAdapter {

    Viewport viewport;
    ShapeRenderer renderer;

    public GameScreen() {
        super();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        viewport.getCamera().translate(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,0);
        renderer = new ShapeRenderer();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.circle(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,50,80);
        renderer.end();


    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height);
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
