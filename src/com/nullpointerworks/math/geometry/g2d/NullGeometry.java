/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d;

/**
 * A null geometry is meant to be returned when errors occur during calculations. It has no area, its bounding-box will have no meaningful dimensions and will always return {@code true} when testing for intersections.
 * @since 1.0.0
 */
public class NullGeometry extends Geometry2D
{
	@Override
	public Type type() 
	{
		return Type.Null;
	}

	@Override
	public boolean isInside(float px, float py) 
	{
		return true;
	}

	@Override
	public float[] center() 
	{
		return new float[] {0f,0f};
	}

	@Override
	public float area() 
	{
		return 0f;
	}
	
	@Override
	public void translate(float x, float y)
	{
		
	}

	@Override
	public Geometry2D copy()
	{
		return new NullGeometry();
	}

	@Override
	public Rectangle getBoundingBox() 
	{
		return new Rectangle(0f,0f,0f,0f);
	}
}
