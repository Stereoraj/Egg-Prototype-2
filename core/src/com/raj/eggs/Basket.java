package com.raj.eggs;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stereoHeart on 28/01/2017.
 */
public class Basket {

    Vector2 position;
    float basketMoveRate;

    Direction direction;

     Constants.BasketType type;

    public Basket(int x, int y, Constants.BasketType type){
        position = new Vector2(x,y);

        // generate the random values for the 4 different speed of the basket
        basketMoveRate = MathUtils.random(1,4);

        // assign the appropriate basket speed
        if(basketMoveRate == 1){
            basketMoveRate = 80;
        }else if(basketMoveRate == 2){
            basketMoveRate = 100;
        }else if(basketMoveRate == 3){
            basketMoveRate = 120;
        }else if(basketMoveRate == 4){
            basketMoveRate = 140;
        }

        this.type = type;

        direction = Direction.forward;
    }

    public void update(float delta){
        if(type == Constants.BasketType.moving) {
            if (direction == Direction.forward)
                position.x += basketMoveRate * delta;
            else
                position.x -= basketMoveRate * delta;

            if (position.x >= Constants.WORLD_WIDTH - Constants.BASKET_WIDTH) {
                direction = Direction.backward;
            } else if (position.x <= 0) {
                direction = Direction.forward;
            }
        }
    }

    public void render(ShapeRenderer renderer){
        renderer.setColor(Color.BROWN);
        renderer.rect(position.x,position.y,Constants.BASKET_WIDTH,Constants.BASKET_HEIGHT);
    }

    public Vector2 getPosition(){
        return position;
    }


    enum Direction{
        forward,
        backward
    }





}
