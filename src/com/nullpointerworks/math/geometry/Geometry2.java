/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
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

/**
 * Contains static members for two-dimensional geometry manipulation, logic operations and intersection tests. 
 * @since 1.0.0
 */
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
	 * Test if two base primitives are in any way intersecting. A base geometry would be any implementation of a Point, Circle, Triangle or Rectangle.
	 * @param A - any base primitives
	 * @param B - any base primitives
	 * @return {@code true} if the two geometries are intersecting
	 * @since 1.0.0
	 * @see Point
	 * @see Circle
	 * @see Triangle
	 * @see Rectangle
	 */
	public static boolean intersect(Geometry2D A, Geometry2D B)
	{
		int a = A.type().ordinal();
		int b = B.type().ordinal();
		return solvers[a][b].solve(A, B);
	}
	
	/**
	 * Creates a logic "and" group of geometries. Any location tested in this group will return {@code true} if all geometries have been intersected.
	 * @param g - a list of 2D geometries
	 * @return a logic NOT group
	 * @since 1.0.0
	 */
	public static Geometry2D AND(Geometry2D... g)
	{
		return makeGroup(new LogicAnd2D(),g);
	}
	
	/**
	 * Creates a logic "or" group of geometries. Any location tested in this group will return {@code false} if none of the geometries have been intersected. 
	 * @param g - a list of 2D geometries
	 * @return a logic NOT group
	 * @since 1.0.0
	 */
	public static Geometry2D OR(Geometry2D... g)
	{
		return makeGroup(new LogicOr2D(),g);
	}
	
	/**
	 *
	 * Creates a logic exclusive or group of geometries. Any location tested in this group will return {@code false} if an even number, including {@code 0}, geometries have been intersected.
	 * @param g - a list of 2D geometries
	 * @return a logic XOR group
	 * @since 1.0.0
	 */
	public static Geometry2D XOR(Geometry2D... g)
	{
		return makeGroup(new LogicXor2D(),g);
	}
	
	/**
	 * Creates a logic "not" group of geometries. Any location tested in this group will return {@code false} if any of the geometries have been intersected.
	 * @param g - a list of 2D geometries
	 * @return a logic NOT group
	 * @since 1.0.0
	 */
	public static Geometry2D NOT(Geometry2D... g)
	{
		return makeGroup(new LogicNot2D(),g);
	}
	
	/**
	 * Compiles a logic group with the list of geometries provided.
	 * @param lg - the logic group to be applied
	 * @param g - a list of 2D geometries
	 * @return a 2D logic group
	 * @since 1.0.0
	 */
	private static LogicGroup2D makeGroup(LogicGroup2D lg, Geometry2D... g)
	{
		for (int l=g.length-1; l>=0; l--)
		{
			lg.add(g[l]);
		}
		return lg;
	}
	
	/**
	 * Finds the fitting rectangle in the overlap of the two given rectangles
	 * @param 
	 * @param 
	 * @return 
	 * @since 1.0.0
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
