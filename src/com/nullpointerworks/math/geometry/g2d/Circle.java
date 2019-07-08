package com.nullpointerworks.math.geometry.g2d;

public class Circle extends Geometry2D
{
	private final float PI = 3.1415927f;
	public float radius = 0f;
	public float sqr = 0f;
	public float x = 0f;
	public float y = 0f;
	
	public Circle() {}
	public Circle(float[] p, float r)
	{
		this(p[0], p[1], r);
	}
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
	public boolean isInside(float[] vec)
	{
		return isInside(vec[0], vec[1]);
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
	public void offset(float x, float y)
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
