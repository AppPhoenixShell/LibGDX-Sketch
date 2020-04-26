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
	private float[] vector = fvector(0,0);
	private float speed = 4;
	
	private float[] pos1 = fvector(0, 0);
	private float[] pos2 = fvector(100, 100);
	
	private float radius = 50;
	
	
	@Override
	public void draw() {
		clear(Color.WHITE);
		fill(Color.BLACK);
		
		rect(vector, 16,16);
		line(pos1, pos2);
		
		circle(pos2, 20);
		
		
	}
	
	@Override
	public void update(float delta) {
		autoCamera(2, 1.1f);
		vector[0]+= speed;
		
		pos2[0] = (float) (sin(elapsed())  * radius);
		pos2[1] = (float) (cos(elapsed()) * radius);
		
	}
	
	@Override
	public void overlay() {
		stroke(Color.BLUE);
		text(0,0, "something");
		
		fill(Color.RED);
		circle(mouseX(), mouseY(), 5);
	}

	@Override
	public void setup() {
		
		
	}

}
