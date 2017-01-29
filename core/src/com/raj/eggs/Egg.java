package com.raj.eggs;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stereoHeart on 29/01/2017.
 */
public class Egg {
    Vector2 position;

    public Egg(){
        position = new Vector2(Constants.WORLD_WIDTH/2,0);
    }

    public void update(float delta){
        position.y+=100*delta;
    }

    public void render(ShapeRenderer renderer){
        renderer.circle(position.x,position.y,20,80);
    }

    public Vector2 getPosition(){
        return position;
    }
}
