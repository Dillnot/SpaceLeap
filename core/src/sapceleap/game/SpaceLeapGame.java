package sapceleap.game;

import spaceleap.engine.input.LeapListener;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.Controller;


public class SpaceLeapGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite player;
	
	Controller c = new Controller();
	LeapListener l = new LeapListener();
	
	@Override
	//Handles all the creation of objects, classes and other stuff in the game
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		player = new Sprite(img);
		
		player.setPosition(250, 250);
		
        // Have the sample listener receive events from the controller
        c.addListener(l);
	}

	@Override //Calls update and render one after another
	public void render () {
		update();
		draw();
	}
	
	
	//This method will call all updates from within the game
	private void update()
	{
        /*if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                player.translateX(-1f);
            else
                player.translateX(-10.0f);
        }
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            if(Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
                player.translateX(1f);
            else
                player.translateX(10.0f);
        }*/
		
		player.setX(l.position.getX());
       
	}
	
	//This method will draw everything into the screen
	private void draw()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.end();
	}
}
