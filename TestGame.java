package app.phoenixshell.kelpie2d.draw;

import app.phoenixshell.kelpie2d.draw.ExampleSketch;
import app.phoenixshell.kelpie2d.draw.Sketch;
import app.phoenixshell.kelpie2d.draw.SketchApp;

public class TestGame extends SketchApp
{
	@Override
	public Sketch createSketch() {
		return new ExampleSketch();
	}

	@Override
	public void onCreate(Sketch sketch) {
		
	}

	/*
	@Override
	public ToolkitUIHandler createUIHandler() {
		return new DesktopToolkit();
	}
	*/
}

