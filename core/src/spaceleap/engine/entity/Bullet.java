package spaceleap.engine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {

	// Local properties
	private int x;
	private int y;
	private final Sprite me = new Sprite(new Texture("shot.png"));
	private final int moveSpeed = 3;
	private boolean alien;
	
	public Bullet(int x, int y) {
		this.x = x;
		this.y = y;
		this.alien = false;
		this.me.setPosition(x,y);
	}
	public Bullet(int x, int y ,boolean al){
		this.x = x;
		this.y = y;
		this.alien = al;
		this.me.setPosition(x,y);
	}

	/**
	 * @return position as an array form [x,y]
	 */
	public int[] getPosition() {
		int[] p = { this.x, this.y };
		return p;
	}

	public boolean move() {
		if (this.alien){
			this.y -= moveSpeed;
		} else{
			this.y += moveSpeed;
		}

		if (this.y > 480 || this.y < 0){
			return false;
		}else {
			this.me.setY(y);
			return true;
  		}
	}

	// Draws Bullet to screen
	public void draw(Batch b) {
		this.me.draw(b);
	}
}
