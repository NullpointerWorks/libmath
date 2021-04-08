/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.triangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

/**
 * Since the {@code Rectangle} class contains two triangles, this simplifies the problem to two triangle-triangle intersections. This class refers to an implementation that detects if a {@code Triangle} objects is intersection another {@code Triangle} object.
 * @since 1.0.0
 */
public class TriangleRectangle implements IIntersectionSolver2 
{
	public static final TriangleRectangle instance = new TriangleRectangle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Rectangle r = (Rectangle) B;
		if (TriangleTriangle.instance.solve(A, r.t1)) return true;
		if (TriangleTriangle.instance.solve(A, r.t2)) return true;
		return false;
	}
}
