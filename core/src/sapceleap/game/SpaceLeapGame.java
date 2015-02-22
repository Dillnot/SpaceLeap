package sapceleap.game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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

	// Screens, so the data is kept to move between :)
	public MainMenuScreen mms;
	public OptionsScreen os;
	public GameScreen gs;

	@Override
	// Handles all the creation of objects, classes and other stuff in the game
	public void create() {
		batch = new SpriteBatch();
		font = new BitmapFont();

		mms = new MainMenuScreen(this);
		os = new OptionsScreen(this);
		gs = new GameScreen(this);

		loadOptions();

		this.setScreen(mms);
	}

	// Calls update and render one after another
	public void render() {
		super.render();
	}

	public void dispose() {

		saveOptions();
		batch.dispose();
		font.dispose();
	}

	// Save Input option to file
	private void saveOptions() {
		try {
			File config = new File("spaceleap.config");

			if (!config.exists()) {
				config.createNewFile();
			}

			BufferedWriter bw = new BufferedWriter(new FileWriter(config));

			bw.write(INPUT_MODE);
			bw.flush();

			bw.close();
		} catch (Exception e) {
			System.out.println("Error saving config");
		}
	}

	// Loads in input options to program
	public void loadOptions() {
		try {
			File config = new File("spaceleap.config");

			if (!config.exists()) {
				INPUT_MODE = "KEYBOARD";
			} else {
				BufferedReader br = new BufferedReader(new FileReader(config));
				INPUT_MODE = br.readLine();
				br.close();
			}
		} catch (Exception e) {
			System.out.println("Error loading config");
		}
	}
}
