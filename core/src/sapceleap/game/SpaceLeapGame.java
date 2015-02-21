package sapceleap.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer.Random;

public class SpaceLeapGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	Sprite player;
	
	java.util.Random rand = new java.util.Random();
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		player = new Sprite(img);
	}

	@Override //Calls update and render one after another
	public void render () {
		update();
		draw();
	}
	
	
	//This method will call all updates from within the game
	private void update()
	{
		int x = rand.nextInt(500);
		int y = rand.nextInt(500);
		
		player.setPosition(x, y);
	}
	
	//This method will draw everything into the screen
	private void draw()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		player.draw(batch);
		batch.end();
	}
	

}
