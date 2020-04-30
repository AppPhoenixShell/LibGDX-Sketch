package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

public class UIBatch {
	
	
	private Viewport screenViewport;
	
	private SpriteBatch batch;
	
	public UIBatch(SpriteBatch batch,
			Viewport screenViewport) {
		
		this.batch = batch;
		this.screenViewport = screenViewport;
	}
	
	public void draw(TextureRegion tile, int screenX, int screenY) {
		Vector3 onScreen = new Vector3(screenX, screenY, 0);
		screenViewport.getCamera().project(onScreen);
		batch.draw(tile, onScreen.x, onScreen.y);
	}
	
	public Vector3 screen(int x, int y) {
		Vector3 onScreen = new Vector3(x, y, 0);
		screenViewport.getCamera().unproject(onScreen,
				screenViewport.getScreenX(), screenViewport.getScreenY(),
				screenViewport.getScreenWidth(), screenViewport.getScreenHeight());
		
		return onScreen;
	}
	
}
