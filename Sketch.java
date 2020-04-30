package app.phoenixshell.kelpie2d.draw;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import app.phoenixshell.kelpie2d.draw.Structs.ComplexShape;
import app.phoenixshell.kelpie2d.draw.Structs.FloatMatrix;
import app.phoenixshell.kelpie2d.draw.Structs.IntMatrix;


/**
 *  The main implementation of the SketchTool. Contains all the drawing & math functions
 *  that can be used in a sketch.
 * */

public abstract class Sketch implements SketchTool
{
	//private Kelpie2DToolkit kelpieToolkit;
	
	private int VHeight = 800;
	private int VWIDTH = 1080;
	
	private ShapeRenderer shape;
	private final Color color;
	private final Color clear;
	
	private OrthographicCamera camera;
	private OrthographicCamera screenCam;

	
	private Viewport port;
	private Viewport screenPort;
	
	private SpriteBatch spriteBatch;
	private UIBatch uiBatch;
	
	private float totalTime = 0;
	
	private Random random; 
	
	private Vector3 mouse;
	private Vector3 mouseWorld = new Vector3();
	

	private NoiseAlgorithm noiseAlgo;
	private boolean mouseMoved;
	private BitmapFont font;
	
	
	
	/*sample minimap*/
	Camera miniCam;
	Viewport miniView;
	
	/*
	public Kelpie2DToolkit getToolkit() {
		return kelpieToolkit;
	}
	*/
	
	
	@Override
	public boolean click() {
		// TODO Auto-generated method stub
		return Gdx.input.isButtonPressed(Input.Buttons.LEFT);
	}


	private BaseInputProcessor input = new BaseInputProcessor() {

		@Override
		public boolean mouseMoved(int screenX, int screenY) {
			mouse.x = screenX;
			mouse.y = screenY;
			mouse.z = 0;
			
			mouseWorld.x = mouse.x;
			mouseWorld.y = mouse.y;
			
			//System.out.printf("mx: %f,%f\n", mouse.x, mouse.y);
		
			//worldMosue.z = 0;
			mouseMoved = true;

			screenCam.unproject(mouse, screenPort.getScreenX(), 
					screenPort.getScreenY(), screenPort.getScreenWidth(), screenPort.getScreenHeight());
			
			camera.unproject(mouseWorld, screenPort.getScreenX(), 
					screenPort.getScreenY(), screenPort.getScreenWidth(), screenPort.getScreenHeight());
			
			//dotX=(touchX*480)/width;
			//dotY=320-(touchY*320/height);
			
			return true;
		}
	};
	
	@Override
	public void drawCursor(Color color, float size) {
		fill(color);
		circle(mouse.x, mouse.y, size);
	}
	
	



	@Override
	public Vector3 mouseWorld() {
		
		return mouseWorld;
	}


	/*
	public void onCreate(Kelpie2DToolkit kelpieToolkit) {
		this.kelpieToolkit = kelpieToolkit;
	}
	*/
	
	public Vector3 mouse() {

		return mouse;
	}
	
	
	
	@Override
	public ShapeRenderer shaperRenderer() {
		return shape;
	}

	@Override
	public float elapsed() {
		return totalTime;
	}

	public boolean mouseMoved() {
		return mouseMoved;
	}
	
	@Override
	public float mouseX() {
		return mouse.x;
	}
	@Override
	public float mouseY() {
		return mouse.y;
	}

	public Sketch() {
		this.font = new BitmapFont();
	
		this.spriteBatch = new SpriteBatch();
		
		this.shape = new ShapeRenderer();
		this.color = new Color();
		this.clear = new Color(Color.WHITE);
		this.random = new Random();
		this.mouse = new Vector3();
		this.noiseAlgo = new SimplexNoise((int)System.currentTimeMillis());
		
		
		this.shape.setAutoShapeType(true);
		
		
				;// Gdx.graphics.getHeight();
		
		this.camera = new OrthographicCamera(VWIDTH, VHeight);
		screenCam = new OrthographicCamera(VWIDTH, VHeight);
		
		port = new FitViewport(VWIDTH, VHeight,camera);
		screenPort = new FitViewport(VWIDTH, VHeight);
		//screenPort.apply(false);
		
		
		
		this.uiBatch = new UIBatch(spriteBatch, screenPort);
		//stage.setViewport(screenViewport);
		

		
		
		
		Gdx.input.setInputProcessor(input);
		
		/*miniMapVoiew*/
		miniCam = new OrthographicCamera(300, 300);
		miniView = new FitViewport(300, 300);
	}
	
