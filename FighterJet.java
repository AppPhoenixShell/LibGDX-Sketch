package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import app.phoenixshell.kelpie2d.draw.Structs.ComplexShape;

public class FighterJet implements ComplexShape
{
	public static final float afterWidthMax = 20;

	public Vector2 position = new Vector2(0,0);
	private Vector2 speed = new Vector2(0,3f);
	
	public float mainWidth;
	public float mainHeight;
	
	public float secondOff;
	public float secondWidth;
	public float secondHeight;
	public float gunXOff;
	public float gunYOff;
	public float gunWidth;
	public float gunHeight;
	Color cMain = Color.RED;
	Color cSec = Color.WHITE;
	
	boolean afterBurner = true;
	float afterLength = 40;
	float afterWidth = 5;



	public float zNoise;
	
	public FighterJet(SketchTool tool) {
		mainWidth = 10;
		mainHeight =50;
		secondOff = -5;
		secondHeight = 35;
		secondWidth = 20;
		
		gunXOff = 5; 
		
		zNoise = tool.random(0, 1000);
		
		
		/*
		mainWidth = 10;
		mainHeight = 20;
		secondOff = -5;
		secondHeight = 35;
		secondWidth = 20;
		*/
	}
	
	public void update() {
		position.add(speed);
	}
	
	
	@Override
	public void onDraw(SketchTool tool) {
		ShapeRenderer shape = tool.shaperRenderer();
		shape.rotate(0, 0, 1, speed.angle() + -90);
		
		//sshape.identity();
		tool.fill(cMain);
		tool.triat(0, 0, mainWidth, mainHeight);
		tool.fill(cSec);
		tool.triat(0, secondOff, secondWidth, secondHeight);
		
	
		
		
		if(afterBurner) {
			tool.fill(Color.ORANGE);
			//tool.translate(0, -10);
			shape.translate(0, -30 - afterLength / 2, 0);
			shape.rotate(0, 0, 1, 180);
			float value = tool.mod((float) (afterWidth * tool.sin(tool.elapsed() * 25)));
			
			tool.triat(0, 0, value, afterLength);
		}
		shape.identity();
		
		
		
		
		//dshape.identity();
	}

	public void rotate(float adjust) {
		speed.setAngle(adjust * 360);
		// TODO Auto-generated method stub
		
	}

}
