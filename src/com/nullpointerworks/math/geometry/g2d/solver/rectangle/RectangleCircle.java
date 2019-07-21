/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver.rectangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.circle.CircleRectangle;

/**
 * Refers to an implementation that detects if a {@code Rectangle} objects is intersection a {@code Circle} object.
 * @since 1.0.0
 * @see CircleRectangle
 */
public class RectangleCircle implements IIntersectionSolver2 
{
	public static final RectangleCircle instance = new RectangleCircle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return CircleRectangle.instance.solve(B, A);
	}
}
