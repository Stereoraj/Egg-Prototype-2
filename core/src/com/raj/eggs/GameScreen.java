package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
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
    Camera camera;
    Basket basket;

    public GameScreen() {
        super();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        camera = viewport.getCamera();
        camera.translate(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,0);
        camera.update();

        basket = new Basket(100,100);
        renderer = new ShapeRenderer();

        //basket = new Basket(20,50);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        basket.update();

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.circle(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,50,80);
        //renderer.rect(Constants.WORLD_WIDTH - 100,50,100,30);
        basket.render(renderer);
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
