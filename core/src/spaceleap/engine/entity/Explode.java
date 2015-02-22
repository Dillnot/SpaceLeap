package spaceleap.engine.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Explode {
	
	private int x;
	private int y;
	public int remove = 0;
	//private final Sprite me = new Sprite(new Texture("explode.png"));
	private Texture sheet;
	private TextureRegion[] frames;
	private Animation Animation;
	private float stateTime;
	private TextureRegion currentFrame;
	

	public Explode(int x, int y) {
		this.x=x;
		this.y=y;
		//load the animation sheet
		sheet = new Texture("explosions.png"); // #9
		//set num of columns in sheet
        int FRAME_COLS = 16;
        //set num of rows in sheet
		int FRAME_ROWS = 8;
		//spit sheet in to frames
		TextureRegion[][] tmp = TextureRegion.split(sheet, sheet.getWidth()/FRAME_COLS, sheet.getHeight()/FRAME_ROWS);       
		// make a new texture region the size of the sheet
        frames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        
        //populate the texture region
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                frames[index++] = tmp[i][j];
            }
        }
        //create the animation
    Animation = new Animation(0.025f, frames);
    stateTime = 0f;
	}
	
	public void draw(Batch b){
		stateTime += Gdx.graphics.getDeltaTime();           
        currentFrame = Animation.getKeyFrame(stateTime, true);
        b.draw(currentFrame, x, y);
        remove++;
	}

}
