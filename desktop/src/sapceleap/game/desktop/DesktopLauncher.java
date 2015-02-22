package sapceleap.game.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import sapceleap.game.SpaceLeapGame;

public class DesktopLauncher {
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 640;
		config.height = 480;
		config.title = "SpaceHeap";
		config.addIcon("logo.png", FileType.Internal);
		new LwjglApplication(new SpaceLeapGame(), config);
	}
}
