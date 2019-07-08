package com.nullpointerworks.math.geometry.g2d;

import com.nullpointerworks.math.vector.Vector2;

public class Rectangle extends Geometry2D
{
	public Triangle t1,t2;
	public float x=0,y=0,w=0,h=0;
	
	public Rectangle() {}
	
	public Rectangle(float x, float y, float w, float h)
	{
		this.x=x;
		this.y=y;
		this.w=w;
		this.h=h;

		recalculate();
	}

	@Override
	public Type type() 
	{
		return Type.Rectangle;
	}

	@Override
	public boolean isInside(float px, float py)
	{
		if (t1.isInside(px, py)) return true;
		if (t2.isInside(px, py)) return true;
		return false;
	}
	
	@Override
	public float[] center()
	{
		return new float[] {x+(w*0.5f), y+(h*0.5f)};
	}
	
	@Override
	public float area()
	{
		return w*h;
	}
	
	@Override
	public void offset(float x, float y)
	{
		this.x += x;
		this.y += y;
		recalculate();
	}
	
	@Override
	public Geometry2D copy()
	{
		return new Rectangle(x,y,w,h);
	}
	
	@Override
	public Rectangle getBoundingBox() 
	{
		return this;
	}
	
	// ==========================================
	
	private void recalculate()
	{
		float[] v0 = Vector2.New(x, y);
		float[] v1 = Vector2.New(x+w, y);
		float[] v2 = Vector2.New(x+w, y+h);
		float[] v3 = Vector2.New(x, y+h);
		
		t1 = new Triangle(v0,v1,v2);
		t2 = new Triangle(v2,v3,v0);
	}
}
