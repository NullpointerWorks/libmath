/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.circle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.point.PointCircle;

/**
 * Refers to an implementation that detects if a {@code Circle} objects and a {@code Point} is intersecting.
 * @since 1.0.0
 * @see PointCircle
 */
public class CirclePoint implements IIntersectionSolver2 
{
	public static final CirclePoint instance = new CirclePoint();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return PointCircle.instance.solve(B, A);
	}

}
