package sapceleap.game;

import spaceleap.engine.screen.GameScreen;
import spaceleap.engine.screen.MainMenuScreen;
import spaceleap.engine.screen.OptionsScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceLeapGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	public final int VIEWPORT_WIDTH = 640;
	public final int VIEWPORT_HEIGHT = 480;
	
	public String INPUT_MODE = "KEYBOARD";
	
	//Screens, so the data is kept to move between  :)
	public MainMenuScreen mms;
	public OptionsScreen os;
	public  GameScreen gs;
	

	@Override
	// Handles all the creation of objects, classes and other stuff in the game
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		
		mms = new MainMenuScreen(this);
		os = new OptionsScreen(this);
		gs = new GameScreen(this);
		
		this.setScreen(mms);
	}

	// Calls update and render one after another
	public void render() {
		super.render();
	}

	public void dispose() {
		batch.dispose();
		font.dispose();
	}

}
