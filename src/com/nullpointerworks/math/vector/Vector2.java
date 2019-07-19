/*
 * Creative Commons - Attribution, Share Alike 4.0 
 * Nullpointer Works (2019)
 * Use is subject to license terms.
 */
package com.nullpointerworks.math.vector;

import com.nullpointerworks.math.Approximate;
import com.nullpointerworks.math.random.Randomizer;

/**
 * A 2 dimensional implementation of the {@code Vector} interface. This class contains various 2D vector operations including a few special functions not specified in the interface.
 * @since 1.0.0
 */
public class Vector2 implements Vector 
{
	public static Vector2 New() {return new Vector2();}
	
	@Override
	public float[] New(float... v)
	{
		return new float[] {v[0],v[1]};
	}

	@Override
	public float[] add(float[] a, float[] b)
	{
		return new float[]{	a[0]+b[0],
							a[1]+b[1]};
	}

	@Override
	public float[] add(float[]... r) 
	{
		float[] in = {0f,0f};
		for (float[] v : r)
			in = add(in,v);
		return in;
	}

	@Override
	public float[] sub(float[] a, float[] b)
	{
		return new float[]{	a[0]-b[0],
							a[1]-b[1]};
	}

	@Override
	public float[] mul(float[] a, float f)
	{
		return new float[]{	a[0]*f,
							a[1]*f};
	}

	@Override
	public float div(float[] a, float[] b)
	{
		float n = dot(a,b);
		float d = dot(b,b);
		return n / d;
	}

	@Override
	public float dot(float[] a, float[] b)
	{
		return a[0]*b[0] + a[1]*b[1];
	}

	@Override
	public float[] neg(float[] a)
	{
		return new float[]{-a[0], -a[1]};
	}

	@Override
	public float[] copy(float[] c) 
	{
		float[] v = new float[2];
		v[0] = c[0];
		v[1] = c[1];
		return v;
	}

	@Override
	public String print(float[] v)
	{
		return "x:"+v[0]+" y:"+v[1];
	}

	@Override
	public float[] random(Randomizer rng)
	{
		float x = rng._float();
		float y = rng._float();
		return new float[] {x, y};
	}

	@Override
	public float[] lerp(float[] a, float[] b, float i)
	{
		float j = 1f-i;
		return new float[]{ a[0]*j + b[0]*i,
							a[1]*j + b[1]*i};
	}

	@Override
	public float magnitude(float[] v)
	{
		return (float) StrictMath.sqrt( dot(v,v) );
	}

	@Override
	public float[] limit(float[] v, float f) 
	{
		float[] c = copy(v);
		float m = magnitude(c);
		if (m > f)
		{
			if (m == 0f) m = 0.00001f;
			float invm = f / m;
			c[0] = c[0]*invm;
			c[1] = c[1]*invm;
		}
		return c;
	}

	@Override
	public float[] normalize(float[] a)
	{
		float x = a[0];
		float y = a[1];
		float m = magnitude(a);
		float invm = 1f / m;
		return new float[]{	x*invm, y*invm};
	}
	
	/**
	 * The cross product in 2 dimensions has no valid meaning since all results point in the third dimension. This implementation returns a {@code float[3]} of which the third component, the {@code z} axes, has the projection in depth. The x and y components will always be {@code 0.0}.
	 * @param a - a vector
	 * @param b - another vector
	 * @return the cross product vector
	 * @since 1.0.0
	 */
	@Override
	public float[] cross(float[] a, float[] b) 
	{
		return new float[] {0f, 0f, a[0]*b[1]-a[1]*b[0] };
	}

	@Override
	public float[] planar(float[] O, float[] a, float[] b, float u, float v)
	{
		float x = O[0] + a[0]*u + b[0]*v;
		float y = O[1] + a[1]*u + b[1]*v;
		return new float[] {x,y};
	}
	
	@Override
	public float[] project(float[] A, float[] a, float lambda)
	{
		return add(A, mul(a, lambda) );
	}
	
	// ======================================================
	
	/**
	 * Returns the normal from {@code a}.
	 * @param a - the base vector
	 * @return the normal from {@code a}
	 * @since 1.0.0
	 */
	public float[] normal(float[] a)
	{
		return new float[]{-a[1],a[0]};
	}
	
