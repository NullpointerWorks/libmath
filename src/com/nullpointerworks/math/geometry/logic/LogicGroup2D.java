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

public abstract class LogicGroup2D extends Geometry2D
{
	protected List<Geometry2D> geoms = new ArrayList<Geometry2D>();
	
	@Override
	public abstract boolean isInside(float x, float y);
	
	@Override
	public abstract Geometry2D copy();
	
	public void add(Geometry2D geom)
	{
		if (geom!=null) geoms.add(geom);
	}
	
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
	public void offset(float x, float y) 
	{
		for (int i=0,l=geoms.size(); i<l; i++)
		{
			Geometry2D g = geoms.get(i);
			g.offset(x, y);
		}
	}
	
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
