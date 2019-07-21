/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.logic;

import java.util.ArrayList;
import java.util.List;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;

/**
 * Abstract group geometry which extends Geometry2D and supplies some extra methods for adding and clearing the group.
 * @since 1.0.0
 */
public abstract class LogicGroup2D extends Geometry2D
{
	protected List<Geometry2D> geoms = new ArrayList<Geometry2D>();
	
	@Override
	public abstract boolean isInside(float x, float y);
	
	@Override
	public abstract Geometry2D copy();
	
	/**
	 * Add a geometry to this logic group.
	 * @param geom - the 2D geometry to add
	 * @since 1.0.0
	 */
	public void add(Geometry2D geom)
	{
		if (geom!=null) geoms.add(geom);
	}
	
	/**
	 * Removes all geometries from this group.
	 * @since 1.0.0
	 */
	public void clear()
	{
		geoms.clear();
	}
	
	@Override
	public Type type() 
	{
		return Type.Group;
	}
	
	@Override
	public float[] center() 
	{
		float[] sum = {0f,0f};
		float denom = 1f / geoms.size();
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			float[] c = g.center();
			sum[0] += c[0];
			sum[1] += c[1];
		}
		sum[0] *= denom;
		sum[1] *= denom;
		return sum;
	}
	
	@Override
	public void translate(float x, float y) 
	{
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			g.translate(x, y);
		}
	}
	
	final float resolution = 0.01f;
	
	/**
	 * Currently, there is no way to compute the area of the group. This might be implemented in future iterations of the math library.
	 * @return the value {@code -1.0}
	 * @since 1.0.0
	 */
	@Override
	public float area() 
	{
		return -1f;
	}
	
	@Override
	public Rectangle getBoundingBox() 
	{
		float x1 = Float.MAX_VALUE;
		float y1 = Float.MAX_VALUE;
		float x2 = 0f;
		float y2 = 0f;
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			Rectangle r = g.getBoundingBox();
			if (r.x < x1) x1 = r.x;
			if (r.y < y1) y1 = r.y;
			if ((r.w+r.x) > x2) x2 = (r.w+r.x);
			if ((r.h+r.y) > y2) y2 = (r.h+r.y);
		}
		return new Rectangle(x1, y1, x2-x1, y2-y1);
	}
}
