# LibGDX-Sketch
LibGDX-Sketch is a "P5 like" implemention using LibGDX that allows you to draw to the screen using a simple API. No external dependancies except LibGDX.

## Dependancies
LinGDX-Sketch only requires the LibGDX library:
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
