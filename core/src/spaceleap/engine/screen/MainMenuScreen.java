package spaceleap.engine.screen;

import sapceleap.game.SpaceLeapGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class MainMenuScreen implements Screen {

	final SpaceLeapGame game;

	public MainMenuScreen(SpaceLeapGame game) {
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

		//Rendering Main Options
		game.batch.begin();
		game.font.draw(game.batch, "Welcome to spaceleap!!! ", 300, 400);
		game.font.draw(game.batch, "press space begin!", 300, 380);
		
		game.font.draw(game.batch, "Options ->", 550, 350);
		game.font.draw(game.batch, " <- scores", 550, 340);
		game.batch.end();

		//Checking for starting a game or to move options
		if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
			game.setScreen(game.gs);
			game.os.dispose();
			dispose();
		}
		else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
		{
			game.setScreen(game.os);
		}else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
			game.setScreen(new GameOverScreen(game));
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
