package app.phoenixshell.kelpie2d.draw;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Structs
{
	public static class IntMatrix{
		public final int[][] matrix;
		public final int width;
		public final int height;
		
		public IntMatrix(int width, int height) {
			this.width = width;
			this.height = height;
			this.matrix = new int[width][height];
		}
		
	}
	public static class FloatMatrix{
		public final float[][] matrix;
		public final int width;
		public final int height;
		
		public FloatMatrix(int width, int height) {
			this.width = width;
			this.height = height;
			this.matrix = new float[width][height];
		}
		
	}
	
	public static interface ComplexShape{
		void onDraw(SketchTool shape);
	}
}
