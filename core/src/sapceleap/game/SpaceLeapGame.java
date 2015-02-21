package sapceleap.game;

import spaceleap.engine.screen.MainMenuScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpaceLeapGame extends Game {

	public SpriteBatch batch;
	public BitmapFont font;

	public final int VIEWPORT_WIDTH = 640;
	public final int VIEWPORT_HEIGHT = 480;

	public final String controlMode = "LEAP";

	@Override
	// Handles all the creation of objects, classes and other stuff in the game
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
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
