package spaceleap.engine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {

	//Local properties
	private int x;
	private int y;
	private Sprite me;
	private final int moveSpeed = 1;
	
	
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		this.me = new Sprite(new Texture("shot.png"));
	}
	
	/**
	 * @return position as an array form [x,y]
	 */
	public int[] getPosition(){
		int[] p = {x,y};
		return p;
	}
	
	public void move(){
		y += moveSpeed;
	}
	
}
