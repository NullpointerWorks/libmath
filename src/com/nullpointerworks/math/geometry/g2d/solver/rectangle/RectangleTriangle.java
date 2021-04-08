/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.rectangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.triangle.TriangleRectangle;

/**
 * Refers to an implementation that detects if a {@code Rectangle} objects is intersection a {@code Triangle} object.
 * @since 1.0.0
 * @see TriangleRectangle
 */
public class RectangleTriangle implements IIntersectionSolver2 
{
	public static final RectangleTriangle instance = new RectangleTriangle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return TriangleRectangle.instance.solve(B, A);
	}
}
