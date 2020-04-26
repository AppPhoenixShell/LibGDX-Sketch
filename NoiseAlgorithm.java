package app.phoenixshell.kelpie2d.draw;

/**
 *  Interface for a noise algorithm. Use sketch.noise() to get a noise value
 * */

public interface NoiseAlgorithm 
{
	public double noise(double x, double y);
	public double noise(double x, double y, double z);
	public void setSeed(long seed);
}