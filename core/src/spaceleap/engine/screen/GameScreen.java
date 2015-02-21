/**
 * 
 */
package spaceleap.engine.screen;

import sapceleap.game.SpaceLeapGame;
import spaceleap.engine.entity.Player;
import spaceleap.engine.entity.enemy.Alien;
import spaceleap.engine.entity.enemy.Alien.AlienType;
import spaceleap.engine.input.LeapListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
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
	private Player player;
	Alien[][] aliens;

	private Controller c = new Controller();
	private LeapListener l = new LeapListener();

	private short direction = 0;

	/**
	 * 
	 */
	public GameScreen(SpaceLeapGame game) {
		this.game = game;
		batch = new SpriteBatch();
		img = new Texture("Player.png");
		player = new Player(game.VIEWPORT_WIDTH / 2);

		aliens = new Alien[5][10];

		int posX = (game.VIEWPORT_WIDTH - 470) / 2;
		int posY = 400;

		// Create new Array of Aliens :)
		for (int x = 0; x < 5; ++x) {
			for (int y = 0; y < 10; ++y) {
				int realX = posX + 15;

				if (y % 2 == 0) {
					aliens[x][y] = new Alien(AlienType.ORANGE, realX, posY);
				} else {
					aliens[x][y] = new Alien(AlienType.PURPLE, realX, posY);
				}
				posX += 32 + 15;
			}
			posX = (game.VIEWPORT_WIDTH - 470) / 2;
			posY -= 35;
		}
		c.addListener(l);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
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

		for (int x = 0; x < 5; ++x) {
			for (int y = 0; y < 10; ++y) {
				aliens[x][y].draw(batch);
			}
		}

		batch.end();

	}

	private void update() {

		// Handles Input from Keyboard
		if (game.INPUT_MODE == "KEYBOARD") {

			// Check user input
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
					direction = -2;
				else
					direction = -1;
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				if (Gdx.input.isKeyPressed(Input.Keys.CONTROL_LEFT))
					direction = 2;
				else
					direction = 1;
			} else {
				direction = 0;
			}

			// Check for fire
			if (Gdx.input.isButtonPressed(Input.Keys.SPACE)) {
				player.fire();
			}
		}

		else {
			direction = l.direction;
			// Fire player bullet and update position
			if (l.fire) {
				player.fire();
			}
		}

		player.setX(direction);
	}
	
	private void checkCollision(){
		
		for (Alien[] x:aliens){
			for (Alien y :x){
				if(y.getPosition() == player.) 
			}
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose() {
		batch.dispose();
		img.dispose();
	}

}
