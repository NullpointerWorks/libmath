package com.nullpointerworks.math.geometry.g2d.solver.point;

import com.nullpointerworks.math.geometry.g2d.Circle;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Point;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;

public class PointCircle implements IIntersectionSolver2 
{
	public static final PointCircle instance = new PointCircle();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Circle cir = (Circle) A;
		Point p = (Point) B;
		return cir.isInside(p.x, p.y);
	}

}
