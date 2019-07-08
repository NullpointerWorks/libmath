package com.nullpointerworks.math.geometry;

import com.nullpointerworks.math.FloatMath;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.geometry.g2d.solver.circle.*;
import com.nullpointerworks.math.geometry.g2d.solver.point.*;
import com.nullpointerworks.math.geometry.g2d.solver.rectangle.*;
import com.nullpointerworks.math.geometry.g2d.solver.triangle.*;
import com.nullpointerworks.math.geometry.logic.LogicAnd2D;
import com.nullpointerworks.math.geometry.logic.LogicGroup2D;
import com.nullpointerworks.math.geometry.logic.LogicNot2D;
import com.nullpointerworks.math.geometry.logic.LogicOr2D;
import com.nullpointerworks.math.geometry.logic.LogicXor2D;

public class Geometry2 
{
	/*
	 * list of intersection cases
	 * types: Point, Circle, Triangle, Rectangle
	 */
	private static IIntersectionSolver2[][] solvers =
	{
		{PointPoint.instance,
		 PointCircle.instance,
		 PointTriangle.instance,
		 PointRectangle.instance},
		
		{CirclePoint.instance,
		 CircleCircle.instance, 
		 CircleTriangle.instance, 
		 CircleRectangle.instance},
		
		{TrianglePoint.instance,
		 TriangleCircle.instance, 
		 TriangleTriangle.instance, 
		 TriangleRectangle.instance},
		
		{RectanglePoint.instance,
		 RectangleCircle.instance, 
		 RectangleTriangle.instance, 
		 RectangleRectangle.instance},
	};
	
	/**
	 * generalized intersection test
	 */
	public static boolean intersect(Geometry2D A, Geometry2D B)
	{
		int a = A.type().ordinal();
		int b = B.type().ordinal();
		return solvers[a][b].solve(A, B);
	}
	
	public static Geometry2D AND(Geometry2D... g)
	{
		return makeGroup(new LogicAnd2D(),g);
	}
	
	public static Geometry2D OR(Geometry2D... g)
	{
		return makeGroup(new LogicOr2D(),g);
	}
	
	public static Geometry2D XOR(Geometry2D... g)
	{
		return makeGroup(new LogicXor2D(),g);
	}
	
	public static Geometry2D NOT(Geometry2D... g)
	{
		return makeGroup(new LogicNot2D(),g);
	}
	
	private static LogicGroup2D makeGroup(LogicGroup2D lg, Geometry2D... g)
	{
		for (int l=g.length-1; l>=0; l--)
		{
			lg.add(g[l]);
		}
		return lg;
	}
	
	/**
	 * Gauss's shoelace area formula.
	 * @returns the area of the counter-clockwise winded, irregular, non-intersecting polygon
	 */
	public static float area(float[][] polygon)
	{
		float area = 0f;
		int leng = polygon.length - 1;
		for (int i=0; i<leng; i++)
		{
			float[] v = polygon[i];
			float[] n = polygon[i+1];
			float x_hand = v[0] * n[1];
			float y_hand = n[0] * v[1];
			area += (x_hand - y_hand);
		}
		return area * 0.5f;
	}
	
	/**
	 * Finds the fitting rectangle in the overlap of the two given rectangles
	 */
	public static Rectangle intersection(Rectangle r1, Rectangle r2)
	{
		float lx = FloatMath.max( r1.x, r2.x );
		float rx = FloatMath.min( r1.x+r1.w, r2.x+r2.w );
		float ty = FloatMath.max( r1.y, r2.y );
		float by = FloatMath.min( r1.y+r1.h, r2.y+r2.h );
		return new Rectangle( lx, ty, rx-lx, by-ty );
	}
}
