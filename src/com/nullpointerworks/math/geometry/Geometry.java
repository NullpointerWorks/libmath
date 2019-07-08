package com.nullpointerworks.math.geometry;

public abstract class Geometry
{
	public enum Dimension
	{
		G2D,G3D
	}
	
	public abstract float[] center();
	public abstract void offset(float[] v);
	public abstract boolean isInside(float[] v);
	public abstract Geometry copy();
	public abstract Dimension dimension();
}
