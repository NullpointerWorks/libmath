/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

public interface IIntersectionSolver2 
{
	public boolean solve(Geometry2D A, Geometry2D B);
}
