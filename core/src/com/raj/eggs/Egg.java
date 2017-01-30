package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stereoHeart on 29/01/2017.
 */
public class Egg {
    Vector2 position;
    Movement movement;
    float velocity;


    BasketList basketList;
    int basketNo;

    public Egg(BasketList basketList){
        position = new Vector2();
        movement = Movement.stopping;
        this.basketList = basketList;
        basketNo = 0;



        this.position.x = (basketList.basketListArray.get(basketNo).getPosition()).x + 25;
        this.position.y = (basketList.basketListArray.get(basketNo).getPosition()).y + 30;
    }

    /*public void update(float delta){
        position.y+=100*delta;
    }*/

    public void render(ShapeRenderer renderer){
        renderer.circle(position.x,position.y,20,80);
    }

    public void update(float delta){


        // bouncing the ball
        if(movement != Movement.moving){
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {

                // set the initial value of the velocity

                velocity = Constants.INITIAL_JUMP_VELOCITY;

                movement = Movement.moving;
            }
        }
        if(movement == Movement.moving) {
            ballJump();

            // check only if the egg/ball is in the falling direction
            if(velocity<0) {
                if (this.position.y >= basketList.basketListArray.get(basketNo + 1).getPosition().y + Constants.BASKET_HEIGHT &&
                        this.position.y <= basketList.basketListArray.get(basketNo + 1).getPosition().y + Constants.BASKET_HEIGHT + 20) {
                    checkCollision();

                }

            }

        }
        else {

            this.position.x = (basketList.basketListArray.get(basketNo).getPosition()).x + 25;
            this.position.y = (basketList.basketListArray.get(basketNo).getPosition()).y + 28;
        }

    }

    void ballJump(){

        velocity += Constants.GRAVITY * Gdx.graphics.getDeltaTime();
        position.y += velocity;

        if(position.y < 0){
            movement = Movement.stopping;
        }

    }

    void checkCollision(){

        if(this.position.x > basketList.basketListArray.get(basketNo+1).getPosition().x &&
                this.position.x < basketList.basketListArray.get(basketNo+1).getPosition().x + Constants.BASKET_WIDTH){
            basketNo++;
            movement = Movement.stopping;


        }
    }

    public Vector2 getPosition(){
        return position;
    }

    enum Movement{
        moving,
        stopping
    }
}
