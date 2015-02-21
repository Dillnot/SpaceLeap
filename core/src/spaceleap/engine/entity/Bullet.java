package spaceleap.engine.entity;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Bullet {

	//Local properties
	private int x;
	private int y;
	private static final Sprite me = new Sprite(new Texture("shot.png"));
	private final int moveSpeed = 3;
	
	
	public Bullet(int x, int y){
		this.x = x;
		this.y = y;
		me.setPosition(x,y);
	}
	
	/**
	 * @return position as an array form [x,y]
	 */
	public int[] getPosition(){
		int[] p = {x,y};
		return p;
	}
	
	public boolean move(){
		y += moveSpeed;
		
		if (y > 480) return false;
		else {me.setY(y); return true; }
	}
	
	//Draws Bullet to screen
	public void draw(Batch b) { me.draw(b); }
}
