package com.raj.eggs;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by stereoHeart on 01/02/2017.
 */
public class Settings {

    public static int[] highScores = {0,0,0};
    public final static String filename = ".eggGame";

    public static BitmapFont textFont, titleFont;

    public static void load(){

        try{
            FileHandle fileHandle = Gdx.files.external(filename);

            String[] strings = fileHandle.readString().split("\n");

            for(int i=0;i<3;i++) {
                highScores[i] = Integer.parseInt(strings[i]);
            }


        }catch(Exception e){}

        textFont = new BitmapFont(Gdx.files.internal("myFont.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("title.fnt"));
    }

    public static void save(){
        try{
            FileHandle fileHandle = Gdx.files.external(filename);

            fileHandle.writeString(Integer.toString(highScores[0]),false);

            for(int i=1;i<3;i++) {
                fileHandle.writeString(Integer.toString(highScores[i]), true);
            }
        }catch(Exception ex){}
    }

    public static void reset(){
        try{
            FileHandle fileHandle = Gdx.files.external(filename);

            fileHandle.writeString(Integer.toString(0),false);

            for(int i=1;i<3;i++) {
                fileHandle.writeString(Integer.toString(0), true);
            }
        }catch(Exception ex){}

    }


}
