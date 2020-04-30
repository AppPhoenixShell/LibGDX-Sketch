package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;



/**  SketchApp is an extension of a LibGDX game. This is how you
 * run the Sketch in LibGDX. To use first implement SketchApp.
 * Run this class in the LibGDX desktop launcher
 * 
 * */

public abstract class SketchApp extends Game
{	
	Sketch sketch;
	//Kelpie2DToolkitCSID kelpieToolkit;
	//ToolkitUIHandler uiHandler;
	
	public SketchApp() {
		
		
	}
	
	public abstract Sketch createSketch();
	public abstract void onCreate(Sketch sketch);
	//public abstract ToolkitUIHandler createUIHandler();
	
	@Override
	public final void resize(int width, int height) {
		super.resize(width, height);
		sketch.resize(width, height);
	}
	
	
	@Override
	public final void dispose() {
		super.dispose();
		sketch.onDispose();
	}
	@Override
	public final void create() {
		sketch = createSketch();
		//uiHandler = createUIHandler();
		
		//kelpieToolkit = new Kelpie2DToolkitCSID(uiHandler);
		
		//sketch.onCreate(kelpieToolkit);
			
		
		onCreate(sketch);
		sketch.setup();
		//uiHandler.runUI(kelpieToolkit);
	}

	@Override
	public final void render() {
		sketch.render();
		
	}

}
