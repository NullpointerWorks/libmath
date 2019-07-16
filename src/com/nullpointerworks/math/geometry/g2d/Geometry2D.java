/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d;

import com.nullpointerworks.math.geometry.Geometry;

public abstract class Geometry2D extends Geometry
{
	public enum Type 
	{
		Point, Circle, Triangle, Rectangle,
		Group, Null
	}
	
	public abstract float[] center();
	public abstract float area();
	public abstract Geometry2D copy();
	public abstract Rectangle getBoundingBox();
	public abstract Type type();
	
	public abstract void offset(float x, float y);
	public void offset(float[] vec)
	{
		offset(vec[0], vec[1]);
	}
	
	public abstract boolean isInside(float px, float py);
	public boolean isInside(float[] vec)
	{
		return isInside(vec[0], vec[1]);
	}
	
	public Dimension dimension()
	{
		return Dimension.G2D;
	}
}
