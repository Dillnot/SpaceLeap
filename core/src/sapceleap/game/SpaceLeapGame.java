package sapceleap.game;


import spaceleap.engine.screen.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.Controller;


public class SpaceLeapGame extends Game {
	
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	//Handles all the creation of objects, classes and other stuff in the game
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));	
	}

	 //Calls update and render one after another
	public void render () {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();      
	}
	
}
