/*
 * This is free and unencumbered software released into the public domain.
 * (http://unlicense.org/)
 * Nullpointer Works (2021)
 */
package com.nullpointerworks.math.geometry.g2d.solver.triangle;

import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Triangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.vector.Vector2;

/**
 * An implementation that detects if a {@code Triangle} objects is intersection another {@code Triangle} object.
 * @since 1.0.0
 */
public class TriangleTriangle implements IIntersectionSolver2 
{
	public static final TriangleTriangle instance = new TriangleTriangle();

	private int X = 0;
	private int Y = 1;
	
	private float[] lambda(float[] A, float[] a,
						   float[] B, float[] b)
	{
		float x1 = A[X];
		float y1 = A[Y];
		float dx1 = a[X];
		float dy1 = a[Y];
		
		float x2 = B[X];
		float y2 = B[Y];
		float dx2 = b[X];
		float dy2 = b[Y];
		
		float t1,t2,det = (dy1 * dx2 - dx1 * dy2);
		if (det != 0f)
			t1 = ((x1 - x2) * dy2 + (y2 - y1) * dx2) / det;
		else t1 = Float.MAX_VALUE;
		
		det = (dy2 * dx1 - dx2 * dy1);
		if (det != 0f)
			t2 = ((x2 - x1) * dy1 + (y1 - y2) * dx1) / det;
		else t2 = Float.MAX_VALUE;
		
		return new float[] {t1, t2};
	}
	
	private final Vector2 V2 = new Vector2();
	
	@Override
	public boolean solve(Geometry2D gA, Geometry2D gB) 
	{
		Triangle ta = (Triangle) gA;
		Triangle tb = (Triangle) gB;
		
		// check points first
		if (ta.isInside(tb.v1)) return true;
		if (ta.isInside(tb.v2)) return true;
		if (ta.isInside(tb.v3)) return true;
		
		if (tb.isInside(ta.v1)) return true;
		if (tb.isInside(ta.v2)) return true;
		if (tb.isInside(ta.v3)) return true;
	    
		// check edges
		float[][] poly_a = {ta.v1,ta.v2,ta.v3};
		float[][] poly_b = {tb.v1,tb.v2,tb.v3};
		
		int j=0;
		while (j<3)
		{
			float[] A = poly_a[j];
			float[] a = V2.sub(poly_a[(j+1)%3], A);
			
			int i=0;
			while (i<3)
			{
				float[] B = poly_b[i];
				float[] b = V2.sub(poly_b[(i+1)%3], B);
				
				float[] l12 = lambda(A,a, B,b);
				float t1 = l12[X];
				float t2 = l12[Y];

				if (t1>=0f && t1<=1f)
					if (t2>=0f && t2<=1f)
						return true;
				
				i++;
			}
			j++;
		}
		return false;
	}
	
}
