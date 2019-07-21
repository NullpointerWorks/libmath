/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver.rectangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.point.PointRectangle;

/**
 * Refers to an implementation that detects if a {@code Rectangle} objects is intersection a {@code Point} object.
 * @since 1.0.0
 * @see PointRectangle
 */
public class RectanglePoint implements IIntersectionSolver2 
{
	public static final RectanglePoint instance = new RectanglePoint();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return PointRectangle.instance.solve(B, A);
	}
}
