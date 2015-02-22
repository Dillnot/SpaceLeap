package spaceleap.engine.screen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import sapceleap.game.SpaceLeapGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameOverScreen implements Screen {

	final SpaceLeapGame game;
	private int score;
	private ArrayList<Integer> Scores = new ArrayList<Integer>();

	public GameOverScreen(SpaceLeapGame game, int score) {
		this.game = game;
		this.score = score;
		for (int x = 100; x < 1000; x += 100) {
			Scores.add(x);
		}
		if (score != -1) {
			Scores.add(score);
		}
		Arrays.sort(Scores.toArray());
		Collections.reverseOrder();

	}

	public GameOverScreen(SpaceLeapGame game) {
		this.game = game;
		this.score = -1;
		// try {
		// Scanner in = new Scanner(new FileReader("scores.txt"));
		// } catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		for (int x = 100; x < 1000; x += 100) {
			Scores.add(x);
		}
		if (score != -1) {
			Scores.add(score);
		}
		Collections.sort(Scores);
		Collections.reverse(Scores);

		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		if (score != -1) {
			game.font.draw(game.batch, "Game Over!", 300, 300);
			game.font.draw(game.batch, "Score:" + score, 300, 270);
		}

		StringBuilder sb;
		game.font.draw(game.batch, "top scores", 300, 250);
		for (int x = 0; x < 5; x++) {
			sb = new StringBuilder();
			sb.append(x + 1);
			sb.append(": ");
			sb.append(Scores.get(x));
			game.font.draw(game.batch, sb.toString(), 300, (240 - (x * 20)));

		}
		
		game.font.draw(game.batch, "main menu -->", 550, 350);
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
			game.setScreen(new MainMenuScreen(game));
		}

		game.batch.end();
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
