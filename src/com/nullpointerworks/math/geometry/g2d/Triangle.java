/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d;

import com.nullpointerworks.math.FastMath;
import com.nullpointerworks.math.FloatMath;
import com.nullpointerworks.math.vector.Vector2;

public class Triangle extends Geometry2D
{
	public float[] v1,v2,v3;
	private final int X = 0;
	private final int Y = 1;
	
	public Triangle() {}
	
	public Triangle(float x1, float y1,
					float x2, float y2,
					float x3, float y3)
	{
		v1 = Vector2.New(x1, y1);
		v2 = Vector2.New(x2, y2);
		v3 = Vector2.New(x3, y3);
	}
	
	public Triangle(float[] p1, float[] p2, float[] p3)
	{
		v1 = Vector2.copy(p1);
		v2 = Vector2.copy(p2);
		v3 = Vector2.copy(p3);
	}
	
	@Override
	public Type type() 
	{
		return Type.Triangle;
	}
	
	@Override
	public boolean isInside(float[] vec)
	{
		boolean b1,b2,b3;
		b1 = cross(vec, v1, v2) < 0.0f;
	    b2 = cross(vec, v2, v3) < 0.0f;
	    b3 = cross(vec, v3, v1) < 0.0f;
		return ((b1 == b2) && (b2 == b3));
	}
	
	@Override
	public boolean isInside(float px, float py)
	{
		return isInside(new float[] {px,py});
	}
	
	@Override
	public float[] center()
	{
		// centroid is the average of the sum of each vertex
		float centroid_x = v1[X] + v2[X] + v3[X];
		float centroid_y = v1[Y] + v2[Y] + v3[Y];
		return new float[] {centroid_x*0.33333f, centroid_y*0.33333f};
	}
	
	@Override
	public float area()
	{
		return FastMath.abs( cross(v1, v2, v3) ) * 0.5f;
	}
	
	@Override
	public void offset(float x, float y)
	{
		v1[X] += x;
		v1[Y] += y;
		v2[X] += x;
		v2[Y] += y;
		v3[X] += x;
		v3[Y] += y;
	}
	
	@Override
	public Geometry2D copy()
	{
		return new Triangle(v1,v2,v3);
	}
	
	@Override
	public Rectangle getBoundingBox() 
	{
		float minx = FloatMath.min( v1[X], v2[X], v3[X]);
		float miny = FloatMath.min( v1[Y], v2[Y], v3[Y]);
		float maxx = FloatMath.max( v1[X], v2[X], v3[X]);
		float maxy = FloatMath.max( v1[Y], v2[Y], v3[Y]);
		return new Rectangle(minx,miny, maxx-minx, maxy-miny);
	}
	
	private float cross(float[] p1, float[] p2, float[] p3)
	{
	    return (p1[X] - p3[X]) * (p2[Y] - p3[Y]) - (p2[X] - p3[X]) * (p1[Y] - p3[Y]);
	}
}
