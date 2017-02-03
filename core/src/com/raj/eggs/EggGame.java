package com.raj.eggs;

import com.badlogic.gdx.Game;

public class EggGame extends Game {

	@Override
	public void create() {
		setScreen(new MainMenuScreen(this));
		//setScreen(new GameScreen());
		//showMenuScreen();
	}

	public void showMenuScreen(){
		setScreen(new MainMenuScreen(this));
	}


}
