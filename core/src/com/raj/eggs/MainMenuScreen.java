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
 * Created by stereoHeart on 02/02/2017.
 */
public class MainMenuScreen extends ScreenAdapter implements InputProcessor {

    Viewport viewport;
    ShapeRenderer renderer;

    SpriteBatch batch;


    EggGame eggGame;

    // bounds for the buttons on the main menu screen

    Rectangle playBounds;
    Rectangle scoresBounds;
    Rectangle exitBounds;

    public MainMenuScreen(EggGame eggGame){
        this.eggGame = eggGame;
    }

    @Override
    public void show() {
        super.show();
        viewport = new StretchViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);
        renderer = new ShapeRenderer();

        batch = new SpriteBatch();

        viewport.getCamera().translate(Constants.WORLD_WIDTH/2, Constants.WORLD_HEIGHT/2,0);
        viewport.getCamera().update();

        Gdx.input.setInputProcessor(this);

        // boundaries for the menu buttons
        playBounds = new Rectangle(100,360,180,50);
        scoresBounds = new Rectangle(100,260,180,50);
        exitBounds = new Rectangle(100,160,180,50);

    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
        dispose();
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        //update();

        viewport.apply();

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);
        renderer.setColor(Color.YELLOW);
        renderer.circle(180,430,30,80);
        renderer.setColor(Color.BROWN);
        renderer.rect(100,360,180,50);
        renderer.rect(100,260,180,50);
        renderer.rect(100,160,180,50);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
            Settings.titleFont.setColor(Color.FOREST);
            Settings.titleFont.draw(batch,"EGGS",110,570);
        batch.end();

        batch.begin();
            Settings.textFont.setColor(Color.WHITE);
            Settings.textFont.draw(batch,"PLAY",140,400);
            Settings.textFont.draw(batch,"SCORES",120,300);
            Settings.textFont.draw(batch,"EXIT",140,200);
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

        renderer.dispose();
        batch.dispose();

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
        System.out.println(screenX + "\n " + screenY);
        Vector2 point = new Vector2();
        viewport.unproject(point.set(Gdx.input.getX(),Gdx.input.getY()));
        System.out.println(point.x);
        System.out.println(point.y);

        if(playBounds.contains(point.x,point.y)){
            eggGame.setScreen(new GameScreen(eggGame));

        }else if(scoresBounds.contains(point.x,point.y)){
            //
            eggGame.setScreen(new ScoresScreen(eggGame));
        }else if(exitBounds.contains(point.x,point.y)){
            Gdx.app.exit();
        }

        return true;
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
