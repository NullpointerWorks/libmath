/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver.circle;

import com.nullpointerworks.math.geometry.g2d.Circle;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Rectangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.vector.Vector2;

/**
 * An implementation that detects if a {@code Circle} objects is intersection a {@code Rectangle} object.
 * @since 1.0.0
 */
public class CircleRectangle implements IIntersectionSolver2 
{
	public static final CircleRectangle instance = new CircleRectangle();
	private final Vector2 V2 = new Vector2();
	
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Circle cir = (Circle) A;
		Rectangle r = (Rectangle) B;
		
		float rad = -cir.radius;
		float[] c = {cir.x, cir.y};
		float[][] vertices = 
		{
			{r.x, r.y},
			{r.x+r.w, r.y},
			{r.x+r.w, r.y+r.h},
			{r.x, r.y+r.h}
		};
		
		int l=vertices.length;
		for (int i=0;i<l;i++)
		{
			if (cir.isInside(vertices[i])) return true;
			
			float[] v0 = vertices[i];
			float[] v1 = vertices[(i+1)%l];
			
			float[] v = V2.sub(v1, v0);
			float[] n = V2.normalize( V2.normal(v) );
			n = V2.project(c, n, rad);
			
			if (r.isInside(n)) return true;
		}
		return false;
	}

}