	/**
	 * Finds the intersection of {@code A+l*a} onto line {@code B+l*b}. The intersection is found with the following formula:
	 * <pre>l = ( dot(n,B) - dot(n,A) ) / dot(n,a)</pre>
	 * @param A - a source vertex
	 * @param a - the projection vector for {@code A}
	 * @param B - a source vertex
	 * @param b - the projection vector for {@code B}
	 * @return the lambda scalar
	 * @since 1.0.0
	 */
	public float intersect(float[] A, float[] a,
						   float[] B, float[] b)
	{
		float[] n 	= normal(b);
		float denom = dot(n,a);
		if (denom == 0.0f) return 0f;
		float numer = dot(n,B) - dot(n,A);
		float l 	= numer / denom;
		return l;
	}
	
	/**
	 * Finds the intersection of {@code A+l*a} onto line {@code B+l*b}. The intersection is returned as a vector
	 * returns both lambda scalars in vector form<br>
	 * @param A - a source vertex
	 * @param a - the projection vector for {@code A}
	 * @param B - a source vertex
	 * @param b - the projection vector for {@code B}
	 * @return two lambda scalars for both axes in the form of a {@code float[2]}
	 * @since 1.0.0
	 */
	public float[] intersect2(float[] A, float[] a,
				   		   	  float[] B, float[] b)
	{
		float x1 = A[0];
		float y1 = A[1];
		float dx1 = a[0];
		float dy1 = a[1];
		
		float x2 = B[0];
		float y2 = B[1];
		float dx2 = b[0];
		float dy2 = b[1];
		
		float t1,t2,det = (dy1 * dx2 - dx1 * dy2);
		if (det != 0f)
		t1 = ((x1 - x2) * dy2 + (y2 - y1) * dx2) / det;
		else t1 = MAX_VALUE;
		
		det = (dy2 * dx1 - dx2 * dy1);
		if (det != 0f)
		t2 = ((x2 - x1) * dy1 + (y1 - y2) * dx1) / det;
		else t2 = MAX_VALUE;
		
		return new float[] {t1, t2};
	}

	final float MAX_VALUE = Float.MAX_VALUE;
	
	/** 
	 * Finds the angle in radians between two vectors. The measured range between vectors is always {@code [0 - pi]}.
	 * <pre>
	 *  u dot v
	 * -------- = cos(t)
	 * |u| * |v|</pre>
	 * 
	 * @return the angle in radians between two vectors
	 * @param u - a vector
	 * @param v - another vector
	 * @return the angle between two vectors
	 * @since 1.0.0
	 */
	public float angle(float[] u, float[] v)
	{
		float magU = (float) Math.sqrt( dot(u,u) );
		float magV = (float) Math.sqrt( dot(v,v) );
		float m = magU * magV;
		if (m == 0.0f) return MAX_VALUE;
		float s = dot(u, v);
		return (float)Approximate.acos(s / m);
	}
	
	/**
	 * Returns a unit vector pointing at the angular direction specified.
	 * @param angle - the angle for the vector
	 * @return a unit vector pointing at the angular direction specified
	 * @since 1.0.0
	 */
	public float[] rotation(float angle)
	{
		float cs = (float)Approximate.cos(angle);
		float sn = (float)Approximate.sin(angle);
		return new float[]{cs,sn};
	}
	
	/**
	 * Returns the reflection vector from a given vector of incidence {@code A+a*l} and the boundary to test {@code B+b*l}.
	 * @param A - the ray source vertex
	 * @param a - the ray projection vector
	 * @param B - boundary source vertex
	 * @param b - boundary projection vector
	 * @return a reflection vector from the boundary
	 * @since 1.0.0
	 */
	public float[] reflection(float[] A, float[] a, float[] B, float[] b)
	{
		// find reflective normal
		float l 	= intersect(A, a, B, b);
		float[] n 	= normalize( normal(b) );
		float[] C 	= project(A, a, l);
		// reflect
		float u 	= dot(n, sub(A, C));
		float[] P	= project(C, n, u+u);
		return sub(P, A);
	}
}
