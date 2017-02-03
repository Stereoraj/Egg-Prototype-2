package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    BasketList basketList;
    Egg egg;
    CameraManager camManager;

    SpriteBatch batch;
    BitmapFont font;

    EggGame eggGame;



    public GameScreen(EggGame eggGame) {

        this.eggGame = eggGame;

        // create the viewport of the game with the world width and the height
        viewport = new ExtendViewport(Constants.WORLD_WIDTH,Constants.WORLD_HEIGHT);

        // initializing and positioning the camera to the center of the screen
        camera = viewport.getCamera();
        camera.translate(Constants.WORLD_WIDTH/2,0,0);
        camera.update();

        // the basketList object to manage the list of baskets in the game
        basketList = new BasketList();

        // pass the basketList and Camera reference to the new egg object to check the values
        // of each during the mechanism of the egg movement
        egg = new Egg(basketList,camera);

        // the renderer to draw the basic game objects
        renderer = new ShapeRenderer();

        // the camera manager object to move the camera with the gameplay
        camManager = new CameraManager(camera,egg);

        ////////
        batch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("myFont.fnt"));

        Settings.load();



    }

    @Override
    public void render(float delta) {

        // perform the updating process of the baskets , eggs and the camera
        basketList.update(delta);
        egg.update();
        camManager.update(delta);

        Gdx.gl.glClearColor(Constants.BACKGROUND_COLOR.r,Constants.BACKGROUND_COLOR.g,Constants.BACKGROUND_COLOR.b,Constants.BACKGROUND_COLOR.a);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera().combined);

        renderer.begin(ShapeRenderer.ShapeType.Filled);

            egg.render(renderer);
            basketList.render(renderer);

        renderer.end();



        font.setColor(Color.BLACK);
        batch.begin();
            font.draw(batch,"Score :: " + egg.getScore() + "",10.0f,Constants.WORLD_HEIGHT + 20);
            font.draw(batch,"Life :: " + egg.getLife() + "",10.0f,Constants.WORLD_HEIGHT - 8);
            font.draw(batch,"High Score :: " + Settings.highScores + "",10.0f,Constants.WORLD_HEIGHT - 36);
        batch.end();

        if(Settings.highScores < egg.getScore()){
            Settings.highScores = egg.getScore();

        }

        if(egg.getLife() == 0){
            Settings.save();
            eggGame.setScreen(new MainMenuScreen(eggGame));
        }

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

    }
}
