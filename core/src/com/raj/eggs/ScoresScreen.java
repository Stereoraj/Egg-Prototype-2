package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by stereoHeart on 04/02/2017.
 */
public class ScoresScreen extends ScreenAdapter {
    EggGame eggGame;

    SpriteBatch batch;
    ShapeRenderer renderer;

    Viewport viewport;

    public ScoresScreen(EggGame eggGame){
        this.eggGame = eggGame;
    }

    @Override
    public void show() {
        super.show();

        batch = new SpriteBatch();
        renderer = new ShapeRenderer();

        viewport = new StretchViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        viewport.getCamera().translate(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,0);
        viewport.getCamera().update();

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        viewport.apply();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.YELLOW);
        renderer.circle(Constants.WORLD_WIDTH/2,430,40,80);
        renderer.circle(Constants.WORLD_WIDTH/2,340,40,80);
        renderer.circle(Constants.WORLD_WIDTH/2,250,40,80);

        renderer.setColor(Color.BROWN);
        renderer.rect(100,100,180,50);
        renderer.rect(100,20,180,50);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        Settings.titleFont.draw(batch,"HighScores",30,580);

        Settings.textFont.draw(batch, Settings.highScores[2] + "", Constants.WORLD_WIDTH / 2 - 10, 438);
        Settings.textFont.draw(batch, Settings.highScores[1] + "", Constants.WORLD_WIDTH / 2 - 10, 348);
        Settings.textFont.draw(batch, Settings.highScores[0] + "", Constants.WORLD_WIDTH / 2 - 10, 258);

        Settings.textFont.draw(batch,"RESET",135,142);
        Settings.textFont.draw(batch,"BACK",135,62);

        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        viewport.update(width,height);
    }



    @Override
    public void hide() {
        super.hide();
    }
}
