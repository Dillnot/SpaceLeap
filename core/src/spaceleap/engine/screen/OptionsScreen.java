package spaceleap.engine.screen;

import sapceleap.game.SpaceLeapGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class OptionsScreen implements Screen {

	final SpaceLeapGame game;
	
	
	public OptionsScreen(SpaceLeapGame game) 
	{
		this.game = game;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.batch.begin();
		game.font.draw(game.batch, "Input Mode: " + game.INPUT_MODE, 240, 300);
		game.font.draw(game.batch, "Press <space> to change", 240, 270);
		game.font.draw(game.batch, "<- MainMenu", 0, 350);
		game.batch.end();
		
		//Update the screen when space is pressed
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
		{
			if (game.INPUT_MODE == "KEYBOARD") { game.INPUT_MODE = "LEAP"; }
			else { game.INPUT_MODE = "KEYBOARD"; } 
		}
		//Exit back to the MainMenu
		else if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
		{
			game.setScreen(game.mms);
		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
