/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.geometry.g2d.solver.circle;

import com.nullpointerworks.math.geometry.g2d.Circle;
import com.nullpointerworks.math.geometry.g2d.Geometry2D;
import com.nullpointerworks.math.geometry.g2d.Triangle;
import com.nullpointerworks.math.geometry.g2d.solver.IIntersectionSolver2;
import com.nullpointerworks.math.vector.Vector2;

/**
 * An implementation that detects if a {@code Circle} objects is intersection a {@code Triangle} object.
 * @since 1.0.0
 */
public class CircleTriangle implements IIntersectionSolver2 
{
	public static final CircleTriangle instance = new CircleTriangle();
	private final Vector2 V2 = new Vector2();
	@Override
	public boolean solve(Geometry2D A, Geometry2D B) 
	{
		Circle cir = (Circle) A;
		Triangle t = (Triangle) B;
		
		float rad = -cir.radius;
		float[] c = {cir.x, cir.y};
		float[][] vrt = {t.v1, t.v2, t.v3};
		
		boolean hand = V2.cross( V2.sub(vrt[1], vrt[0]), V2.sub(vrt[2], vrt[0]) )[2] > 0f;
		
		if (hand)
		{
			vrt[0] = t.v3;
			vrt[2] = t.v1;
		}
		
		int l=vrt.length;
		for (int i=0;i<l;i++)
		{
			if (cir.isInside(vrt[i])) return true;
			float[] v0 = vrt[i];
			float[] v1 = vrt[(i+1)%l];
			float[] v = V2.sub(v1, v0);
			v = V2.normalize( V2.normal(v) );
			v = V2.project(c, v, rad);
			if (t.isInside(v)) return true;
		}
		return false;
	}

}
