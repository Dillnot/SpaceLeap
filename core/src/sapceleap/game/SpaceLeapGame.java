package sapceleap.game;

import spaceleap.engine.screen.MainMenuScreen;
import spaceleap.engine.input.LeapListener;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.Controller;


public class SpaceLeapGame extends Game {
	
	Controller c = new Controller();
	LeapListener l = new LeapListener();
	public SpriteBatch batch;
	public BitmapFont font;

	@Override
	//Handles all the creation of objects, classes and other stuff in the game
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		
        // Have the sample listener receive events from the controller
        c.addListener(l);
	}

	 //Calls update and render one after another
	public void render () {
		super.render();
	}
	
	public void dispose() {
		batch.dispose();
		font.dispose();
        /*if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
        }*/
		
		player.setX(l.position.getX());
       
	}
	
	
}
