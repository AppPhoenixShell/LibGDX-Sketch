# LibGDX-Sketch
LibGDX-Sketch is a "P5 like" implemention using LibGDX that allows you to draw to the screen using a simple API. No external dependancies except LibGDX.

## Dependancies
LibGDX-Sketch only requires the LibGDX library:
https://libgdx.badlogicgames.com/
No External dependacies required

## Setup
1. Download the java files into the project
2. Create a class that implements SketchApp.java
```
public MySketchApp extends SketchApp{
  
  public Sketch createSketch(){
      //return the sketch to run
      return new DrawSketch();
  }
}

```
3. Implement Sketch.java
```
implement:
public void draw(){};
public void setup(){};
public void update(float delta){};
```
4. Set the LibGDX desktop Launcher to the SketchApp.java class
```
LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new MySketchApp(), config);
```
## Usage
To implement a sketch there are 3 methods that need to be implemented in the SketchClass
draw();
setup();
update(float delta);

### draw();
This method is where draw calls are to be run. Check the topic below on what draw calls are avaliable
```
Dev Note:
one of the draw calls is clear(Color c); Unlike other graphics frameworks
you do not have to callclear before a draw, the sketch will automtically 
clear each frame. clear(Color c); only sets the clear color to
be used next frame. It's still good practice however to call clear(Color c);
```

### setup();
This method is called first when the Sketch is run. This method should be used to initialise any resources that need to be used in the Sketch like objects & files.

### update(float delta);

This method is where you update any drawing objects that need to be rendered. Typcially this will be things like update the position values, updating the animations & getting user input. The float delta time is the time in seconds since the last frame. This value should be used to animate objects in realtime to be independant of CPU clock times. 

## Draw Calls
The SketchTool.java contains the list of draw & tooling methods that can be used
Some common examples are:

draw(TextureRegion texture, float x, float y);

fill(Color color);

fill(float red, float green, float blue, float a);

fillRandom();

tint(Color color);

stroke(Color c);

circle(float x, float y, float r);

circle(Vector2 pos, float r);

rect(float x, float y, float w, float h);

rect(Rectangle r);

line(Vector2 p1, Vector2 p2);

line(float x1, float y1, float x2, float y2);

translate(float x, float y);

camera(float x, float y);

## Math Calls
rfloat();

rfloat(float bound);

rint(int bound);

float noise(float x, float y);
	
double distance(float x1, float y1, float x2, float y2);

double distance(Vector2 p1, Vector2 p2);

## Input Calls
You can access various inputs such as mouse & keyboard.

float mouseX();

float mouseY();

boolean key(int keyCode) // Uses Keys LibGDX class

boolean keyJust(int keyCode) // single key press

## Texture Calls
Texture texture(String path);

TextureRegion region(Texture texure, int x, int y, int w, int h);


## Drawing Textures

Using the LibGDX framework, Sketch allows you to load Textures & TextureRegion's using the built in LibGDX classes.
To create a texture add it to the ```assets/``` directory.
load a texture by using:

```Texture spritesheet = texture("game_sprites.png);" ```

You can use texture regions to only draw parts of textures:

```TextureRegion player = region(spritesheet, 0,0, 16, 16); // load 16x16 sprite at (x,y):0,0```

You can then use the draw(TextureRegion region, x, y); to draw the region to a x,y position

```draw(player, 0,0) //draw player at (0,0)```

## Camera Functions

Sketch also have functions to control the camera:

### Auto Camera
Auto camera is a quick way to get caera functionality.
```autoCamera(float camSpeed, float zoomFactor);```
Zoom factor is the value that multiplies the current zoomLevel. 
So a zoomFactor of 1.1 will increase the zoom by %10 each zoom.
For correct behaviour zoomFactor > 1

camSpeed is the translation of the camera after each key press.
For correct behaviour camSpeed must be positive

The "O" & "L" keys are used to zoom in and out. WASD are used for moving the camera.

