package com.raj.eggs;

/**
 * Created by stereoHeart on 28/01/2017.
 */
public class Constants {

    // the default size of the viewport window

    public static final float WORLD_WIDTH = 380;
    public static final float WORLD_HEIGHT = 600;

    // the constant values for the basket

    public static final float BASKET_WIDTH = 100;
    public static final float BASKET_HEIGHT = 30;

    public static final float GRAVITY = -8.8f;
    public static final float INITIAL_JUMP_VELOCITY = 9.0f;

    public enum BasketType{
        moving,
        stopping
    }

}
