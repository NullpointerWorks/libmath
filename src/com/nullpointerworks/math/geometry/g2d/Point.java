package com.nullpointerworks.math.geometry.g2d;

public class Point extends Geometry2D
{
	public float x,y;
	
	public Point() {}
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
	public void offset(float x, float y)
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
		return new Rectangle(x,y, 1,1);
	}
	
}
