package com.raj.eggs;


import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

/**
 * Created by stereoHeart on 28/01/2017.
 */
public class BasketList {

    Array<Basket> basketListArray;
    int basketY;


    Constants.BasketType type;

    public BasketList(){

        basketListArray = new Array<Basket>(48);

        // first initialize the basket type to stopping so that the first basket type
        // can be both stopping and moving
        type = Constants.BasketType.stopping;
        init();
    }

    public void init(){

        for(int i=0;i<1000;i+=200){

            // this conditional checks helps to prevent from generation of
            // two consecutive stopping basket by checking previously initialized type
            if(type==Constants.BasketType.moving){
                type = MathUtils.random() <= 0.8f ? Constants.BasketType.moving : Constants.BasketType.stopping;
            }else if(type==Constants.BasketType.stopping){
                type = Constants.BasketType.moving;
            }

            basketListArray.add(new Basket(MathUtils.random((int)Constants.WORLD_WIDTH - (int)Constants.BASKET_WIDTH),i,type));
            basketY = i;


        }
    }

    public void update(float delta){
        for(Basket basket: basketListArray){
            basket.update(delta);
        }


    }

    // this method is to remove the first basket and add the new basket in the basket list as the egg
    // goes up and up
    public void addAndRemove(){
        basketListArray.removeIndex(0);

        // checking the basket type of previous basket so to prevent the consecutive
        // stopping baskets
        if(type==Constants.BasketType.moving){
            type = MathUtils.random() <= 0.8f ? Constants.BasketType.moving : Constants.BasketType.stopping;
        }else if(type==Constants.BasketType.stopping){
            type = Constants.BasketType.moving;
        }

        basketListArray.add(new Basket(MathUtils.random((int)Constants.WORLD_WIDTH - (int)Constants.BASKET_WIDTH),basketY+=200,type));
    }

    public void render(ShapeRenderer renderer){

        for(Basket basket: basketListArray){

            basket.render(renderer);
        }
    }




}
