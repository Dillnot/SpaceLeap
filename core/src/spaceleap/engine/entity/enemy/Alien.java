package spaceleap.engine.entity.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * A basic Alien Entity in SpaceLeap
 * @author Ewan
 *
 */
public class Alien {

	/**
	 * Define the type of enemy we wish. opefully alternating orange/purples
	 * @author Ewan
	 *
	 */
	public enum AlienType {
		ORANGE,
		PURPLE,
	};
	
	//Private fixed textures for the aliens
	private static final Texture orangeTex = new Texture("alien1.png");
	private static final Texture purpleTex = new Texture("alien2.png");
	private static final Texture deadTex = new Texture("dead.png");
	
	//Local properties
	private Sprite me;
	private int x = 0;
	private int y = 0;
	private boolean isDead = false;
	private int score = 50;
	
	/**
	 * Create new enemy type
	 * @param type - either ORANGE or PURPLE
	 * @param x - location
	 * @param y - location
	 */
	public Alien(AlienType type, int x, int y)
	{
		if (type == AlienType.ORANGE) { this.me = new Sprite(orangeTex);}
		else if (type == AlienType.PURPLE){ this.me = new Sprite(purpleTex); }
		else { System.out.println("Error creating alien");}
		
		this.x = x;
		this.y = y;
		
		this.me.setPosition(x, y);
	}
	/**
	 * @return postion as an array form [x,y]
	 */
	public int[] getPosition(){
		int[] p = {x,y};
		return p;
	}
	
	/**
	 * Moves alien along row
	 */
	public void moveX() { x += 2; this.me.setPosition(x, y); }
	
	/**
	 * Moves alien down column
	 */
	public void moveY() { y += 5; this.me.setPosition(x, y); }
	
	/**
	 * Is Alien dead?
	 * @return True if dead
	 */
	public boolean isDead() { return isDead; }
	
	/**
	 * Kills the ailen off if it gets hit
	 */
	public void kill() { isDead = true; this.me = new Sprite(deadTex); }
	
	/**
	 * Returns the score of the alien
	 * @return
	 */
	public int getScore() { return score; }
	
	/**
	 * Draws Alien to screen
	 * @param b - batch from GameScreen
	 */
	public void draw(Batch b) { this.me.draw(b); }
}
