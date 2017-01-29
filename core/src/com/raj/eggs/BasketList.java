package com.raj.eggs;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Created by stereoHeart on 28/01/2017.
 */
public class BasketList {

    Array<Basket> basketListArray;

    public BasketList(){

        basketListArray = new Array<Basket>(48);
        init();
    }

    public void init(){
        for(int i=0;i<10000;i+=100){
            basketListArray.add(new Basket(MathUtils.random((int)Constants.WORLD_WIDTH - (int)Constants.BASKET_WIDTH),i));
        }
    }

    public void update(float delta){
        for(Basket basket: basketListArray){
            basket.update(delta);
        }


    }

    public void render(ShapeRenderer renderer){

        for(Basket basket: basketListArray){

            basket.render(renderer);
        }
    }
}
