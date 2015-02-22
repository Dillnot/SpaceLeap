package spaceleap.engine.entity.enemy;

/**
 * The special fancy alien at the top of the screen!
 * @author Ewan
 *
 */
public class SpecialAlien extends Alien {

	private final static short maxY = 425;
	
	public SpecialAlien(AlienType type, int x, int y) {
		super(type, 650, maxY);
	}
	
	private boolean megoLeft =false;
	
	@Override
	public int getScore() { return super.score * 3; }

	
	@Override
	//Moves constantly at a slow speed!
	//If it gets to the boundry of the special location, it turns round and comes back! 
	public void moveX()
	{
		if(megoLeft){
			super.x -=2;
		}else{
			super.x += 2;
		}
		super.me.setPosition(super.x, super.y);
		
		if (x <= -350) { megoLeft = false; if (super.isDead()) { super.resetTex();} }
		else if (x >=990) { megoLeft = true; if (super.isDead()) { super.resetTex();} }
	}



}
