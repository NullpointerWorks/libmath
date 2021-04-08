/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d;

/**
 * An extension of the {@code Geometry2D} class. Contains information for defining a circle in 2D. 
 * @since 1.0.0
 */
public class Circle extends Geometry2D
{
	private final float PI = 3.1415927f;
	public float radius = 0f;
	public float sqr = 0f;
	public float x = 0f;
	public float y = 0f;
	
	/**
	 * Creates an empty circle object without any predefined characteristics.
	 * @since 1.0.0
	 */
	public Circle() {}
	
	/**
	 * Creates a new Circle object defined by it's location and radius.
	 * @param x - location on the x axes
	 * @param y - location on the y axes
	 * @param r - its radius
	 * @since 1.0.0
	 */
	public Circle(float x, float y, float r)
	{
		this.x=x;
		this.y=y;
		radius=r;
		sqr=r*r;
	}
	
	@Override
	public Type type() 
	{
		return Type.Circle;
	}
	
	@Override
	public boolean isInside(float px, float py) 
	{
		float dx = x - px;
		float dy = y - py;
		dx *= dx;
		dy *= dy;
		return (dx+dy) < sqr;
	}
	
	@Override
	public float[] center()
	{
		return new float[] {x,y};
	}
	
	@Override
	public float area()
	{
		return PI * radius * radius;
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
		return new Circle(x,y,radius);
	}
	
	@Override
	public Rectangle getBoundingBox() 
	{
		float x = this.x - radius;
		float y = this.y - radius;
		float w = 2f*radius;
		float h = 2f*radius;
		return new Rectangle(x,y,w,h);
	}
}
