package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;

import app.phoenixshell.kelpie2d.draw.Structs.ComplexShape;
import app.phoenixshell.kelpie2d.draw.Structs.IntMatrix;

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
	
	private IntMatrix levels = imatrix(10,10);
	
	private float radius = 30;
	
	private float h = 50;
	private float w = 10;
	
	private float rotate = 0;
	

	private float x = 100;
	private float y = 100;
	
	//FighterJet plane = new FighterJet();
	
	private float xFreg = 0.001f;
	private float yFreq = 0.001f;
	private float zFreq = 0.01f;
	
	private int numJets = 100;
	private FighterJet[] jets = new FighterJet[numJets];
	
	int currentFrame = 0;
	
	@Override
	public void draw() {
		++currentFrame;
		
		clear(Color.BLACK);
		fill(Color.WHITE);
		
		rect(vector, 16,16);
		//line(pos1, pos2);
		
		circle(pos2, 2);
		
		//triat(0, 0, w,h, rotate);
		
		if(key(Keys.R)) {
			
		}
		for(int i=0; i < jets.length; i++) {
			FighterJet plane = jets[i];
			
			if(currentFrame == 10) {
				float noise = noise(plane.position.x * xFreg, plane.position.y* yFreq
					, plane.zNoise * zFreq);
				plane.rotate(noise);
				
			}else {
				
			}
			
			
			
			
			
			draw(plane, plane.position.x, plane.position.y, rotate);
		}
		if(currentFrame == 10)
				currentFrame = 0;
		
		
	}
	
	@Override
	public void update(float delta) {
		for(FighterJet plane : jets) {
			//plane.zNoise += random(0f, .003f);
			plane.update();
		}
		
		if(key(Keys.T)) {
			
			//plane.afterWidth = clampf(plane.afterLength *= 1.01, 0, FighterJet.afterWidthMax);
			//plane.afterWidth *= 1.01;
		}
		
		autoCamera(1.5f, 1.2f);
		vector[0]+= speed;
		
		float faster = 5;
		
		pos2[0] = (float) (sin(elapsed() * faster)  * radius);
		pos2[1] = (float) (cos(elapsed() * faster) * radius);
		
		for(int x=0; x < levels.width; x ++) {
			for(int y=0; y < levels.height; y++) {
				int l = levels.matrix[x][y];
			}
		}
		
	}
	
	@Override
	public void overlay(UIBatch batch) {
		Vector3 origin = batch.screen(5,5);
		stroke(Color.BLUE);
		//text(-100,0, "something");
		
		fill(Color.RED);
	
		circle(origin.x,  origin.y, 40);
		drawCursor(Color.RED, 4);
	}

	@Override
	public void setup() {
		System.out.println("running sketch");
		Label label = new Label("hello stage", new LabelStyle(new BitmapFont(), Color.WHITE));
		//stage.addActor(label);
		
		
		
		for(int i=0; i < jets.length; i++) {
			jets[i] = new FighterJet(this);
			jets[i].position.x += random(10, 1000);
			jets[i].position.y += random(10,1000);
		}

	}


}
