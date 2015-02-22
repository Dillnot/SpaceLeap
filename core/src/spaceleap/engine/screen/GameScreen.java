package spaceleap.engine.screen;


import sapceleap.game.SpaceLeapGame;
import spaceleap.engine.entity.Player;
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
	private ArrayList<Bullet> aliensBullets  = new ArrayList<Bullet>();

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
		alienShooting();
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
				
			} else {
				player.getBullet().draw(batch);
			}
		}

		ArrayList<Bullet> remove = new ArrayList<Bullet>();
		for (Bullet b : aliensBullets) {
			if(!b.move()){
				remove.add(b);
		
			}else{
				b.draw(batch);
			}
		}
		for (Bullet b : remove) {
			aliensBullets.remove(b);
		}

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
							player.updateScore(y.getScore());
							killcount += 1;
							return;
						}
					}
				}
			}
		}
		
		
		//for(Bullet b : aliensBullets)
		//{
		//	int bx = b.getPosition()[0];
		//	int by = b.getPosition()[1];
		//	
		//	if ((bx >= player.getPosition()[0] && bx <= player.getPosition()[0] + 32) && (by >= player.getPosition()[1] && by <= player.getPosition()[1] + 32))
		//	{
		//		//Remove a players life and check if they are dead.
		//		if(!player.kill()) 
		//		{
		//			//aliensBullets.clear();
		//			break;
		//		}
		//		//Player is dead :(
		//		else { game.setScreen(new GameOverScreen(game, player.getScore()));
		//		}
		//	}
		//}
	}

	// Generates bullets for aliens
	private void alienShooting(){
		// Calculate possible shot location
		ArrayList<int[]> possShotLocations = new ArrayList<int[]>();
		for (int x = 4; x >= 0; x--) {
			for (int y = 0; y < 10; y++) {
				if (!(aliens[x][y].isDead())) {
					possShotLocations.add(aliens[x][y].getPosition());
					}
				}
			}
		if(aliensBullets.isEmpty())	{	
			
			Bullet b = new Bullet(possShotLocations.get(0)[0] , possShotLocations.get(0)[1], true);
			aliensBullets.add(b);
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
		//if (count % 20 == 0) {
			//System.out.println(count);
			for (int x = 0; x < 5; ++x) {
				for (int y = 0; y < 10; ++y) {
					aliens[x][y].moveX();
				}
			}
		//}
		
		sa.moveX();

	}

	//Resets the position of Aliens in the game
	//////////////////////////////////////////////////
		
	//TODO : MAKE THIS JUST CHANGE THE POSITION OF ALIENS, NOT CREATE A NEW LOT
	
	///////////////////////////////////////////////////////
	
	
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
