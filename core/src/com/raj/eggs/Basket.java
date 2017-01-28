package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stereoHeart on 28/01/2017.
 */
public class Basket {

    Vector2 position;
    float basketMoveRate;

    Direction direction;

    public Basket(int x, int y){
        position = new Vector2(x,y);
        basketMoveRate = 20;

        direction = Direction.forward;
    }

    public void update(){
        if(direction==Direction.forward)
            position.x += 20 * Gdx.graphics.getDeltaTime();
        else
            position.x -= basketMoveRate * Gdx.graphics.getDeltaTime();

        if(position.x >= Constants.WORLD_WIDTH - Constants.BASKET_WIDTH){
            direction = Direction.backward;
        }else if(position.x<= 0){
            direction = Direction.forward;
        }
    }

    public void render(ShapeRenderer renderer){
        renderer.rect(position.x,position.y,Constants.BASKET_WIDTH,Constants.BASKET_HEIGHT);
    }

    enum Direction{
        forward,
        backward
    }

}