	@Override public void overlay(UIBatch batch) {
		
	}
	@Override public abstract void draw();
	@Override public abstract void setup();
	@Override public abstract void update(float delta);
	
	@Override public float width() 	{return Gdx.graphics.getWidth();}
	@Override public float height() 	{return Gdx.graphics.getHeight();}
	
	@Override public final void clear(Color color) 		{clear.set(color);}
	@Override public final void translate(float x, float y) 	{camera.translate(x, y, 0);}
	@Override public final void camera(float x, float y) 	{camera.position.set(x, y, 0);}
	@Override public final void zoomIn(float zoom) {camera.zoom /= zoom;}
	@Override public final void zoomOut(float zoom) {camera.zoom *= zoom;}
	@Override public final void zoom(float zoom) {camera.zoom = zoom;}
	@Override public final float cameraY() {return camera.position.x;}
	@Override public final float cameraX() {return camera.position.y;}
	
	@Override public float rfloat() {return random.nextFloat();}
	@Override public float rfloat(float bound) {return rfloat() * bound;}
	@Override public int rint(int bound) {return random.nextInt(bound);}
	@Override public float noise(float x, float y) {return (float) noiseAlgo.noise(x, y);}
	
	@Override public float noise(float x, float y, float z) {return (float) noiseAlgo.noise(x, y,z);}
	@Override public float mod(float value) {return Math.abs(value);}
	@Override public double mod(double value) {return Math.abs(value);}
	@Override public double sin(float x) {return Math.sin(x);}
	@Override public double cos(float x) {return Math.cos(x);}
	@Override public double min(float v1, float v2) {return Math.min(v1, v2);}
	@Override public double max(float v1, float v2) {return Math.max(v1, v2);}

	@Override public float clampf(float value, float min, float max)
	{return (float) clampd(value, min, max);}

	@Override
	public double clampd(double value, double min, double max) {
		if(value < min)
			return min;
		if(value > max)
			return max;
		return value;
	}

	@Override
	public Rectangle bounds(float x, float y, float width, float height) {
		return new Rectangle(x, y, width, height);
		
	}
	@Override public Vector2 vector2(float x, float y) {return new Vector2(x,y);}
	
	
	
	@Override
	public float[] fvector(float x, float y) {
		float[] vector = new float[2];
		vector[0] =x;
		vector[1] = y;
		
		return vector;
	}

	@Override
	public float[] clamp(float[] coord, float xMin, float xMax, float yMin, float yMax) {
		if(coord.length == 2) {
			coord[0] = clampf(coord[0], xMin, xMax);
			coord[1] = clampf(coord[1], yMin, yMax);
		}
		return coord;
	}

	@Override
	public float[] clamp(float[] coord, Rectangle bounds) {
		return clamp(coord, bounds.x, bounds.x+ bounds.width, bounds.y, bounds.y + bounds.height);
	}

	public void render() {
		mouseMoved = false;
		Gdx.gl.glClearColor(clear.r, clear.b, clear.g, clear.a);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		//spriteBatch.setProjectionMatrix(projection);
		float deltaTime =Gdx.graphics.getDeltaTime();
		totalTime += deltaTime;
		
		
		/*game world viewport/camera updates*/
		camera.update();
		shape.setProjectionMatrix(camera.combined);
		spriteBatch.setProjectionMatrix(camera.combined);
		
		update(deltaTime);
		shape.begin();
		spriteBatch.begin();
		draw();
		shape.end();
		spriteBatch.end();
		
	
		/*screen updates*/
		screenCam.update();
		
		shape.setProjectionMatrix(screenCam.combined);
		spriteBatch.setProjectionMatrix(screenCam.combined);
		spriteBatch.begin();
		shape.begin();
		
		overlay(uiBatch);
		spriteBatch.end();
		shape.end();
		
		/*mini updates*/
		miniCam.update();
		spriteBatch.begin();
		shape.begin();
		shape.setProjectionMatrix(miniCam.combined);
		draw();
		shape.end();
		spriteBatch.end();
		
		
	}


	@Override
	public void draw(ComplexShape cshape, float x, float y, float rotation) {
		shape.identity();
		shape.translate(x, y, 0);
		shape.rotate(0, 0, 1, rotation);
		cshape.onDraw(this);
		shape.identity();
		
	}

