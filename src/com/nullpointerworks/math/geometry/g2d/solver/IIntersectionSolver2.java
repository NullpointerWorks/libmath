/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;

/**
 * An interface for solving a 2 dimensional intersection.
 * @since 1.0.0
 */
public interface IIntersectionSolver2 
{
	/**
	 * Returns true if the two geometries are intersecting in some way or another.
	 * @param A - a Geometry2D implementation
	 * @param B - a Geometry2D implementation
	 * @return {@code true} if there is an intersection, {@code false} otherwise
	 * @since 1.0.0
	 */
	public boolean solve(Geometry2D A, Geometry2D B);
}
