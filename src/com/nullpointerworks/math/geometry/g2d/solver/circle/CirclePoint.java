/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver.circle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.point.PointCircle;

public class CirclePoint implements IIntersectionSolver2 
{
	public static final CirclePoint instance = new CirclePoint();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		return PointCircle.instance.solve(B, A);
	}

}
