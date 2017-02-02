package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by stereoHeart on 02/02/2017.
 */
public class MainMenuScreen extends ScreenAdapter {

    Viewport viewport;
    ShapeRenderer renderer;

    SpriteBatch batch;
    BitmapFont font;


    @Override
    public void show() {
        super.show();
        viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderer = new ShapeRenderer();

        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("mainMenu.fnt"));

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        viewport.apply();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.YELLOW);
        renderer.circle(180,430,30,80);
        renderer.setColor(Color.BROWN);
        renderer.rect(100,360,180,50);
        renderer.rect(100,260,180,50);
        renderer.rect(100,160,180,50);
        renderer.end();

        batch.begin();
            font.setColor(Color.FOREST);
            font.draw(batch,"EGGS",140,550);
        batch.end();

        batch.begin();
            font.setColor(Color.WHITE);
            font.draw(batch,"PLAY",140,400);
            font.draw(batch,"SCORES",120,300);
            font.draw(batch,"EXIT",140,200);
        batch.end();
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
