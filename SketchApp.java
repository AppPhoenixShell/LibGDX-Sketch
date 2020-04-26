package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.Game;

/**  SketchApp is an extension of a LibGDX game. This is how you
 * run the Sketch in LibGDX. To use first implement SketchApp.
 * Run this class in the LibGDX desktop launcher
 * 
 * */

public abstract class SketchApp extends Game
{
	Sketch sketch;
	
	public abstract Sketch createSketch();
	public abstract void onCreate(Sketch sketch);
	
	@Override
	public final void resize(int width, int height) {
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
		
		onCreate(sketch);
		sketch.setup();
	}

	@Override
	public final void render() {
		sketch.render();
		
	}

}
