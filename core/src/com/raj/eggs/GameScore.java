package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by stereoHeart on 04/02/2017.
 */
public class GameScore extends ScreenAdapter implements InputProcessor{

    EggGame eggGame;
    ShapeRenderer renderer;
    SpriteBatch batch;
    Viewport viewport;

    Rectangle menuBounds;

    String msg;
    int score;

    public GameScore(EggGame eggGame ,String msg, int score) {
        super();
        this.eggGame = eggGame;

        this.msg = msg;
        this.score = score;
    }

    @Override
    public void show() {
        super.show();

        renderer = new ShapeRenderer();
        batch = new SpriteBatch();

        viewport = new StretchViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        viewport.getCamera().translate(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2,0);
        viewport.getCamera().update();

        menuBounds = new Rectangle(90,150,200,50);

        Gdx.input.setInputProcessor(this);

    }

    @Override
    public void render(float delta) {
        super.render(delta);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.YELLOW);
        renderer.circle(Constants.WORLD_WIDTH/2,Constants.WORLD_HEIGHT/2 +30,60,80);
        renderer.setColor(Color.BROWN);
        renderer.rect(90,150,200,50);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        Settings.titleFont.setColor(Color.FOREST);
        Settings.titleFont.draw(batch,msg,30,500);
        Settings.textFont.setColor(Color.FIREBRICK);
        Settings.textFont.draw(batch,score + "",160,350);
        Settings.textFont.setColor(Color.WHITE);
        Settings.textFont.draw(batch,"MENU",135,190);
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

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Vector2 point = new Vector2();

        viewport.unproject(point.set(Gdx.input.getX(),Gdx.input.getY()));

        if(menuBounds.contains(point.x,point.y)){
            Gdx.input.setInputProcessor(null);
            eggGame.setScreen(new MainMenuScreen(eggGame));
        }


        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
