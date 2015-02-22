package spaceleap.engine.entity;

import spaceleap.engine.entity.Bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Player of SpaceLeap
 * 
 * @author Ewan
 */
public class Player {

	private final Texture tex = new Texture("player.png");
	private final Sprite player = new Sprite(tex);
	private int x = 0;
	private final int y = 35;
	private int score = 0;
	private short lives = 3;
	private Bullet bullet = null;
	private final int screenMiddle;

	/**
	 * Create new player object
	 * 
	 * @param screenMiddle
	 *            - center of screen for y position
	 */
	public Player(int screenMiddle) {
		x = screenMiddle;
		this.screenMiddle = screenMiddle;

		this.player.setPosition(x, y);
	}

	/**
	 * Update position of player on the X axis
	 * 
	 * @param x
	 */
	public void setX(int direction) {
		switch (direction) {
		case -1: {
			if (x - 3f >= 0)
				x -= 3f;
			break;
		}
		case 1: {
			if (x + 3f <= 640 - 32)
				x += 3.0f;
			break;
		}
		}

		player.setX(x);
	}

	/**
	 * Reset player to center of screen
	 */
	public void reset() {
		this.x = screenMiddle;
	}

	/**
	 * Removes a life from the player.
	 * 
	 * @return false, if there are no more lives
	 */
	public boolean kill() {
		lives -= 1;
		if (lives < 0) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Fires a bullet from the player, if that's possible
	 */
	public void fire() {
		if (bullet == null) {
			bullet = new Bullet(x + 16, y);
		}
	}

	/**
	 * Update score and reset the bullet from inside the class
	 * 
	 * @param award
	 *            - increase score. Pass award = 0 if bullet misses or hits a
	 *            wall
	 */
	public void updateScore(int award) {
		score += award;
		bullet = null;
	}

	/**
	 * Returns the score of the player
	 * 
	 * @return
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Draws the player
	 */
	public void draw(Batch b) {
		this.player.draw(b);
	}

	/**
	 * Returns the players bullet object
	 * 
	 * @return
	 */
	public Bullet getBullet() {
		return this.bullet;
	}
	
	/**
	 * Returns the number of lives for the player
	 * @return
	 */
	public int getLives() { return this.lives; }
	
	/**
	 * Returns player position
	 * @return
	 */
	public int[] getPosition()
	{
		int[] pos = {x,y};
		return pos;
	}

}
