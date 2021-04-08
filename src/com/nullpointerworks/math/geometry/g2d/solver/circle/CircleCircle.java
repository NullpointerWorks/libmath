/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.circle;

import com.nullpointerworks.math.geometry.g2d.Circle;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

/**
 * Implements an algorithm to detect if two {@code Circle} objects are intersection.
 * @since 1.0.0
 */
public class CircleCircle implements IIntersectionSolver2
{
	public static final CircleCircle instance = new CircleCircle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Circle c1 = (Circle) A;
		Circle c2 = (Circle) B;
		
		float dx = c1.x - c2.x;
		float dy = c1.y - c2.y;
		float dr = c1.radius + c2.radius;
		dx*=dx;
		dy*=dy;
		dr*=dr;
		return (dx+dy < dr);
	}
}
