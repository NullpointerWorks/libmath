/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver.triangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

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
