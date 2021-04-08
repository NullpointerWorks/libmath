/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.triangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.circle.CircleTriangle;

/**
 * Refers to an implementation that detects if a {@code Triangle} objects is intersection a {@code Circle} object.
 * @since 1.0.0
 * @see CircleTriangle
 */
public class TriangleCircle implements IIntersectionSolver2 
{
	public static final TriangleCircle instance = new TriangleCircle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return CircleTriangle.instance.solve(B, A);
	}
}
