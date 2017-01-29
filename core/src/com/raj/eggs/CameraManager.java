package com.raj.eggs;

import com.badlogic.gdx.graphics.Camera;

/**
 * Created by stereoHeart on 29/01/2017.
 */
public class CameraManager {
    Camera camera;
    float cameraMaxY;
    Egg egg;

    public CameraManager(Camera camera,Egg egg){
        this.camera = camera;
        cameraMaxY = this.camera.position.y;
        this.egg = egg;
    }

    public void update(float delta){

        camera.translate(0,egg.getPosition().y - cameraMaxY,0);
        camera.update();

        cameraMaxY = camera.position.y;
    }
}
