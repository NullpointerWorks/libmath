/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.point;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Point;
import com.nullpointerworks.math.geometry.g2d.Rectangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

/**
 * An implementation that detects if a {@code Point} objects is intersection a {@code Rectangle} object.
 * @since 1.0.0
 */
public class PointRectangle implements IIntersectionSolver2 
{
	public static final PointRectangle instance = new PointRectangle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Point p = (Point)A;
		Rectangle r = (Rectangle)B;
		return r.isInside(p.x, p.y);
	}
}
