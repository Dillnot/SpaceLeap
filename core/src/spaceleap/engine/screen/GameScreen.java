/**
 * 
 */
package spaceleap.engine.screen;

import sapceleap.game.SpaceLeapGame;
import spaceleap.engine.input.LeapListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.leapmotion.leap.Controller;

/**
 * @author dylan
 *
 */
public class GameScreen implements Screen {

	final SpaceLeapGame game;
	private SpriteBatch batch;
	private Texture img;
	private Sprite player;
	
	private Controller c = new Controller();
	private LeapListener l = new LeapListener();

	/**
	 * 
	 */
	public GameScreen(SpaceLeapGame game) {
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		player = new Sprite(img);
		
		player.setPosition(250, 250);		
		c.addListener(l);
	}

	
	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta) {
		update();
		draw();

	}

	private void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.end();
		
	}


	private void update() {
		 if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
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
	        }
	        
	     player.setX(l.position.getX());
	}


	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

}
