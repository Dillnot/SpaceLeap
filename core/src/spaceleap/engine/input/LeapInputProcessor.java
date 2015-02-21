package spaceleap.engine.input;

import javafx.geometry.Point2D;
import javafx.scene.input.InputMethodRequests;

import com.badlogic.gdx.InputProcessor;

public class LeapInputProcessor implements InputProcessor, InputMethodRequests {

	@Override
	public Point2D getTextLocation(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getLocationOffset(int x, int y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void cancelLatestCommittedText() {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSelectedText() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
