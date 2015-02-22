package spaceleap.engine.screen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

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
		
		try
		{
			File config = new File("Scores.txt");
			
			
				BufferedReader br = new BufferedReader(new FileReader(config));
				String in = br.readLine();
				while(in != ""){
					Scores.add(Integer.valueOf(in));
					in = br.readLine();
				}
				br.close();
						
		}
		catch (Exception e)
		{ System.out.println("Error loading config");
		}
		
		if (score != -1) {
			Scores.add(score);
		}
		Arrays.sort(Scores.toArray());
		Collections.reverseOrder();
		
		try
		{
		File config = new File("Scores.txt");
		
		if (!config.exists())
		{
			config.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(config));
		for (Integer sco:Scores){
			bw.write(sco + "\n");
		}
		bw.flush();
		bw.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public GameOverScreen(SpaceLeapGame game) {
		this.game = game;
		this.score = -1;
		
		if (score != -1) {
			Scores.add(score);
		}
		Collections.sort(Scores);
		Collections.reverse(Scores);
		
		try
		{
			File config = new File("Scores.txt");
			
			
				BufferedReader br = new BufferedReader(new FileReader(config));
				String in = br.readLine();
				while(in != ""){
					Scores.add(Integer.valueOf(in));
					in = br.readLine();
				}
				br.close();
						
		}
		catch (Exception e)
		{ System.out.println("Error loading config");
		}
		
		if (score != -1) {
			Scores.add(score);
		}
		Collections.sort(Scores);
		Collections.reverse(Scores);
		
		try
		{
		File config = new File("Scores.txt");
		
		if (!config.exists())
		{
			config.createNewFile();
		}
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(config));
		for (Integer sco:Scores){
			bw.write(sco + "\n");
		}
		bw.flush();
		bw.close();
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
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
		if (Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)) {
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
