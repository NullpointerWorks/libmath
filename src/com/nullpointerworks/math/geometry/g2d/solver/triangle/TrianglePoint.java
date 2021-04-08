/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.triangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.point.PointTriangle;

/**
 * Refers to an implementation that detects if a {@code Triangle} objects is intersection a {@code Point} object.
 * @since 1.0.0
 * @see PointTriangle
 */
public class TrianglePoint implements IIntersectionSolver2 
{
	public static final TrianglePoint instance = new TrianglePoint();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return PointTriangle.instance.solve(B, A);
	}
}
