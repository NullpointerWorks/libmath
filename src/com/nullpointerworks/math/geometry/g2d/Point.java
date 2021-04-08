/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d;

/**
 * An extension of the {@code Geometry2D} class. Provides the functionality of a point in 2 dimensional space.
 * @since 1.0.0
 */
public class Point extends Geometry2D
{
	public float x,y;
	
	/**
	 * Creates an empty {@code Point} defaulted at location {@code (0,0)}.
	 * @since 1.0.0
	 */
	public Point() {}
	
	/**
	 * Creates a new {@code Point} at the specified location.
	 * @param x - location on the x axes
	 * @param y - location on the y axes
	 * @since 1.0.0
	 */
	public Point(float x, float y)
	{
		this.x=x;
		this.y=y;
	}
	
	@Override
	public Type type() 
	{
		return Type.Point;
	}
	
	/**
	 * Test to see if a location is found inside of the geometry. In this case, a location is inside of the {@code Point} object if the given {@code x} and {@code y} are equal the the {@code Point}'s location.
	 * @param px - x location to test
	 * @param py - y location to test
	 * @return {@code true} if the location is the same as the location of the point
	 * @since 1.0.0
	 */
	@Override
	public boolean isInside(float px, float py) 
	{
		return px==x && py==y;
	}

	@Override
	public float[] center()
	{
		return new float[] {x,y};
	}

	@Override
	public float area()
	{
		return 0f;
	}
	
	@Override
	public void translate(float x, float y)
	{
		this.x += x;
		this.y += y;
	}
	
	@Override
	public Geometry2D copy()
	{
		return new Point(x,y);
	}
	
	@Override
	public Rectangle getBoundingBox() 
	{
		return new Rectangle(x,y, 0,0);
	}
}
