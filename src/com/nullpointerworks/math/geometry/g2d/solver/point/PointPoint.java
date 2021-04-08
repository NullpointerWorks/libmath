/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.point;

import com.nullpointerworks.math.FloatMath;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Point;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

/**
 * An implementation that detects if a {@code Point} objects is intersection another {@code Point} object.
 * @since 1.0.0
 */
public class PointPoint implements IIntersectionSolver2 
{
	public static final PointPoint instance = new PointPoint();

	public static float TOLERANCE = 0.00001f;
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Point a = (Point)A;
		Point b = (Point)B;
		
		float dx = FloatMath.abs(b.x - a.x);
		float dy = FloatMath.abs(b.y - a.y);
		
		return (dx*dx + dy*dy) < TOLERANCE;
	}
}
