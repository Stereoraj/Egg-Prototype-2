package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by stereoHeart on 29/01/2017.
 */
public class Egg {
    Vector2 position;
    Movement movement;
    float velocity;
    float curCamPosition;


    BasketList basketList;
    int basketNo;

    int score;
    int life;


    // use to check the camera members for the egg reinitiation action
    Camera camera;

    public Egg(BasketList basketList,Camera camera){

        this.basketList = basketList;
        this.camera = camera;

        curCamPosition = camera.position.y;

        // position vector of the egg
        position = new Vector2();

        // first initialize the movement of the egg to stopping state
        movement = Movement.stopping;

        // used to keep the track of which basket the egg is in currently
        // first initialized to the 0th basket
        basketNo = 0;


        life = 6;


        // first initialize the position of the egg to the 0th basket
        this.position.x = (basketList.basketListArray.get(basketNo).getPosition()).x + Constants.BASKET_WIDTH/2;
        this.position.y = (basketList.basketListArray.get(basketNo).getPosition()).y + Constants.BASKET_HEIGHT + 10;
    }



    public void render(ShapeRenderer renderer){
        System.out.println(score);
        renderer.setColor(Color.YELLOW);
        renderer.circle(position.x,position.y,20,80);
    }

    public void update(){



        // if the movement state of the egg is in the stopping state then check for the key pressed or touched
        if(movement != Movement.moving){
            if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isTouched()) {


                // when the key is pressed , initialize the velocity in which the egg is to be bounced
                velocity = Constants.INITIAL_JUMP_VELOCITY;

                // then the state of the egg changes to moving state
                movement = Movement.moving;
            }
        }
        if(movement == Movement.moving) {
            eggJump();

            // check only if the egg/ball is in the falling direction i.e
            // negative velocity, then check for the collision with the next basket based on the basketNo
            if(velocity<0) {
                if (this.position.y >= basketList.basketListArray.get(basketNo + 1).getPosition().y + Constants.BASKET_HEIGHT &&
                        this.position.y <= basketList.basketListArray.get(basketNo + 1).getPosition().y + Constants.BASKET_HEIGHT + 20) {
                    checkCollision();

                }

            }

        }
        else {

            this.position.x = (basketList.basketListArray.get(basketNo).getPosition()).x + Constants.BASKET_WIDTH/2;
            this.position.y = (basketList.basketListArray.get(basketNo).getPosition()).y + Constants.BASKET_HEIGHT + 10;
        }

        if(life == 0){
            Settings.save();
            Gdx.app.exit();
        }
    }

    void eggJump(){

        velocity += Constants.GRAVITY * Gdx.graphics.getDeltaTime();
        position.y += velocity;

        if(position.y < camera.position.y - (Constants.WORLD_HEIGHT/2) - 100){
            movement = Movement.stopping;
            life--;
        }

    }

    void checkCollision(){

        if(this.position.x > basketList.basketListArray.get(basketNo+1).getPosition().x &&
                this.position.x < basketList.basketListArray.get(basketNo+1).getPosition().x + Constants.BASKET_WIDTH) {

            if (basketNo < 2) {
                // if the egg collides with the basket then increase the basketNo
                basketNo++;
            }else
                basketList.addAndRemove();

            // change the state of the egg to stopping
            movement = Movement.stopping;

            score+=10;


        }
    }

    public Vector2 getPosition(){
        return position;
    }

    public int getScore(){
        return score;
    }

    public int getLife(){
        return life;
    }

    enum Movement{
        moving,
        stopping
    }
}
