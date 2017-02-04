package com.raj.eggs;

import com.badlogic.gdx.Game;

public class EggGame extends Game {

	@Override
	public void create() {
		Settings.load();
		System.out.println("loaded");
		setScreen(new MainMenuScreen(this));
		//setScreen(new GameScreen());
		//showMenuScreen();
	}

	public void showMenuScreen(){
		setScreen(new MainMenuScreen(this));
	}


}
