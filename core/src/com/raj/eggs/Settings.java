package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Created by stereoHeart on 01/02/2017.
 */
public class Settings {

    public static int highScores;
    public final static String filename = ".eggGame";

    public static void load(){

        try{
            FileHandle fileHandle = Gdx.files.external(filename);
            highScores = Integer.parseInt(fileHandle.readString());
        }catch(Exception e){}
    }

    public static void save(){
        try{
            FileHandle fileHandle = Gdx.files.external(filename);
            fileHandle.writeString(Integer.toString(highScores),false);
        }catch(Exception ex){}
    }


}
