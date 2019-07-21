/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d;

import com.nullpointerworks.math.vector.Vector2;

/**
 * An extension of the {@code Geometry2D} class. Provides the functionality of an axes aligned rectangle. This implementation uses two {@code Triangle} objects internally.
 * @since 1.0.0
 * @see Triangle
 */
public class Rectangle extends Geometry2D
{
	private final Vector2 V2 = new Vector2();
	public Triangle t1,t2;
	public float x=0,y=0,w=0,h=0;
	
	/**
	 * Creates an empty rectangle at the location {@code (0,0)} and has no width or height.
	 * @since 1.0.0
	 */
	public Rectangle() {}
	
	/**
	 * Creates a rectangle at the location {@code (x,y)} with a given width and height.
	 * @param x - location on the x axes
	 * @param y - location on the y axes
	 * @param w - width of the rectangle
	 * @param h - height of the rectangle
	 * @since 1.0.0
	 */
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
	public void translate(float x, float y)
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
	
	private void recalculate()
	{
		float[] v0 = V2.New(x, y);
		float[] v1 = V2.New(x+w, y);
		float[] v2 = V2.New(x+w, y+h);
		float[] v3 = V2.New(x, y+h);
		
		t1 = new Triangle(v0,v1,v2);
		t2 = new Triangle(v2,v3,v0);
	}
}
