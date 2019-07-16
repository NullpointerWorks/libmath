/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d;

public class NullGeometry extends Geometry2D
{
	@Override
	public Type type() 
	{
		return Type.Point;
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
	public void offset(float x, float y)
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
