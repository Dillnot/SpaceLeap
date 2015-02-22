package spaceleap.engine.screen;



import java.util.ArrayList;
import java.util.Random;

import sapceleap.game.SpaceLeapGame;
import spaceleap.engine.entity.Bullet;
import spaceleap.engine.entity.Player;
import spaceleap.engine.entity.Explode;
import spaceleap.engine.entity.enemy.Alien;
import spaceleap.engine.entity.enemy.Alien.AlienType;
import spaceleap.engine.entity.enemy.SpecialAlien;
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
	SpecialAlien sa = new SpecialAlien(AlienType.SPECIAL,0,0); //Just given 0,0 as the location is set within constructor

	private Controller c = new Controller();
	private LeapListener l = new LeapListener();

	private short direction = 0;
	private short killcount = 0;

	private int count = 0;
	ArrayList<Bullet> alienBullets = new ArrayList<Bullet>();
	ArrayList<Bullet> old = new ArrayList<Bullet>();
	
	ArrayList<Explode> expolsions = new ArrayList<Explode>();
	ArrayList<Explode> expolsionsToRemove = new ArrayList<Explode>();
	
	Random rand = new Random();
	
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
		
		resetAliens();
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
		checkCollision();
		
		// Every 20 draws, we attempt to add some bullets
		if (count % 20 == 0) {
			alienShooting();
		}
	}

	private void draw() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (killcount == 50) {
			game.setScreen(new GameOverScreen(game, player.getScore()));
		}

		// Start draw and draw player
		batch.begin();
		player.draw(batch);

		game.font.draw(batch, "Score: " + player.getScore(), 0, 475);
		game.font.draw(batch, "Lives: " + player.getLives(), 500, 475);

		// Draw all the aliens
		for (int x = 0; x < 5; ++x) {
			for (int y = 0; y < 10; ++y) {
				aliens[x][y].draw(batch);
			}
		}

		// Add Bullet, if it exists
		if (player.getBullet() != null) {
			// Check if bullet is off the screen, if so we just remove it by
			// added 0 to the score, else draw it
			if (!player.getBullet().move()) {
				player.updateScore(0);
			} else {
				player.getBullet().draw(batch);
			}
		}

		
		//Updating and drawing the bullets all at once :/
		for(Bullet b : alienBullets){
			if(!b.move()) { old.add(b); }
			else { b.draw(batch); }
		}
		for(Bullet b: old) { alienBullets.remove(b); }
		
		//Explosion drawing
		for(Explode b : expolsions){
			if(b.remove == 10){
				 expolsionsToRemove.add(b);
			}
			b.draw(batch); 
		}
		for(Explode b: expolsionsToRemove) { expolsions.remove(b); }
		
		
		
		//Drawing the special alien
		sa.draw(batch);
		batch.end();

		count += 1;
	}

	private void update() {

		// Handles Input from Keyboard
		if (game.INPUT_MODE.compareTo("KEYBOARD") == 0) {

			// Check user input
			if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
				direction = -1;
			} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
				direction = 1;
			} else {
				direction = 0;
			}

			// Check for fire
			if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
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

		moveAliens();
		

	}

	// Checks if the player's bullet collides with any of the aliens
	// Also checks collision with enemy bullets
	private void checkCollision() {

		if (player.getBullet() != null) {
			for (Alien[] x : aliens) {
				for (Alien y : x) {
					if (!(y.isDead())) {
						int bx = player.getBullet().getPosition()[0];
						int by = player.getBullet().getPosition()[1];

						if ((bx >= y.getPosition()[0] && bx <= y.getPosition()[0] + 32)
								&& (by >= y.getPosition()[1] && by <= y
										.getPosition()[1] + 32)) {
							y.kill();
							expolsions.add(new Explode(bx,by));
							player.updateScore(y.getScore());
							killcount += 1;
							return;
						}
					}
				}
			}
			
			if ((player.getBullet().getPosition()[0] >= sa.getPosition()[0] && player.getBullet().getPosition()[0] <= sa.getPosition()[0] + 32)&& (player.getBullet().getPosition()[1] >= sa.getPosition()[1] && player.getBullet().getPosition()[1] <= sa.getPosition()[1] + 32))
			{
				sa.kill();
				player.updateScore(sa.getScore());
			}
		}
		
		
		for(Bullet b : alienBullets)
		{
			int bx = b.getPosition()[0];
			int by = b.getPosition()[1];
			
			if ((bx >= player.getPosition()[0] && bx <= player.getPosition()[0] + 32) && (by >= player.getPosition()[1] && by <= player.getPosition()[1] + 32)){
				expolsions.add(new Explode(bx,by));
				
				//Remove a players life and check if they are dead.
				if(!player.kill()) 
				{ 
					game.setScreen(new GameOverScreen(game, player.getScore()));
					
				}
				//Player is dead :(
				else {
					alienBullets.clear();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					player.reset();
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	// Generates bullets for aliens
	private void alienShooting(){

		if (alienBullets.size() <= 0)
		{ 
			ArrayList<Alien> enemies = new ArrayList<Alien>();
			
			for(int x = 4; x >= 0; --x)
			{
				for(int y = 0; y < 10; ++y)
				{
					if(!aliens[x][y].isDead())
					{
						enemies.add(aliens[x][y]);
					}
				}
			}	
			int max = rand.nextInt(3);
			for(int i = 0; i < max; ++i)
			{
				Alien a = enemies.get(rand.nextInt(enemies.size()));
				enemies.remove(a);
				alienBullets.add(new Bullet(a.getPosition()[0] + 16, a.getPosition()[1], true));
			}		
		}
	}

	// Moves Aliens around the screen
	private void moveAliens() {

		// Moves Aliens down a row
		if (aliens[0][9].getPosition()[0] >= 600
				|| aliens[0][0].getPosition()[0] <= 0) {
			aliens[0][0].switchGoLeft();
			for (int x = 0; x < 5; ++x) {
				for (int y = 0; y < 10; ++y) {
					aliens[x][y].moveY();
					aliens[x][y].moveX();
				}
			}
			
			if (aliens[4][9].getPosition()[1] <= 50)
			{
				if(!player.kill()) 
				{ 
					game.setScreen(new GameOverScreen(game, player.getScore()));
				}
				resetAliens();
			}
		}

		// Every 20 draws, we move the aliens a set amount
		if (count % 20 == 0) {
			for (int x = 0; x < 5; ++x) {
				for (int y = 0; y < 10; ++y) {
					aliens[x][y].moveX();
				}
			}
		}
		
		sa.moveX();

	}

	//Resets the position of Aliens in the game	
	private void resetAliens() {
		int posX = (game.VIEWPORT_WIDTH - 470) / 2;
		int posY = 400;

		// Create new Array of Aliens :)
		for (int x = 0; x < 5; ++x) {
			for (int y = 0; y < 10; ++y) {
				int realX = posX + 15;
				aliens[x][y].setX(realX);
				aliens[x][y].setY(posY);
				posX += 32 + 15;
			}
			posX = (game.VIEWPORT_WIDTH - 470) / 2;
			posY -= 35;
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