	@Override
	public Texture texture(String path) {
		return new Texture(Gdx.files.internal(path));
	}
	@Override
	public final void fill(Color color) {
		this.color.set(color);
		shape.setColor(this.color);
		shape.set(ShapeType.Filled);
		spriteBatch.setColor(this.color);
	}
	@Override
	public final void fill(float r, float g, float b, float a) {
		this.color.set(r,g,b,a);
		fill(this.color);
	}
	@Override
	public final void fillRandom() {
		fill(rfloat(), rfloat(), rfloat(), rfloat());
	}
	@Override
	public final void stroke(Color c) {
		this.color.set(c);
		shape.setColor(c);
		shape.set(ShapeType.Line);
	}
	@Override
	public final void circle(float x, float y, float r) {
		shape.circle(x, y, r);}
	@Override
	public final void circle(Vector2 pos, float r) {circle(pos.x, pos.y, r);}
	
	
	
	@Override
	public void circle(float[] fvector, float r) {
		circle(fvector[0], fvector[1], r);
	}

	@Override
	public void rect(float[] fvector, float w, float height) {
		rect(fvector[0], fvector[1], w, height);
	}

	@Override
	public void line(float[] v1, float[] v2) {
		line(v1[0], v1[1], v2[0], v2[1]);
	}
	
	

	@Override
	public final void rect(float x, float y, float w, float h) {
		shape.rect(x, y, w,h);
	}
	@Override
	public final void rect(Rectangle r) {
		rect(r.x, r.y, r.width, r.height);
	}
	@Override
	public final void line(Vector2 p1, Vector2 p2) {
		shape.line(p1, p2);
	}
	
	
	@Override
	public final void line(float x1, float y1, float x2, float y2) {
		shape.line(x1, y1, x2, y2);
	}
	
	@Override
	public void tri(float x1, float y1, float x2, float y2, float x3, float y3) {
		shape.triangle(x1, y1, x2, y2, x3, y3);
	}

	@Override
	public void triat(float x, float y, float width, float height) {
		triat(x,y, width, height, 0);	
	}

	@Override
	public void triat(float x, float y, float width, float height, float rotation) {
		//shape.identity();
		//shape.identity();
		shape.translate(x, y, 0);
		
		shape.rotate(0, 0, 1, rotation);
		tri(- width /2,  - height /2, 0,  + height/2,  + width /2,   - height /2);
		//shape.identity();
		
		
	}

	@Override
	public double distance(float x1, float y1, float x2, float y2) {
		return Vector2.dst(x1, y1, x2, y2);
	}
	@Override
	public double distance(Vector2 p1, Vector2 p2) {
		return p1.dst(p2);
	}

	@Override
	public void resize(int width, int height) {
	
		port.update(width, height, true);
		screenPort.update(width, height, false);
	}
	@Override
	public void seedRandom(long seed) {
		random.setSeed(seed);}
	public void seedNoise(long seed) {
		noiseAlgo.setSeed(seed);}
	@Override
	public float random(float min, float max) {
		return min + random.nextFloat() * (max - min);
	}
	@Override
	public boolean key(int keycode) {
		return Gdx.input.isKeyPressed(keycode);
	}
	public boolean keyJust(int keycode) {
		return Gdx.input.isKeyJustPressed(keycode);
	}
	@Override
	public TextureRegion region(Texture texure, int x, int y, int w, int h) {
		return new TextureRegion(texure, x,y,w,h);
	}
	@Override
	public void tint(Color color) {
		spriteBatch.setColor(color);
	}
	@Override
	public void draw(TextureRegion texture, float x, float y) {
		spriteBatch.draw(texture, x, y);
	}
	@Override
	public void text(float x, float y, String msg) {
		font.draw(spriteBatch, msg, x, y);
	}
	
	
	
	public void onDispose() {
		
	}
	@Override
	public void autoCamera(float camFactor, float zoomFactor) {
		if(key(Keys.O))
			zoomIn(zoomFactor);
		if(key(Keys.L))
			zoomOut(zoomFactor);
		
		float camSpeed = camFactor * camera.zoom;
		
		
		if(key(Keys.W))
			translate(0, camSpeed);
		if(key(Keys.S))
			translate(0, -camSpeed);
		if(key(Keys.A))
			translate(-camSpeed, 0);
		if(key(Keys.D))
			translate(camSpeed, 0);
		
		
		camera.update();
			
	}

	@Override
	public IntMatrix imatrix(int width, int height) {
		return new IntMatrix(width, height);
	}

	@Override
	public FloatMatrix fmatrix(int width, int height) {
		return new FloatMatrix(width, height);
	}
	
	
	
	
	
	/*generic typing system*/
	
	
	
	
	


	
	

}
