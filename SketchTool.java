package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;


/** The interface that defines all sketching operations
 * 
 * */

public interface SketchTool{

	/*io calls*/
	
	public float mouseX();
	public float mouseY();
	
	
	/*callbacks*/
	public void draw();
	public void setup();
	public void overlay();
	public void update(float delta);
	
	public float width();
	public float height();
	
	public void clear(Color color);
	
	
	/*camera funcs*/
	public void translate(float x, float y);
	public void camera(float x, float y);
	public void zoomIn(float zoomFactor);
	public void zoomOut(float zoomFactor);
	public void zoom(float zoom);
	public float cameraY() ;
	public float cameraX();
	
	/*random & math functions*/
	public float rfloat();
	public float rfloat(float bound);
	public int rint(int bound);
	public float noise(float x, float y);
	public double distance(float x1, float y1, float x2, float y2);
	public double distance(Vector2 p1, Vector2 p2);
	
	public double sin(float x);
	public double cos(float x);
	public double min(float v1, float v2);
	public double max(float v1, float v2);
	public float clampf(float value, float min, float max);
	public double clampd(double value, double min, double max);
	public float[] clamp(float[] coord, float xMin, float xMax, float yMin, float yMax);
	public float[] clamp(float[] coord, Rectangle bounds);
	
	public Rectangle bounds(float x, float y, float width, float height);
	public Vector2 vector2(float x, float y);
	public float[] fvector(float x, float y);
	public float mod(float value);
	public double mod(double value);
	
	
	
	
	/*drawing functions*/
	public Texture texture(String path);
	
	public  void fill(Color color);
	public  void fill(float r, float g, float b, float a);
	public void fillRandom();
	
	public void stroke(Color c);
	
	public void circle(float x, float y, float r);
	public void circle(Vector2 pos, float r);
	public void circle(float[] fvector, float r);
	
	public void rect(float x, float y, float w, float h);
	public void rect(Rectangle r);
	public void rect(float[] fvector, float w, float h);
	
	
	public void line(Vector2 p1, Vector2 p2);
	public void line(float[] v1, float[] v2);
	public void line(float x1, float y1, float x2, float y2);
	

	public void resize(int width, int height);
	
	public void seedRandom(long seed);
	public void seedNoise(long seed);
	
	public float random(float min, float max);
	
	public boolean key(int keycode);
	public boolean keyJust(int keycode);
	
	public TextureRegion region(Texture texure, int x, int y, int w, int h);
	
	public void tint(Color color);
	
	public void draw(TextureRegion texture, float x, float y);
	
	public void text(float x, float y, String msg);
	public void autoCamera(float camSpeed, float zoomFactor);
	
	/*time fucntions*/
	public float elapsed();
	
	
}
