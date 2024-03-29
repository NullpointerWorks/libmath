/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.rectangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

/**
 * An implementation that detects if a {@code Rectangle} objects is intersection another {@code Rectangle} object.
 * @since 1.0.0
 */
public class RectangleRectangle implements IIntersectionSolver2 
{
	public static final RectangleRectangle instance = new RectangleRectangle();

	/*
	 * Axis-Aligned Bounding Box collision checking
	 * separating-axes check
	 */
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Rectangle a = (Rectangle)A;
		Rectangle b = (Rectangle)B;
		
		float a_centerx = a.x + (a.w - a.x)*0.5f;
		float b_centerx = b.x + (b.w - b.x)*0.5f;
		if ( abs(a.x - b.x) > (a_centerx + b_centerx) ) return false;
		
		float a_centery = a.y + (a.h - a.y)*0.5f;
		float b_centery = b.y + (b.h - b.y)*0.5f;
		if ( abs(a.y - b.y) > (a_centery + b_centery) ) return false;
		
	    return true;
	}
	
	private float abs(float v)
	{
		return (v<0f)?-v:v;
	}
}
