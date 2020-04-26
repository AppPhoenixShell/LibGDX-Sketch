package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * *
 * @author PhoenixShell
 * Example of how to implement a Sketch
 * This example draws a sqaure moving at 
 *
 */

public class ExampleSketch extends Sketch
{
	private float x = 0;
	private float y = 0;
	private float speed = 4;
	
	@Override
	public void draw() {
		clear(Color.WHITE);
		fill(Color.BLACK);
		
		rect(x, y,16,16);
	}
	
	@Override
	public void update(float delta) {
		x += speed;
	}
	@Override
	public void setup() {
		
		
	}

}
