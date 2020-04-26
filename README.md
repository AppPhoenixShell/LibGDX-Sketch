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

fill(Color color);
fill(float red, float green, float blue, float a);
fillRandom();
stroke(Color c);
circle(float x, float y, float r);
circle(Vector2 pos, float r);
rect(float x, float y, float w, float h);
rect(Rectangle r);
line(Vector2 p1, Vector2 p2);
line(float x1, float y1, float x2, float y2);
translate(float x, float y);
camera(float x, float y);
